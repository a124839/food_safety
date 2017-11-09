package com.ichinait.food.service.dataAnalysis;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.Vector;

import javax.annotation.Resource;

import org.apache.commons.beanutils.converters.DoubleArrayConverter;
import org.apache.commons.beanutils.converters.DoubleConverter;
import org.apache.ibatis.javassist.expr.NewArray;
import org.apache.shiro.SecurityUtils;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.fasterxml.jackson.databind.JavaType;
import com.google.common.base.Throwables;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.ichinait.food.cache.AnalysisCache;
import com.ichinait.food.constant.Constant;
import com.ichinait.food.db.entity.AlgorithmWithBLOBs;
import com.ichinait.food.db.entity.Analysis;
import com.ichinait.food.db.entity.Datas;
import com.ichinait.food.db.entity.User;
import com.ichinait.food.db.entity.plus.AlgorithmPDS;
import com.ichinait.food.db.mapper.plus.DataMapperPlus;
import com.ichinait.food.dto.analysis.AnalysisDTO;
import com.ichinait.food.dto.analysis.AnalysisDatasDTO;
import com.ichinait.food.dto.analysis.AnalysisDatasPlusDTO;
import com.ichinait.food.dto.data.ValuesDTO;
import com.ichinait.food.dto.dataAnalysis.AlgorithmPdsDTO;
import com.ichinait.food.dto.dataAnalysis.AlgorithmSstDTO;
import com.ichinait.food.dto.dataAnalysis.ModelCalibrationResultDTO;
import com.ichinait.food.exception.BusinessException;
import com.ichinait.food.exception.ErrorMessage;
import com.ichinait.food.util.JsonMapper;
import com.mathworks.toolbox.javabuilder.MWClassID;
import com.mathworks.toolbox.javabuilder.MWException;
import com.mathworks.toolbox.javabuilder.MWNumericArray;

import PDS.PDS;
import sst.SST;

/**
 * @author k570
 * 2017年7月9日16:20:35
 */

@Service
@Transactional(isolation = Isolation.READ_COMMITTED)
public class DataAnalysisService {
	private static org.slf4j.Logger logger = LoggerFactory.getLogger(DataAnalysisService.class);
	@Resource
	private DataMapperPlus dataMapperPlus;
	
