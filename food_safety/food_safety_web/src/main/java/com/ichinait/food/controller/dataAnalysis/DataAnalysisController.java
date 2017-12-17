package com.ichinait.food.controller.dataAnalysis;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.common.base.Throwables;
import com.ichinait.food.cache.AnalysisCache;
import com.ichinait.food.controller.base.BaseController;
import com.ichinait.food.db.entity.Algorithm;
import com.ichinait.food.db.entity.AlgorithmParams;
import com.ichinait.food.db.entity.Datas;
import com.ichinait.food.db.entity.plus.DataPlus;
import com.ichinait.food.dto.analysis.AnalysisDTO;
import com.ichinait.food.dto.analysis.AnalysisDatasDTO;
import com.ichinait.food.dto.dataAnalysis.AlgorithmPdsDTO;
import com.ichinait.food.dto.dataAnalysis.AlgorithmSstDTO;
import com.ichinait.food.dto.dataAnalysis.ModelCalibrationResultDTO;
import com.ichinait.food.service.attachment.AttachmentService;
import com.ichinait.food.service.dataAnalysis.DataAnalysisService;
import com.ichinait.food.service.datas.DatasService;
import com.ichinait.food.util.JsonMapper;
@Controller
@RequestMapping("dataAnalysis")
public class DataAnalysisController extends BaseController<Datas, DatasService> {
    private static Logger logger = LoggerFactory.getLogger(DataAnalysisController.class);
    
    @Resource //@Resource定义一个属性--2017年6月25日10:31:52
    private DatasService datasService;
    @Resource
    private AttachmentService attachmentService;
//    @Autowired //@Autowired定义一个属性与Resource区别是通过Resource是通过name注入，Autowired是通过type注入--2017年6月25日10:31:49
//    private AlgorithmService algorithmService;
    
    @Resource
    private DataAnalysisService dataAnalysisService;
    
    @RequestMapping(value = "toDataAnalysisPage", method = RequestMethod.GET)
    public String toDataAnalysisPage(HttpServletRequest request){
        return "/dataAnalysis/dataAnalysis2";
    }
    
    //如果处理过程中有错误则跳转到错误界面
    @RequestMapping(value = "toDataAnalysisErrorPage", method = RequestMethod.GET)
    public String toDataAnalysisErrorPage(){
        return "/dataAnalysis/dataAnalysisError";
    }
    //处理成功则跳转到处理结果界面
    @RequestMapping(value = "toDataAnalysisResultPage", method = RequestMethod.POST)
    public String toDataAnalysisResultPage(){
        return "/dataAnalysis/dataAnalysisResult";
    }
    
    //toDataAnalysisPDSPage
    @RequestMapping(value = "toDataAnalysisPDSPage", method = RequestMethod.GET)
    public String toDataAnalysisPDSPage(){
        return "/dataAnalysis/dataAnalysisPDS";
    }
    
    //toDataAnalysisSSTPage
    @RequestMapping(value = "toDataAnalysisSSTPage", method = RequestMethod.GET)
    public String toDataAnalysisSSTPage(){
        return "/dataAnalysis/dataAnalysisSST";
    }
    
  //toDataAnalysisPLSCVPage
    @RequestMapping(value = "toDataAnalysisPLSCVPage", method = RequestMethod.GET)
    public String toDataAnalysisPLSCVPage(){
        return "/dataAnalysis/dataAnalysisPLSCV";
    }
    
