package com.ichinait.food.service.project;

import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.databind.JavaType;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.ichinait.food.cache.ProjectCache;
import com.ichinait.food.constant.Constant;
import com.ichinait.food.db.entity.Instrument;
import com.ichinait.food.db.entity.Project;
import com.ichinait.food.db.entity.ProjectExample;
import com.ichinait.food.db.entity.Sample;
import com.ichinait.food.db.entity.plus.ExcelDatas;
import com.ichinait.food.db.entity.plus.ExcelProject;
import com.ichinait.food.db.entity.plus.ProjectPlus;
import com.ichinait.food.db.mapper.ProjectMapper;
import com.ichinait.food.db.mapper.plus.DataMapperPlus;
import com.ichinait.food.db.mapper.plus.ProjectMapperPlus;
import com.ichinait.food.dto.data.ValuesDTO;
import com.ichinait.food.dto.project.ProjectDTO;
import com.ichinait.food.service.datas.DatasService;
import com.ichinait.food.util.ExcelUtil;
import com.ichinait.food.util.JsonMapper;

@Service
@Transactional(isolation=Isolation.READ_COMMITTED)
public class ProjectService {
	
	@Resource
	private ProjectMapper projectMapper;
	@Resource
	private ProjectMapperPlus projectMapperPlus;

	@Resource
	private DatasService datasService;

    @Resource
    private DataMapperPlus dataMapperPlus;
	
	public PageInfo<ProjectPlus> queryProject(int curror,ProjectDTO projectDto){
        Map<String,Object> params = Maps.newHashMap();
        if(!Strings.isNullOrEmpty(projectDto.getProjectName())){
            params.put("projectName",projectDto.getProjectName());
        }
        PageHelper.startPage(curror, Constant.PAGE_LIMIT);
        PageHelper.orderBy("p.ct desc");
		List<ProjectPlus> projects = projectMapperPlus.selectProjectByCondition(params);
		PageInfo<ProjectPlus> pageInfo = new PageInfo<ProjectPlus>(projects);
		return pageInfo;
	}


	public List<Project> queryProjectNoPage(){
		ProjectExample example = new ProjectExample();
		example.createCriteria().andStatusEqualTo(Constant.PROJECT_STATUS_STARTED);
		List<Project> projects = projectMapper.selectByExample(example);
		return projects;
	}
	
	
	public boolean saveOrUpdateProject(Project project){
		boolean flag = false;
		if(Strings.isNullOrEmpty(project.getId())){
			project.setId(UUID.randomUUID().toString());
			project.setCt(new Date());
			project.setUt(new Date());
			project.setStatus(Constant.DATA_VALID_STATE);
			flag = projectMapper.insert(project) > 0;

		}else{
            project.setUt(new Date());
			flag = projectMapper.updateByPrimaryKeySelective(project) >0;
			//更新缓存
			ProjectCache.getInstance().update();
		}
		return flag;
	}
	
	
	public Project queryProjectById(String id){
		return projectMapper.selectByPrimaryKey(id);
	}


	public boolean delete(String id){
		Project project = new Project();
		project.setId(id);
		project.setStatus(Constant.DATA_INVALID_STATE);
        project.setUt(new Date());
        ProjectCache.getInstance().remove(project);
		return projectMapper.updateByPrimaryKeySelective(project)>0;
	}



    public boolean start(String id){
        Project project = new Project();
        project.setId(id);
        project.setStatus(Constant.PROJECT_STATUS_STARTED);
        project.setUt(new Date());
        int updates = projectMapper.updateByPrimaryKeySelective(project);
        //更新缓存
        ProjectCache.getInstance().update();
        return updates>0;
    }

    public boolean finish(String id){
        Project project = new Project();
        project.setId(id);
        project.setStatus(Constant.PROJECT_STATUS_FINISH);
        project.setUt(new Date());
        ProjectCache.getInstance().remove(project);
        return projectMapper.updateByPrimaryKeySelective(project)>0;
    }


	public Map<String, Object> generateReport(String id){
		List<ExcelDatas> datasList = dataMapperPlus.selectExcelDatasByProjectId(id);
		Map<String, Object> result = generateExcel(datasList, id);
		return result;
	}