    //保存分析后的数据
    public void saveDataAnalysisData(Long cacheKey) {
        // TODO Auto-generated method stub
        
    }
    
    
	//PDS算法用于模型转移:[Xc_after]= pds_ZJ(Xm,Xs,Xc,wleft,wright,numcomp,flag)
	//Xm为主机标准噗;Xc为子机标准谱;Xs为子机待测谱,Xm主机光谱，Xs从机光谱，Xc从机预测集
	//wleft、wright分别为左右窗口数；numcomp为因子数/主成分数，对于多元线性回归无意义，但是也要输入一个数字
	//flag可以为1、2、3，分别代表多元线性回归，主成分回归、偏最小二乘回归
	//使用double类型是图省事，int类型就够			
	//SST算法用于模型转移:[ Xc_after ] = sst( Xm,Xs,Xc,n_comp )
	//n_comp使用的主成分数
	//double类型图省事			
	//PLSCV建模算法:[Ycv,Tcv,RMSEcv,R2cv]= plscv(x,y,vl,da)
	//x (samples x descriptors)  for cross-validation
	//y (samples x variables)    for regression or
	//y (samples x classes)   for discriminant analysis. Classes numbers must be >0.
	//vl (1 x 1) number of latent variables to compute in cross-validation
	//da' (char) to indicate PLS-discriminant analysis (in PLS regression it is no used)			
	//KHLSSVM建模算法:
	//[R2,RMSECV,model] = KHLssvm(NR,NK,MI,UBg,LBg,UBc,LBc,
	//                           trainset,trainlabel,testset,
	//                           testlabel)
	//NR：kh优化lssvm的运行次数
	//NK：kh算法中磷虾个数
	//MI：kh算法中最大迭代次数
	//UBg，UBc：lssvm（svm）中gam，sig2的最大的gam，sig2的值
	//LBc，LBg：lssvm（svm）中gam，sig2的最小gam，sig2的值
	//trainset：训练集
	//trainlabel：训练集标签
	//testset：测试集
	//testlabel：测试集标签			
    
    
    /**
     * 处理系统自带算法产生的数据
     * @param result
     * @return
     * @throws Exception
     */
    private AnalysisDatasDTO createAnalysisDatasDTO(double[][] result, List<ValuesDTO> dtos) throws  Exception{
        AnalysisDatasDTO analysisDatasDTO = getAnalysisDatasDTO();
        double[] x = onlyGetX(dtos);
        if(result.length>0){
        	
//            for(double[][] d:result){
//                analysisDatasDTO.getxDatas().add(d[0]);
//                analysisDatasDTO.getyDatas().add(d[1]);
//            }
        }
        return analysisDatasDTO;
    }
    
    
    /**AlgorithmPdsDTO [xmdataIds=[b4e4fff4-652d-4f33-a9bc-5a6ca24953fe], 
     * xsdataIds=[d19d82e0-7c63-43fd-a90e-ab5725abf130], 
     * xcdataIds=[b4e4fff4-652d-4f33-a9bc-5a6ca24953fe, 096c276f-f7ee-4104-b186-7d8cfe87f27e, 
     * b66dd627-48df-4807-80d7-e36af54f4611, d19d82e0-7c63-43fd-a90e-ab5725abf130, 7ecfe87f-c458-41cd-a17e-bd5e6f6e1b6e], 
     * xm=null, xs=null, xc=null, wleft=56, wright=23, numcomp=12]
     * @return
     */
    //PDS算法用于模型转移:[Xc_after]= pds_ZJ(Xm,Xs,Xc,wleft,wright,numcomp,flag)
	//Xm为主机标准噗;Xc为子机标准谱;Xs为子机待测谱,Xm主机光谱，Xs从机光谱，Xc从机预测集
	//wleft、wright分别为左右窗口数；numcomp为因子数/主成分数，对于多元线性回归无意义，但是也要输入一个数字
	//flag可以为1、2、3，分别代表多元线性回归，主成分回归、偏最小二乘回归
	//使用double类型是图省事，int类型就够

    
    //每个算法法对应一个方法遇到java.lang.UnsatisfiedLinkError: Native Library 
//    C:\Program Files\MATLAB\MATLAB Runtime\v90\bin\win64\
//    BuilderJABootstrap.dll already loaded in another classloader
    //这个问题。
    
    
    /**
     * 2017年8月12日上午9:11:02
     * @param algorithmPdsDTO
     * @return
     */
    public ModelCalibrationResultDTO pds(AlgorithmPdsDTO algorithmPdsDTO){
    	//结果类
    	ModelCalibrationResultDTO mcrd = new ModelCalibrationResultDTO();
    	AnalysisDatasDTO analysisDatasDTO = null;
    	List<String> xmDataIds = algorithmPdsDTO.getXmdataIds();
    	List<String> xsDataIds = algorithmPdsDTO.getXsdataIds();
    	List<String> xcDataIds = algorithmPdsDTO.getXcdataIds();  
    	
    	Integer wleft = algorithmPdsDTO.getWleft();
    	Integer wright = algorithmPdsDTO.getWright();
    	Integer numcomp = algorithmPdsDTO.getNumcomp();    	
    	//转换成二维数据
    	
    	double[][] xm = getCorrespondedData(xmDataIds);
    	double[][] xs = getCorrespondedData(xsDataIds);
    	double[][] xc = getCorrespondedData(xcDataIds);
    	if (xm == null || xs == null || xc == null) {
			throw new BusinessException("ModelCalibrationResultDTO com.ichinait.food.service.dataAnalysis."
					+ "DataAnalysisService.pds(AlgorithmPdsDTO algorithmPdsDTO)."
					+ "getCorrespondedData(xmDataIds)", "二维数据转换出错");
		}
    	//valuesDTO=[x:-20.33178,y:0.0, x:-19.36755,y:0.0, x:-18.40331,y:0.0, x:-17....]
    	//转换成matlab数组
    	MWNumericArray xmM = new MWNumericArray(xm, MWClassID.DOUBLE);
    	MWNumericArray xsM = new MWNumericArray(xs, MWClassID.DOUBLE);
    	MWNumericArray xcM = new MWNumericArray(xc, MWClassID.DOUBLE);
    	//调用matlab方法
    	try {
			PDS pds = new PDS();
			//返回obj数组对象
			Object[] obj = pds.pds_ZJ(1, xmM, xsM, xcM, wleft, wright, numcomp, 1);
			Object[] obj2 = pds.pds_ZJ(1, xm, xs, xc, wleft, wright, numcomp, 1);
			//obj再转换成java二维对象
			MWNumericArray temp = (MWNumericArray)obj[0];
			MWNumericArray temp2 = (MWNumericArray)obj2[0];
			double[][] d = (double[][]) temp.toDoubleArray();
			double[][] d2 = (double[][]) temp.toDoubleArray();
//			analysisDatasDTO = createAnalysisDatasDTO(d);
			if (d != null) {
				mcrd.setResult(d);
			}
			logger.info("===========Result======", d.toString());
			logger.info("===========Result=22=====", d2.toString());
		} catch (MWException e) {
			e.printStackTrace();
		} catch (Throwable e1) {
			e1.printStackTrace();
		}
    	//转换为DTO前台可用数据
    	//返回对应DTO  
//    	logger.info(mcrd.toString());
    	System.out.println("=================mcrd=======" + mcrd);
        return mcrd;
    }
    
