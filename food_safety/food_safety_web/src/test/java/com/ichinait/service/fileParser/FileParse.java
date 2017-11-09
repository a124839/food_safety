package com.ichinait.service.fileParser;

import com.google.common.base.Charsets;
import com.google.common.base.Throwables;
import com.google.common.io.CharStreams;
import com.google.common.io.Files;
import com.ichinait.food.db.entity.Datas;
import com.ichinait.food.db.mapper.DatasMapper;
import com.ichinait.food.db.mapper.plus.DataMapperPlus;
import com.ichinait.food.util.JsonMapper;
import org.apache.poi.ss.usermodel.charts.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;
import org.apache.poi.xssf.usermodel.charts.XSSFChartLegend;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

//@ContextConfiguration("/spring/applicationContext.xml")
//@RunWith(SpringJUnit4ClassRunner.class)
public class FileParse {
	private static Logger logger = LoggerFactory.getLogger(FileParse.class);
	@Resource
	private DatasMapper datasMapper;
	@Resource
	private DataMapperPlus dataMapperPlus;
	
	
	@Test
	public void readTxt(){
	    String path = "D:\\光谱数据\\吴静珠-近红外-中红外2006-2016-TXT转换\\食用油近红外北京理化中心测试2015txt\\1.0";
	    try {
	        InputStream is = new FileInputStream(path);
	        List<String> lines = CharStreams.readLines(new InputStreamReader(is));
//            List<String> lines = Files.readLines(new File(path), Charset.forName("GBK"));
            for(String line:lines){
                System.out.println(line);
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
	    
	}
	
	
	
	public void testDemo(){
		int n = 1000;
		String path = "D:\\upload\\4.CSV";
		try {
//			String json = Files.toString(new File(path), Charset.forName("GBK"));
			List<Datas> records = new ArrayList<Datas>();
			List<String> lines = Files.readLines(new File(path), Charset.forName("GBK"));
			for(int i = 0;i<n;i++){
				Datas datas = new Datas();
				datas.setData(JsonMapper.nonEmptyMapper().toJson(lines));
				datas.setCt(new Date());
				datas.setUt(new Date());
				datas.setId(UUID.randomUUID().toString());
				records.add(datas);
			}
			//dataMapperPlus.insert(records);
		} catch (Exception e) {
			logger.error(Throwables.getStackTraceAsString(e));
		}
	}
	
	@Test
	public void insert(){
		for(int i = 0;i<10;i++){
			testDemo();
		}
	}
	
	@Test
	public void readToMem(){
		String filePath = "d:\\cloudse_nj_server.2015-11-30.log";
		List<String> all = new ArrayList<String>();
		try {
			List<String> lines = Files.readLines(new File(filePath), Charsets.UTF_8);
			for(int i = 0;i<20;i++){
				all.addAll(lines);
				System.out.println(all.size());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 操作excel公式
	 */
	@Test
	public void excel(){
		String path = "d:/18.xlsx";
		String to = "d:/19.xlsx";
		File f = new File(to);
		if(f.exists()){
			f.delete();
		}
		InputStream inputStream = null;
		XSSFWorkbook workbook = null;
		try {
			OutputStream outputStream = new FileOutputStream(to);
			inputStream = new FileInputStream(path);
			workbook = new XSSFWorkbook(inputStream);
			XSSFSheet sheet = workbook.getSheetAt(0);
			/* At the end of this step, we have a worksheet with test data, that we want to write into a chart */
            /* Create a drawing canvas on the worksheet */
			XSSFDrawing xssfDrawing = sheet.createDrawingPatriarch();
			 /* Define anchor points in the worksheet to position the chart */
			XSSFClientAnchor anchor = xssfDrawing.createAnchor(0, 0, 0, 0, 0, sheet.getLastRowNum()+5, 20, sheet.getLastRowNum()+50);
			/* Create the chart object based on the anchor point */
			XSSFChart my_line_chart = xssfDrawing.createChart(anchor);
			my_line_chart.setTitle("光谱信息");
 			/* Define legends for the line chart and set the position of the legend */
			XSSFChartLegend legend = my_line_chart.getOrCreateLegend();
			legend.setPosition(LegendPosition.BOTTOM);
 			/* Create data for the chart */
			LineChartData data = my_line_chart.getChartDataFactory().createLineChartData();
            /* Define chart AXIS */
			ChartAxis bottomAxis = my_line_chart.getChartAxisFactory().createCategoryAxis(AxisPosition.BOTTOM);
			ValueAxis leftAxis = my_line_chart.getChartAxisFactory().createValueAxis(AxisPosition.LEFT);
			leftAxis.setCrosses(AxisCrosses.AUTO_ZERO);
			/* Define Data sources for the chart */
			/* Set the right cell range that contain values for the chart */
            /* Pass the worksheet and cell range address as inputs */
			/* Cell Range Address is defined as First row, last row, first column, last column */
			ChartDataSource<Number> xs = DataSources.fromNumericCellRange(sheet, new CellRangeAddress(0, sheet.getLastRowNum(), 0, 0));
			ChartDataSource<Number> ys1 = DataSources.fromNumericCellRange(sheet, new CellRangeAddress(0,sheet.getLastRowNum(), 1, 1));
//			ChartDataSource<Number> ys2 = DataSources.fromNumericCellRange(sheet, new CellRangeAddress(2, 2, 0, 4));
//			ChartDataSource<Number> ys3 = DataSources.fromNumericCellRange(sheet, new CellRangeAddress(3, 3, 0, 4));
            /* Add chart data sources as data to the chart */
			data.addSeries(xs, ys1);
//			data.addSeries(xs, ys2);
//			data.addSeries(xs, ys3);
			/* Plot the chart with the inputs from data and chart axis */
			my_line_chart.plot(data, bottomAxis, leftAxis);
//			int lastRowNum = sheet.getLastRowNum();
//			System.out.println("============================="+lastRowNum);
//			Row row = sheet.createRow(lastRowNum+1);
//
//			Cell cell = row.createCell(1);
//			cell.setCellValue("aaaaaaaaaaaaaaaaaaaaaaaaaaa");
//			cell = row.createCell(2);
//			cell.setCellFormula("sum(A1,A2)");
//			row = sheet.getRow(3735);
			workbook.write(outputStream);
            outputStream.flush();
//			System.out.println(row.getCell(1).getStringCellValue());
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
//				workbook.close();
				inputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}


	}
	
}