  //toDataAnalysisSSTPage
    @RequestMapping(value = "toDataAnalysisKHLSSVMPage", method = RequestMethod.GET)
    public String toDataAnalysisKHLSSVMPage(){
        return "/dataAnalysis/dataAnalysisKHLSSVM";
    }
    /**
     * 分析数据
     *
     * @param
     * @return
     */
    @RequestMapping("analysisData")
    public String analysisData(@RequestParam(name = "params") String data, HttpServletRequest request) {
        logger.info("======================= 分析数据参数：{}", data);
        //从json到java数据
        AnalysisDTO analysisDTO = JsonMapper.nonEmptyMapper().fromJson(data, AnalysisDTO.class);

        Algorithm algorithm = new Algorithm();
        algorithm.setName(analysisDTO.getAlgorithmInfo().getAlgorithmName());
        //获得参数
        List<AlgorithmParams> paramses = analysisDTO.getAlgorithmInfo().getAlgorithmParams();
        List<AnalysisDatasDTO> analysisDatasDTOs = null;
        try {
            //处理数据
            analysisDatasDTOs = datasService.handlerDatas(analysisDTO, analysisDTO.getDataIds());
        } catch (Exception e) {
            logger.error(Throwables.getStackTraceAsString(e));
        }
        //查询分析用到的原始数据
        List<DataPlus> datas = datasService.queryDatasByIds(analysisDTO.getDataIds());
        request.setAttribute("x", datas.get(0).getX());
        request.setAttribute("y", datas.get(0).getY());
        request.setAttribute("datas", datas);
        request.setAttribute("params", paramses);
        request.setAttribute("algorithm", algorithm);
        //将分析参数与结果存放到内存中,当前时间毫秒数为key
        Long key = new DateTime().getMillis();
        Map<String, Object> analysisInfo = getAnalysisInfoMap(analysisDTO, analysisDatasDTOs);
        AnalysisCache.getInstance().getCache().put(key, analysisInfo);
        request.setAttribute("analysisDatas", JsonMapper.nonEmptyMapper().toJson(analysisDatasDTOs));
        request.setAttribute("cacheKey", key);
        return "/analysis/analysis_info";
    }
    
    @RequestMapping("dataAnalysisPDS")
    public String dataAnalysisPDS(@RequestParam(name = "pdsParams") String data, HttpServletRequest request) {
        logger.info("=========PDS====================PDS==============PDS============== 分析数据参数：{}", data);
        //从json到java数据
        //拿到数据的id，和其他参数
        //从id查询到具体数据
        //把查询到的数据传给对应方法
        AlgorithmPdsDTO pdsDTO = JsonMapper.nonEmptyMapper().fromJson(data, AlgorithmPdsDTO.class);
        System.out.println(pdsDTO);
        try {
        	ModelCalibrationResultDTO  mcrd = dataAnalysisService.pds(pdsDTO);
        	request.setAttribute("PDS_Results", JsonMapper.nonEmptyMapper().toJson(mcrd));
        	logger.debug("-----------" + JsonMapper.nonEmptyMapper().toJson(mcrd));
		} catch (Exception e) {
			logger.error(Throwables.getStackTraceAsString(e));
			e.printStackTrace();
		}
        return "/dataAnalysis/dataAnalysisResult";  
    }
    
    @RequestMapping("dataAnalysisSST")
    public String dataAnalysisSST(@RequestParam(name = "sstParams") String data, HttpServletRequest request) {
        logger.info("=========SST====================SST==============SST============== 分析数据参数：{}", data);
        //从json到java数据
        //拿到数据的id，和其他参数
        //从id查询到具体数据
        //把查询到的数据传给对应方法
        //JsonMapper.nonEmptyMapper().fromJson(data, AlgorithmSstDTO.class);
        AlgorithmSstDTO sstDTO = JsonMapper.nonEmptyMapper().fromJson(data, AlgorithmSstDTO.class);
        System.out.println(sstDTO);
        try {
        	ModelCalibrationResultDTO  mcrd = dataAnalysisService.sst(sstDTO);
        	request.setAttribute("SST_Results", JsonMapper.nonEmptyMapper().toJson(mcrd));
		} catch (Exception e) {
			logger.error(Throwables.getStackTraceAsString(e));
			e.printStackTrace();
		}
        return "/dataAnalysis/dataAnalysisResult";  
    }
    
    private Map<String, Object> getAnalysisInfoMap(AnalysisDTO analysisDTO, List<AnalysisDatasDTO> analysisDatasDTOs) {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * 保存分析后的数据
     *
     * @param cacheKey
     * @return
     */
    @RequestMapping("save")
    public String saveAnalysis(Long cacheKey) {

        dataAnalysisService.saveDataAnalysisData(cacheKey);
        return "/dataAnalysis/dataAnalysisResult";
    }
    
    
    
}