	public Map<String, Object> generateExcel(List<ExcelDatas> dataList, String id){
		Map<String, Object> result = new HashMap<String, Object>();
		Workbook workbook = new XSSFWorkbook();
		//JsonMapper mapper = JsonMapper.nonEmptyMapper();

		// addBy xiao 2016-04-26 添加实验详情sheet
		// updateBy xiao 2016-07-06 新版本实验报告
		String projectName = this.generateProjectSheet(workbook, dataList, id);

		// 样品数据
		/*XSSFSheet sheet = (XSSFSheet) workbook.createSheet("样品数据详情");
		List<Sample> chartY = Lists.newArrayList();
		for(int j=0; j<dataList.size(); j++){
			JavaType type = mapper.constructCollectionType(List.class, ValuesDTO.class);
			List<ValuesDTO>  valuesDTOs = mapper.fromJson(dataList.get(j).getData(), type);
			Sample sample = new Sample();
			sample.setId(valuesDTOs.size()+"");
			sample.setName(dataList.get(j).getSampleName());
			chartY.add(sample);

			for(int i =0;i<valuesDTOs.size();i++){
				if(j==0){
					sheet.createRow(0).createCell(0).setCellValue("X轴");
					Cell cell = sheet.createRow(i+1).createCell(0);
					cell.setCellValue(valuesDTOs.get(i).getX());
					sheet.getRow(0).createCell(1).setCellValue("Y轴(" + dataList.get(j).getSampleName() + ")");
				}

				Cell cell1 =  sheet.getRow(i+1).createCell(j+1);
				cell1.setCellValue(valuesDTOs.get(i).getY());
			}
			if(j > 0){
				sheet.getRow(0).createCell(j+1).setCellValue("Y轴(" + dataList.get(j).getSampleName() + ")");
			}
			// 列宽自适应
			sheet.autoSizeColumn(j);
		}
		sheet.autoSizeColumn(dataList.size());

		// addBy xiao 2016-04-26 生成折线图
		ExcelUtil.DrawLineChart(sheet, chartY);*/

		result.put("workbook", workbook);
		result.put("projectName", projectName);

		return result;
	}

	
	private String generateProjectSheet(Workbook workbook, List<ExcelDatas> dataList, String id){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		List<Sample> sampleList = new ArrayList<Sample>();
		List<Instrument> instrumentList = new ArrayList<Instrument>();
		for(ExcelDatas data : dataList){
			Sample sample = new Sample();
			Instrument instrument = new Instrument();

			sample.setName(data.getSampleName());
			sample.setCode(data.getSampleCode());
			sample.setManufactor(data.getSampleManufactor());
			sample.setProducingArea(data.getSampleProducingArea());
			sample.setProductionDate(data.getSampleProductionDate());
			sample.setBatches(data.getSampleBatches());
			sampleList.add(sample);

			instrument.setName(data.getInstrumentName());
			instrument.setSn(data.getInstrumentSN());
			instrument.setCategory(data.getInstrumentCategory());
			instrument.setType(data.getInstrumentType());
			instrument.setModel(data.getInstrumentModel());
			instrument.setManufactor(data.getInstrumentManufactor());
			instrumentList.add(instrument);
		}

		ExcelProject project = projectMapperPlus.selectProjectByIdForExcel(id);

		XSSFSheet sheet = (XSSFSheet) workbook.createSheet(project.getName());
		int rowIndex = 0;
		// 合并单元格的首行、最后一行、首列、最后一列
		Row row = sheet.createRow(0);
		for(int i=0; i<6; i++){
			row.createCell(i).setCellStyle(this.setCellStyle(workbook, "head"));;
		}
		row.getCell(0).setCellValue("实验报告记录表");
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 6));
		row.getCell(0).setCellStyle(this.setCellStyle(workbook, "head"));
		
		++ rowIndex;
		sheet.addMergedRegion(new CellRangeAddress(rowIndex, rowIndex, 0, 6));
		row = sheet.createRow(rowIndex);
		row.createCell(0).setCellValue("实验信息");
		row.getCell(0).setCellStyle(this.setCellStyle(workbook, "title"));

		row = sheet.createRow(++rowIndex);
		row.createCell(0).setCellValue("实验名称");
		row.getCell(0).setCellStyle(this.setCellStyle(workbook, "other"));
		row.createCell(1).setCellValue("实验员");
		row.getCell(1).setCellStyle(this.setCellStyle(workbook, "other"));
		row.createCell(2).setCellValue("开始时间");
		row.getCell(2).setCellStyle(this.setCellStyle(workbook, "other"));
		row.createCell(3).setCellValue("结束时间");
		row.getCell(3).setCellStyle(this.setCellStyle(workbook, "other"));
		row.createCell(4).setCellValue("实验方案");
		row.getCell(4).setCellStyle(this.setCellStyle(workbook, "other"));
		row.createCell(5).setCellValue("实验标准");
		row.getCell(5).setCellStyle(this.setCellStyle(workbook, "other"));
		row.createCell(6).setCellValue(" ");
		row.getCell(6).setCellStyle(this.setCellStyle(workbook, "other"));

		row = sheet.createRow(++rowIndex);
		row.createCell(0).setCellValue(project.getName());
		row.getCell(0).setCellStyle(this.setCellStyle(workbook, "other"));
		row.createCell(1).setCellValue(project.getUserName());
		row.getCell(1).setCellStyle(this.setCellStyle(workbook, "other"));
		row.createCell(2).setCellValue(sdf.format(project.getStartDate())+"");
		row.getCell(2).setCellStyle(this.setCellStyle(workbook, "other"));
		row.createCell(3).setCellValue(sdf.format(project.getEndDate())+"");
		row.getCell(3).setCellStyle(this.setCellStyle(workbook, "other"));
		if(StringUtils.isEmpty(project.getSchemaName())){
			row.createCell(4).setCellValue("");
		} else {
			row.createCell(4).setCellValue(project.getSchemaName().substring(0, project.getSchemaName().indexOf(".")));
		}
		row.getCell(4).setCellStyle(this.setCellStyle(workbook, "other"));
		if(StringUtils.isEmpty(project.getStandardName())){
			row.createCell(5).setCellValue("");
		} else {
			row.createCell(5).setCellValue(project.getStandardName().substring(0, project.getStandardName().indexOf(".")));
		}
		row.getCell(5).setCellStyle(this.setCellStyle(workbook, "other"));
		row.createCell(6).setCellValue("");
		row.getCell(6).setCellStyle(this.setCellStyle(workbook, "other"));

		++rowIndex;
		sheet.addMergedRegion(new CellRangeAddress(rowIndex, rowIndex, 0, 6));
		row = sheet.createRow(rowIndex);

		++rowIndex;
		sheet.addMergedRegion(new CellRangeAddress(rowIndex, rowIndex, 0, 6));
		row = sheet.createRow(rowIndex);
		row.createCell(0).setCellValue("样品信息");
		row.getCell(0).setCellStyle(this.setCellStyle(workbook, "title"));

		row = sheet.createRow(++rowIndex);
		row.createCell(0).setCellValue("序号");
		row.getCell(0).setCellStyle(this.setCellStyle(workbook, "other"));
		row.createCell(1).setCellValue("样品名称");
		row.getCell(1).setCellStyle(this.setCellStyle(workbook, "other"));
		row.createCell(2).setCellValue("编号");
		row.getCell(2).setCellStyle(this.setCellStyle(workbook, "other"));
		row.createCell(3).setCellValue("生产商");
		row.getCell(3).setCellStyle(this.setCellStyle(workbook, "other"));
		row.createCell(4).setCellValue("生产地");
		row.getCell(4).setCellStyle(this.setCellStyle(workbook, "other"));
		row.createCell(5).setCellValue("生产日期");
		row.getCell(5).setCellStyle(this.setCellStyle(workbook, "other"));
		row.createCell(6).setCellValue("批次");
		row.getCell(6).setCellStyle(this.setCellStyle(workbook, "other"));

		++rowIndex;
		for(int i=0; i<sampleList.size(); i++){
			row = sheet.createRow(rowIndex + i);
			row.createCell(0).setCellValue(i+1+"");
			row.getCell(0).setCellStyle(this.setCellStyle(workbook, "other"));
			row.createCell(1).setCellValue(sampleList.get(i).getName());
			row.getCell(1).setCellStyle(this.setCellStyle(workbook, "other"));
			row.createCell(2).setCellValue(sampleList.get(i).getCode());
			row.getCell(2).setCellStyle(this.setCellStyle(workbook, "other"));
			row.createCell(3).setCellValue(sampleList.get(i).getManufactor());
			row.getCell(3).setCellStyle(this.setCellStyle(workbook, "other"));
			row.createCell(4).setCellValue(sampleList.get(i).getProducingArea());
			row.getCell(4).setCellStyle(this.setCellStyle(workbook, "other"));
			row.createCell(5).setCellValue(sdf.format(sampleList.get(i).getProductionDate())+"");
			row.getCell(5).setCellStyle(this.setCellStyle(workbook, "other"));
			row.createCell(6).setCellValue(sampleList.get(i).getBatches());
			row.getCell(6).setCellStyle(this.setCellStyle(workbook, "other"));
		}

		rowIndex = rowIndex + sampleList.size();
		sheet.addMergedRegion(new CellRangeAddress(rowIndex, rowIndex, 0, 6));
		row = sheet.createRow(rowIndex);

		++ rowIndex;
		sheet.addMergedRegion(new CellRangeAddress(rowIndex, rowIndex, 0, 6));
		row = sheet.createRow(rowIndex);
		row.createCell(0).setCellValue("设备信息");
		row.getCell(0).setCellStyle(this.setCellStyle(workbook, "title"));

		row = sheet.createRow(++rowIndex);
		row.createCell(0).setCellValue("序号");
		row.getCell(0).setCellStyle(this.setCellStyle(workbook, "other"));
		row.createCell(1).setCellValue("设备名称");
		row.getCell(1).setCellStyle(this.setCellStyle(workbook, "other"));
		row.createCell(2).setCellValue("序列号");
		row.getCell(2).setCellStyle(this.setCellStyle(workbook, "other"));
		row.createCell(3).setCellValue("分类");
		row.getCell(3).setCellStyle(this.setCellStyle(workbook, "other"));
		row.createCell(4).setCellValue("类型");
		row.getCell(4).setCellStyle(this.setCellStyle(workbook, "other"));
		row.createCell(5).setCellValue("型号");
		row.getCell(5).setCellStyle(this.setCellStyle(workbook, "other"));
		row.createCell(6).setCellValue("生产商");
		row.getCell(6).setCellStyle(this.setCellStyle(workbook, "other"));

		++rowIndex;
		for(int j=0; j<instrumentList.size(); j++){
			row = sheet.createRow(rowIndex + j);
			row.createCell(0).setCellValue(j+1+"");
			row.getCell(0).setCellStyle(this.setCellStyle(workbook, "other"));
			row.createCell(1).setCellValue(instrumentList.get(j).getName());
			row.getCell(1).setCellStyle(this.setCellStyle(workbook, "other"));
			row.createCell(2).setCellValue(instrumentList.get(j).getSn());
			row.getCell(2).setCellStyle(this.setCellStyle(workbook, "other"));
			row.createCell(3).setCellValue(instrumentList.get(j).getCategory());
			row.getCell(3).setCellStyle(this.setCellStyle(workbook, "other"));
			row.createCell(4).setCellValue(instrumentList.get(j).getType());
			row.getCell(4).setCellStyle(this.setCellStyle(workbook, "other"));
			row.createCell(5).setCellValue(instrumentList.get(j).getModel());
			row.getCell(5).setCellStyle(this.setCellStyle(workbook, "other"));
			row.createCell(6).setCellValue(instrumentList.get(j).getManufactor());
			row.getCell(6).setCellStyle(this.setCellStyle(workbook, "other"));
		}

		rowIndex = instrumentList.size() + rowIndex;
		sheet.addMergedRegion(new CellRangeAddress(rowIndex, rowIndex, 0, 6));
		row = sheet.createRow(rowIndex);

		
		
		List<Sample> chartY = Lists.newArrayList();
		JsonMapper mapper = JsonMapper.nonEmptyMapper();
		XSSFSheet dataSheet = (XSSFSheet) workbook.createSheet("样品数据详情");
		for(int j=0; j<dataList.size(); j++){
			JavaType type = mapper.constructCollectionType(List.class, ValuesDTO.class);
			List<ValuesDTO>  valuesDTOs = mapper.fromJson(dataList.get(j).getData(), type);
			Sample sample = new Sample();
			sample.setId(valuesDTOs.size()+"");// 为了生成chart时知道应该获取多少row的数据
			sample.setName(dataList.get(j).getSampleName());// 为了生成chart时, 给line命名
			chartY.add(sample);

			for(int i =0;i<valuesDTOs.size();i++){
				if(j==0){
					dataSheet.createRow(0).createCell(0).setCellValue("X轴");
					Cell cell = dataSheet.createRow(i+1).createCell(0);
					cell.setCellValue(valuesDTOs.get(i).getX());
					dataSheet.getRow(0).createCell(1).setCellValue("Y轴(" + dataList.get(j).getSampleName() + ")");
				}

				Cell cell1 =  dataSheet.getRow(i+1).createCell(j+1);
				cell1.setCellValue(valuesDTOs.get(i).getY());
			}
			if(j > 0){
				dataSheet.getRow(0).createCell(j+1).setCellValue("Y轴(" + dataList.get(j).getSampleName() + ")");
			}
			// 列宽自适应
			dataSheet.autoSizeColumn(j);
		}
		dataSheet.autoSizeColumn(dataList.size());

		// addBy xiao 2016-04-26 生成折线图
		ExcelUtil.DrawLineChart(sheet, dataSheet, chartY, ++rowIndex);
		// 隐藏生成报表数据sheet
		workbook.setSheetHidden(1, true);
		
		rowIndex = rowIndex + 13;
		sheet.addMergedRegion(new CellRangeAddress(rowIndex, rowIndex, 0, 6));
		++ rowIndex;
		sheet.addMergedRegion(new CellRangeAddress(rowIndex, rowIndex, 0, 6));
		row = sheet.createRow(rowIndex);
		row.createCell(0).setCellValue("说明");
		row.getCell(0).setCellStyle(this.setCellStyle(workbook, "title"));

		++ rowIndex;
		sheet.addMergedRegion(new CellRangeAddress(rowIndex, rowIndex+5, 0, 6));
		// 偷懒设置cellStyle, 用个setAsActiveCell(), 掩盖一下
		sheet.createRow(rowIndex).createCell(0).setAsActiveCell();

		// 列宽自适应
		sheet.autoSizeColumn(0);
		sheet.autoSizeColumn(1);
		sheet.autoSizeColumn(2);
		sheet.autoSizeColumn(3);
		sheet.autoSizeColumn(4);
		sheet.autoSizeColumn(5);
		sheet.autoSizeColumn(6);

		
		return project.getName();
	}

	private XSSFCellStyle setCellStyle(Workbook workbook, String type){
		XSSFCellStyle style = (XSSFCellStyle) workbook.createCellStyle();
		XSSFFont font = (XSSFFont) workbook.createFont();
		font.setFontName("仿宋");
		if(type.equals("head")){
			font.setFontHeightInPoints((short)14);//设置字号
			font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);//加粗
			style.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);// 垂直    
	        style.setAlignment(XSSFCellStyle.ALIGN_CENTER);// 水平
		} 
		else if(type.equals("title")){
			font.setFontHeightInPoints((short)12);//设置字号
			font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);//加粗
			style.setFillForegroundColor(new XSSFColor( new Color(197, 217, 241)));// 自定义背景色
			style.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
		}
		else {
			// 边框
			style.setBorderTop(XSSFCellStyle.BORDER_THIN);
			style.setTopBorderColor(new XSSFColor( new Color(79, 129, 189)));
			style.setBorderBottom(XSSFCellStyle.BORDER_THIN);
			style.setBottomBorderColor(new XSSFColor( new Color(79, 129, 189)));
			style.setBorderLeft(XSSFCellStyle.BORDER_THIN);
			style.setLeftBorderColor(new XSSFColor( new Color(79, 129, 189)));
			style.setBorderRight(XSSFCellStyle.BORDER_THIN);
			style.setRightBorderColor(new XSSFColor( new Color(79, 129, 189)));
		}
		style.setFont(font);

		return style;
	}
}
