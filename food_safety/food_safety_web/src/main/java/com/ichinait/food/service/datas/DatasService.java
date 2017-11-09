package com.ichinait.food.service.datas;

import MSC.MSCMethod;
import com.fasterxml.jackson.databind.JavaType;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.io.CharStreams;
import com.google.common.io.Files;
import com.ichinait.food.constant.Constant;
import com.ichinait.food.db.entity.*;
import com.ichinait.food.db.entity.plus.DataPlus;
import com.ichinait.food.db.mapper.*;
import com.ichinait.food.db.mapper.plus.DataMapperPlus;
import com.ichinait.food.dto.analysis.AnalysisDTO;
import com.ichinait.food.dto.analysis.AnalysisDatasDTO;
import com.ichinait.food.dto.data.DataDTO;
import com.ichinait.food.dto.data.ValuesDTO;
import com.ichinait.food.util.JsonMapper;
import com.ichinait.food.util.PropertiesLoader;
import com.mathworks.toolbox.javabuilder.MWClassID;
import com.mathworks.toolbox.javabuilder.MWNumericArray;
import deriv.Deriv;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;
import scale.Scale;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


@Service
@Transactional(isolation=Isolation.READ_COMMITTED,propagation=Propagation.REQUIRED)
public class DatasService {
	private static Logger logger = LoggerFactory.getLogger(DatasService.class);
	private JsonMapper mapper = new JsonMapper();
	
	@Resource
	private DataMapperPlus dataMapperPlus;
	@Resource
	private DatasMapper datasMapper;
	@Resource
	private AttachmentMapper attachmentMapper;
	@Resource
	private AlgorithmMapper algorithmMapper;
    @Resource
    private AlgorithmParamsMapper algorithmParamsMapper;
    @Resource
    private SampleMapper sampleMapper;
	
	
	public PageInfo<DataPlus> queryDataByConditions(int curror,int pageSize,DataDTO dto){
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("samplesId", dto.getSamplesId());
		params.put("instrumentId", dto.getInstrumentId());
		params.put("projectName", dto.getProjectName());
		PageHelper.startPage(curror, pageSize);
		
		List<DataPlus> datas = dataMapperPlus.selectDataByConditions(params);
		PageInfo<DataPlus> pageInfo = new PageInfo<DataPlus>(datas);
		return pageInfo;
	}

	/**
	 * 保存上传的文件
	 * @param files
	 * @param uploader
	 * @param projectId
	 * @param instrumentId
	 * @throws IOException
	 * @return 错误的文件名
     */
	public List<String> saveFile(List<MultipartFile> files,String uploader,String projectId,String instrumentId,String sampleId,String wavelengthRange,String resolution,int scanningTimes,String scanningDuration) throws IOException{
	    List<String> errorFiles = Lists.newArrayList();
		PropertiesLoader loader = new PropertiesLoader("/application.properties");
        String path = loader.getProperty(Constant.PROPER_KEY_UPLOAD_PATH)+projectId+File.separator+instrumentId+File.separator;

        for(MultipartFile file:files){
            String fileOriginalName = file.getOriginalFilename();//文件的真实名字
            //通过样品编号查找样品
            SampleExample example = new SampleExample();
			String sampleCode = fileOriginalName.substring(0,fileOriginalName.lastIndexOf("_"));
            example.createCriteria().andCodeEqualTo(sampleCode);
            List<Sample> samples = sampleMapper.selectByExample(example);
            if(CollectionUtils.isEmpty(samples) || samples.size()>1){
               errorFiles.add(fileOriginalName);
            }else{
                sampleId = samples.get(0).getId();
                long fileSize = file.getSize()/1024;
                File p = new File(path);
                if(!p.exists()){
                    p.mkdirs();
                }
                int dotIndex = fileOriginalName.lastIndexOf('.');
                String type = (dotIndex == -1) ? "" : fileOriginalName.substring(dotIndex + 1);
                String fileName = uploader+'-'+new Date().getTime();//文件保存的名字
                File to = new File(path+fileName+"."+type);
                logger.info("文件保存路径：{}",to.getPath());
                Files.write(file.getBytes(), to);//保存本地
                List<String> lines = CharStreams.readLines(new InputStreamReader(file.getInputStream()));
                List<ValuesDTO> values = handlerData(lines);
                String content = mapper.toJson(values);
                String attachmentId = UUID.randomUUID().toString();
                Attachment attachment = new Attachment();
                attachment.setId(attachmentId);
                attachment.setFileName(fileName);
                attachment.setFileOriginalName(fileOriginalName);
                attachment.setCt(new Date());
                attachment.setFileSize(fileSize);
                attachment.setFileType(type);
                attachment.setUploader(uploader);
                attachment.setUrl(projectId+"/"+instrumentId+"/"+fileName+"."+type);
                attachment.setUt(new Date());
                attachmentMapper.insert(attachment);
                Datas datas = new Datas();
                datas.setAttachmentId(attachmentId);
                datas.setData(content);
                datas.setProjectId(projectId);
                datas.setInstrumentId(instrumentId);
                datas.setSampleId(sampleId);
                datas.setCt(new Date());
                datas.setUt(new Date());
                datas.setId(UUID.randomUUID().toString());
                datas.setResolution(resolution);
                datas.setWavelengthRange(wavelengthRange);
                datas.setScanningDuration(scanningDuration);
                datas.setScanningTimes(scanningTimes);
                datasMapper.insertSelective(datas);
            }
        }
		return errorFiles;
	}