    //SST算法
    public ModelCalibrationResultDTO sst(AlgorithmSstDTO sstDTO) {
    	// TODO Auto-generated method stub
    	ModelCalibrationResultDTO mcrd = new ModelCalibrationResultDTO();
    	AnalysisDatasDTO analysisDatasDTO = null;
    	List<String> xmDataIds = sstDTO.getXmdataIds();
    	List<String> xsDataIds = sstDTO.getXsdataIds();
    	List<String> xcDataIds = sstDTO.getXcdataIds();  
    	
    	Integer numcomp = sstDTO.getNumcomp();    	
    	//转换成二维数据
    	
    	double[][] xm = getCorrespondedData(xmDataIds);
    	double[][] xs = getCorrespondedData(xsDataIds);
    	double[][] xc = getCorrespondedData(xcDataIds);
    	if (xm == null || xs == null || xc == null) {
			throw new BusinessException("ModelCalibrationResultDTO com.ichinait.food.service.dataAnalysis."
					+ "DataAnalysisService.pds(AlgorithmPdsDTO algorithmPdsDTO)."
					+ "getCorrespondedData(xmDataIds)", "二维数据转换出错");
		}
    	//valuesDTO=[x:-20.33178,y:0.0, x:-19.36755,y:0.0, x:-18.40331,y:0.0, x:-17....]
    	//转换成matlab数组
    	MWNumericArray xmM = new MWNumericArray(xm, MWClassID.DOUBLE);
    	MWNumericArray xsM = new MWNumericArray(xs, MWClassID.DOUBLE);
    	MWNumericArray xcM = new MWNumericArray(xc, MWClassID.DOUBLE);
    	//调用matlab方法
    	try {
			SST sstMatlab = new SST();
			//Object[] obj = sstMatlab.sst(1, xmM, xsM, xcM, numcomp);
			Object[] obj = sstMatlab.sst(1, xm, xs, xc, numcomp);
			//obj再转换成java二维对象
			MWNumericArray temp = (MWNumericArray)obj[0];
			double[][] d = (double[][]) temp.toDoubleArray();
			if (d != null) {
				mcrd.setResult(d);
				logger.info("===========如果你看到这条说明d不为空======", d.toString());
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
    	if (mcrd != null) {
    		logger.info("----------------如果你看到这条说明mcrd不为空------", mcrd.toString());
		}
    	return mcrd;
    }
       
    
    //根据id拿到对应数据
    /**根据dataId拿到对应的Y的数据并把它们合并为一个Y矩阵
     * @param ids
     * @return
     */
    public double[][] getCorrespondedData(List<String> ids) {
    	JavaType type = JsonMapper.nonEmptyMapper().constructCollectionType(List.class,ValuesDTO.class);
    	//拿到数据类型集合
    	List<Datas> datas = dataMapperPlus.selectDatasByIds(ids);
//    	List<Double[]> xList = Lists.newArrayList();
    	List<ValuesDTO> v = JsonMapper.nonEmptyMapper().fromJson(datas.get(0).getData(),type);
    	double[][] ary = new double[datas.size()][v.size()]; 
    	//根据数据类型集合的得到数据		
		try {
			for (int i = 0; i < datas.size(); i++) {
				List<ValuesDTO> valuesDTOs = JsonMapper.nonEmptyMapper().fromJson(datas.get(i).getData(),type);
				//对每个包含x，y的数据做合并（合并所有的y）
				double[]  xArray = new double[valuesDTOs.size()];
				for(int j = 0;j<valuesDTOs.size();j++){
			       ValuesDTO dto = valuesDTOs.get(j);
			       double y = dto.getY();
			       ary[i][j] = y;
			       xArray[j] = y;
//			       System.out.println("======="+ i + "=====" + ary[i][j]);
			       logger.info("============", ary[i][j] + "++++++"+i );
			    }
			}			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error(Throwables.getStackTraceAsString(e));
			e.printStackTrace();
		}
		return ary;
	}
    
    /** 只得到X的值，对于所有的光谱data数据来说，一般的同一仪器采集的光谱波长相同
     * 所以只用一组的数据就可以得到波长X
     * @param dtos
     * @return
     */
    private double[] onlyGetX(List<ValuesDTO> dtos){
    	double[] arys = new double[dtos.size()];
    	for(int i = 0;i<dtos.size();i++){
            ValuesDTO dto = dtos.get(i);
            double x = dto.getY();            
            arys[i]=x;
        }
        return arys;
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
    
  
   
   
   
   private double[][] getDoubleAry(List<ValuesDTO> dtos){
       double[][] arys = new double[dtos.size()][2];
       for(int i = 0;i<dtos.size();i++){
           ValuesDTO dto = dtos.get(i);
           double x = dto.getX();
           double y = dto.getY();
           arys[i][0] = x;
           arys[i][1]=y;
       }
       return arys;
   }

   //转置矩阵
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



   /**
    * 保存分析数据
    * @param cacheKey
    */
//   public void saveAlgorithmData(Long cacheKey){
//       Map<String,Object> cache = AnalysisCache.getInstance().getCache().get(cacheKey);
//       AnalysisDTO analysisDTO = (AnalysisDTO)cache.get(AnalysisCache.PARAM_KEY);
//       List<AnalysisDatasDTO> analysisDatasDTOs = (List<AnalysisDatasDTO>) cache.get(AnalysisCache.RESULT_KEY);
//       logger.info("保存分析结果:{}", JsonMapper.nonEmptyMapper().toJson(analysisDTO));
//       String projectId = null;
//       if(analysisDTO.getAnalysisDatasPlusDTOs() == null){//没有导入数据
//           List<AnalysisDatasPlusDTO> analysisDatasPlusDTOs = Lists.newArrayList();
//           List<Datas> datases = dataMapperPlus.selectDatasByIds(analysisDTO.getDataIds());
//           projectId = datases.get(0).getProjectId();
//           JavaType javaType = JsonMapper.nonEmptyMapper().constructCollectionType(List.class, ValuesDTO.class);
//           for(AnalysisDatasDTO dto:analysisDatasDTOs){
//               AnalysisDatasPlusDTO analysisDatasPlusDTO =  new AnalysisDatasPlusDTO();
//               analysisDatasPlusDTO.setId(dto.getDataId());
//               List<ValuesDTO> valuesDTOs = Lists.newArrayList();
//               List<Double> xDoubles = dto.getxDatas();
//               List<Double> yDoubles = dto.getyDatas();
//               for(int i = 0;i<xDoubles.size();i++){
//                   ValuesDTO valuesDTO = new ValuesDTO();
//                   valuesDTO.setX(xDoubles.get(i));
//                   valuesDTO.setY(yDoubles.get(i));
//                   valuesDTOs.add(valuesDTO);
//               }
//               analysisDatasPlusDTO.setValuesDTOs(valuesDTOs);
//               analysisDatasPlusDTOs.add(analysisDatasPlusDTO);
//           }
//           analysisDTO.setAnalysisDatasPlusDTOs(analysisDatasPlusDTOs);
//       }else{
//           String dataId = analysisDTO.getDataIds().get(0);
//           projectId = datasService.queryDatasDetail(dataId).getProjectId();
//       }
//       User user = (User) SecurityUtils.getSubject().getSession().getAttribute("user");
//       Analysis analysis = new Analysis();
//       analysis.setId(UUID.randomUUID().toString());
//       analysis.setProjectId(projectId);
//       analysis.setAlgorithmInfo(JsonMapper.nonEmptyMapper().toJson(analysisDTO.getAlgorithmInfo()));
//       analysis.setDatasInfo(JsonMapper.nonEmptyMapper().toJson(analysisDTO.getAnalysisDatasPlusDTOs()));
//       analysis.setOperator(user.getId());
//       analysis.setCt(new Date());
//       analysis.setUt(new Date());
//       analysisMapper.insert(analysis);
//       //保存后，从内存中移除
//       AnalysisCache.getInstance().getCache().remove(cacheKey);
//   }
   
  
}