	public List<ValuesDTO> handlerData(List<String> lines){
		List<ValuesDTO> values = Lists.newArrayList();
		for(String s:lines){
			ValuesDTO d = new ValuesDTO();
			String[] ary = s.split(",");
			d.setX(Double.valueOf(ary[0]));
			d.setY(Double.parseDouble(ary[1]));
			values.add(d);
		}
		return values;
	}


	public boolean deleteDatas(String id){
		Datas datas  = datasMapper.selectByPrimaryKey(id);
		if(datas != null){
			Attachment attachment = attachmentMapper.selectByPrimaryKey(datas.getAttachmentId());
			PropertiesLoader loader = new PropertiesLoader("/application.properties");
			String path = loader.getProperty(Constant.PROPER_KEY_UPLOAD_PATH);
			File f = new File(path+attachment.getUrl());
			if(f.exists()){
				f.delete();
			}
		}
		return datasMapper.deleteByPrimaryKey(id)>0 && attachmentMapper.deleteByPrimaryKey(datas.getAttachmentId())>0;
	}


	public Datas queryDatasDetail(String id){
		return datasMapper.selectByPrimaryKey(id);
	}


	public List<DataPlus> queryDatasByIds(List<String> ids){
		return dataMapperPlus.selectDatasPlusByIds(ids);
	}
	

	/**
	 * 调用算法 并将结果返回
	 * @param ids
	 * @return
     */

	public List<AnalysisDatasDTO> handlerDatas(AnalysisDTO analysisDTO, List<String> ids) throws Exception{
		List<Datas> datas = dataMapperPlus.selectDatasByIds(ids);
		List<AnalysisDatasDTO> analysisDatasDTOs = null;
		JavaType type = JsonMapper.nonEmptyMapper().constructCollectionType(List.class,ValuesDTO.class);
		String algorithmId = analysisDTO.getAlgorithmInfo().getId();
		AlgorithmWithBLOBs algorithmWithBLOBs = algorithmMapper.selectByPrimaryKey(algorithmId);
//		for(Datas d:datas){
//			List<ValuesDTO> valuesDTOs = JsonMapper.nonEmptyMapper().fromJson(d.getData(),type);
//            AnalysisDatasDTO analysisDatasDTO = null;
//            if(algorithmWithBLOBs.getCategory() != Constant.CUSTOMER_ALGORITHM){//msc
//				double[][] result = getResult(valuesDTOs,algorithmWithBLOBs);
//				analysisDatasDTO = createAnalysisDatasDTO(result);
//				analysisDatasDTO.setDataId(d.getId());
//
//            }else{
//                analysisDatasDTO = createAnalysisDatasDTO(d.getId(),valuesDTOs);
//            }
//			analysisDatasDTOs.add(analysisDatasDTO);
//		}
		if(algorithmWithBLOBs.getCategory() != Constant.CUSTOMER_ALGORITHM){
			analysisDatasDTOs = getResult(datas,algorithmWithBLOBs);
		}else{
			analysisDatasDTOs = createAnalysisDatasDTO(datas);
		}

		return analysisDatasDTOs;
	}

    /**
     * 处理系统自带算法产生的数据
     * @param result
     * @return
     * @throws Exception
     */
    private AnalysisDatasDTO createAnalysisDatasDTO(double[][] result) throws  Exception{
        AnalysisDatasDTO analysisDatasDTO = getAnalysisDatasDTO();
        if(result.length>0){
			for(double[] d:result){
                analysisDatasDTO.getxDatas().add(d[0]);
                analysisDatasDTO.getyDatas().add(d[1]);
            }
        }
        return analysisDatasDTO;
    }


    /**
     * 处理导入自定义算法的数据
     * @param valuesDTOs
     * @return
     * @throws Exception
     */
    public AnalysisDatasDTO createAnalysisDatasDTO(String dataId,List<ValuesDTO> valuesDTOs) throws  Exception{
        AnalysisDatasDTO analysisDatasDTO = getAnalysisDatasDTO();
        if(!CollectionUtils.isEmpty(valuesDTOs)){
            for (ValuesDTO dto:valuesDTOs){
                analysisDatasDTO.getxDatas().add(dto.getX());
                analysisDatasDTO.getyDatas().add(dto.getY());
            }
        }
        analysisDatasDTO.setDataId(dataId);
        return analysisDatasDTO;
    }


	/**
	 *
	 * @param datas
	 * @return
	 * @throws Exception
     */
	public List<AnalysisDatasDTO> createAnalysisDatasDTO(List<Datas> datas) throws  Exception{
		List<AnalysisDatasDTO> analysisDatasDTOs = Lists.newArrayList();
		JavaType type = JsonMapper.nonEmptyMapper().constructCollectionType(List.class,ValuesDTO.class);
		for(Datas d:datas){
			List<ValuesDTO> valuesDTOs = JsonMapper.nonEmptyMapper().fromJson(d.getData(),type);
			AnalysisDatasDTO analysisDatasDTO = getAnalysisDatasDTO();
			if(!CollectionUtils.isEmpty(valuesDTOs)){
				for (ValuesDTO dto:valuesDTOs){
					analysisDatasDTO.getxDatas().add(dto.getX());
					analysisDatasDTO.getyDatas().add(dto.getY());
				}
			}
			analysisDatasDTO.setDataId(d.getId());
			analysisDatasDTOs.add(analysisDatasDTO);
		}
		return analysisDatasDTOs;
	}

    /**
     * 生成存放分析数据的DTO
     * @return AnalysisDatasDTO
     */
    private AnalysisDatasDTO getAnalysisDatasDTO(){
        AnalysisDatasDTO analysisDatasDTO = new AnalysisDatasDTO();
        List<Double> xDatas = Lists.newArrayList();
        List<Double> yDatas = Lists.newArrayList();
        analysisDatasDTO.setxDatas(xDatas);
        analysisDatasDTO.setyDatas(yDatas);
        return analysisDatasDTO;
    }


	public List<DataPlus> queryDatasNoPage(DataDTO dto){
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("samplesId", dto.getSamplesId());
		params.put("instrumentId", dto.getInstrumentId());
		params.put("projectName", dto.getProjectName());
		List<DataPlus> datas = dataMapperPlus.selectDataByConditions(params);
		return datas;
	}


	public List<Datas> queryDatasByProjectId(String projectId){
		DatasExample example = new DatasExample();
		example.createCriteria().andProjectIdEqualTo(projectId);
		return datasMapper.selectByExampleWithBLOBs(example);
	}


    /**
	 *
     * 调用msc算法 返回算法结果
     * @param dtos
     * @return
     * @throws Exception
     */
//	private double[][] getMscResult(List<ValuesDTO> dtos, List<AlgorithmParams> params) throws  Exception{
//		double[][] arys = getDoubleAry(dtos);
//		List<double[]> list = Lists.newArrayList();
//
//		MWNumericArray x = new MWNumericArray(arys, MWClassID.DOUBLE);
//
//		MSCMethod mscMethod = new MSCMethod();
//        double first = 1;
//        double last = 1;
//        for(AlgorithmParams d:params){
//            if(d.getName().equals("first")){
//                first = d.getValue();
//            }
//            if(d.getName().equals("last")){
//                last = d.getValue();
//            }
//        }
//		Object[] obj = mscMethod.MSC(2,x,first,last);
//		MWNumericArray temp = (MWNumericArray)obj[0];
//		MWNumericArray temp1 = (MWNumericArray)obj[1];
//		double[][] d = (double[][]) temp.toDoubleArray();
//		double[][] d1 = (double[][]) temp1.toDoubleArray();
//		return d;
//	}

	/**
	 * 调用算法，返回结果
	 * @param datas
	 * @param algorithmWithBLOBs
	 * @return
	 * @throws Exception
     */
//	private double[][] getResult(List<ValuesDTO> dtos, AlgorithmWithBLOBs algorithmWithBLOBs) throws  Exception{
//		double[][] arys = getDoubleAry(dtos);
//		List<double[]> list = Lists.newArrayList();
//		MWNumericArray x = new MWNumericArray(arys, MWClassID.DOUBLE);
//		Object[] obj = null;
//		if(algorithmWithBLOBs.getName().equals(Constant.MSC)){
//			AlgorithmParamsExample example = new AlgorithmParamsExample();
//			example.createCriteria().andAlgorithmIdEqualTo(algorithmWithBLOBs.getId());
//			List<AlgorithmParams> params = algorithmParamsMapper.selectByExample(example);
//			double first = 1;
//			double last = 1;
//			for(AlgorithmParams d:params){
//				if(d.getName().equals("first")){
//					first = d.getValue();
//				}
//				if(d.getName().equals("last")){
//					last = d.getValue();
//				}
//			}
//			MSCMethod mscMethod = new MSCMethod();
//			obj = mscMethod.MSC(2,x,first,last);
//		}else if(algorithmWithBLOBs.getName().equals(Constant.SCALE)){
//			AlgorithmParamsExample example = new AlgorithmParamsExample();
//			example.createCriteria().andAlgorithmIdEqualTo(algorithmWithBLOBs.getId());
//			List<AlgorithmParams> params = algorithmParamsMapper.selectByExample(example);
//			double scaltype = 1.0;
//			for(AlgorithmParams d:params){
//				if(d.getName().equals("scaltype")){
//					scaltype = d.getValue();
//				}
//			}
//			Scale scale = new Scale();
//			obj = scale.scale(2,x,scaltype);
//		}
//
//		MWNumericArray temp = (MWNumericArray)obj[0];
//		MWNumericArray temp1 = (MWNumericArray)obj[1];
//		double[][] d = (double[][]) temp.toDoubleArray();
//		double[][] d1 = (double[][]) temp1.toDoubleArray();
//		return d;
//	}
	private List<AnalysisDatasDTO> getResult(List<Datas> datas, AlgorithmWithBLOBs algorithmWithBLOBs) throws  Exception{
		List<AnalysisDatasDTO> analysisDatasDTOs = Lists.newArrayList();
		JavaType type = JsonMapper.nonEmptyMapper().constructCollectionType(List.class,ValuesDTO.class);
		AlgorithmParamsExample example = new AlgorithmParamsExample();
		example.createCriteria().andAlgorithmIdEqualTo(algorithmWithBLOBs.getId());
		List<AlgorithmParams> params = algorithmParamsMapper.selectByExample(example);
		AnalysisDatasDTO dto = null;
		for(Datas data:datas){
//			List<double[]> list = Lists.newArrayList();
			List<Double> xList = Lists.newArrayList();
			Object[] obj = null;
			double[][] arys = null;
			List<ValuesDTO> valuesDTOs = JsonMapper.nonEmptyMapper().fromJson(data.getData(),type);
			if(algorithmWithBLOBs.getName().equals(Constant.DERIV)){
				Map<String, Object> result = this.getDoubleAryColToRow(valuesDTOs);
				arys = (double[][]) result.get("arys");
				xList = (List<Double>) result.get("xList");
			} else {
				arys = this.getDoubleAry(valuesDTOs);
			}
			
			MWNumericArray x = new MWNumericArray(arys, MWClassID.DOUBLE);
			if(algorithmWithBLOBs.getName().equals(Constant.MSC)){

				double first = 1;
				double last = 1;
				for(AlgorithmParams d:params){
					if(d.getName().equals("first")){
						first = d.getValue();
					}
					if(d.getName().equals("last")){
						last = d.getValue();
					}
				}
				MSCMethod mscMethod = new MSCMethod();
				obj = mscMethod.MSC(2,x,first,last);
			}else if(algorithmWithBLOBs.getName().equals(Constant.SCALE)){
				double scaltype = 1.0;
				for(AlgorithmParams d:params){
					if(d.getName().equals("scaltype")){
						scaltype = d.getValue();
					}
				}
				Scale scale = new Scale();
				obj = scale.scale(2,x,scaltype);
			}else if(algorithmWithBLOBs.getName().equals(Constant.DERIV)){
				double der = 3.0;
				double window = 5.0;
				double order = 4.0;
				for(AlgorithmParams param : params){
					if(param.getName().equals("der")){
						der = param.getValue();
					}
					if(param.getName().equals("window")){
						window = param.getValue();
					}
					if(param.getName().equals("order")){
						order = param.getValue();
					}
				}
				Deriv deriv = new Deriv();
				obj = deriv.deriv(1, x, der, window, order);

			}

			MWNumericArray temp = (MWNumericArray)obj[0];
//			MWNumericArray temp1 = (MWNumericArray)obj[1];
			double[][] d = (double[][]) temp.toDoubleArray();
//			double[][] d1 = (double[][]) temp1.toDoubleArray();
			if(algorithmWithBLOBs.getName().equals(Constant.DERIV)){
				dto = createAnalysisDatasDTOForDeriv(d, xList);
			} else {
				dto = createAnalysisDatasDTO(d);
			}
			dto.setDataId(data.getId());
			analysisDatasDTOs.add(dto);
		}

		return analysisDatasDTOs;
	}
	private double[][] getDoubleAry(List<ValuesDTO> dtos){
		double[][] arys = new double[dtos.size()][2];
		for(int i = 0;i<dtos.size();i++){
			ValuesDTO dto = dtos.get(i);
			double x = dto.getX();
			double y = dto.getY();
//			double[] ary = {x,y};
//			arys[i] = ary;
			arys[i][0] = x;
			arys[i][1]=y;
		}
		return arys;
	}


	private Map<String, Object> getDoubleAryColToRow(List<ValuesDTO> dtos){
		Map<String, Object> result = Maps.newHashMap();
		List<Double> xList = Lists.newArrayList();
		double[][] arys = new double[1][dtos.size()];
		for(int i = 0;i<dtos.size();i++){
			ValuesDTO dto = dtos.get(i);
			xList.add(dto.getX());
			double y = dto.getY();
			arys[0][i] = y;
		}
		result.put("xList",xList);
		result.put("arys", arys);

		return result;
	}


	private AnalysisDatasDTO createAnalysisDatasDTOForDeriv(double[][] result, List<Double> xList) throws  Exception{
		AnalysisDatasDTO analysisDatasDTO = getAnalysisDatasDTO();
		if(result.length>0){
			for(int i=0; i<result[0].length; i++){
				analysisDatasDTO.getxDatas().add(xList.get(i));
				analysisDatasDTO.getyDatas().add(result[0][i]);
			}
		}
		return analysisDatasDTO;
	}
}


