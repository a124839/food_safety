package com.ichinait.food.controller.analysis;

import com.fasterxml.jackson.databind.JavaType;
import com.github.pagehelper.PageInfo;
import com.google.common.base.Throwables;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.io.CharStreams;
import com.ichinait.food.cache.AnalysisCache;
import com.ichinait.food.constant.Constant;
import com.ichinait.food.controller.base.BaseController;
import com.ichinait.food.db.entity.*;
import com.ichinait.food.db.entity.plus.AnalysisPlus;
import com.ichinait.food.db.entity.plus.DataPlus;
import com.ichinait.food.dto.algorithm.AlgorithmAddDTO;
import com.ichinait.food.dto.analysis.AnalysisDTO;
import com.ichinait.food.dto.analysis.AnalysisDatasDTO;
import com.ichinait.food.dto.analysis.AnalysisDatasPlusDTO;
import com.ichinait.food.dto.analysis.CommentDTO;
import com.ichinait.food.dto.data.ValuesDTO;
import com.ichinait.food.queue.Calculator;
import com.ichinait.food.service.analysis.AnalysisService;
import com.ichinait.food.service.datas.DatasService;
import com.ichinait.food.util.JsonMapper;
import com.ichinait.food.util.SpringContextHolder;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.mgt.DefaultSessionKey;
import org.apache.shiro.session.mgt.DefaultSessionManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("analysis")
public class AnalysisController extends BaseController<Analysis, AnalysisService> {

    private Logger logger = LoggerFactory.getLogger(AnalysisController.class);
    @Resource
    private DatasService datasService;
    @Resource
    private AnalysisService analysisService;

    @RequestMapping("toAnalysisManagePage")
    public String toAnalysisManagePage() {

        return "/analysis/analysis_list";
    }

    @RequestMapping("query")
    @ResponseBody
    public Map<String, Object> query(String projectName, int curr) {
        Map<String, Object> result = Maps.newHashMap();
        PageInfo<AnalysisPlus> pageInfo = analysisService.queryAnalysisByConditions(curr, projectName);
        super.setSuccess(pageInfo, result);
        return result;
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
        AnalysisDTO analysisDTO = JsonMapper.nonEmptyMapper().fromJson(data, AnalysisDTO.class);

        Algorithm algorithm = new Algorithm();
        algorithm.setName(analysisDTO.getAlgorithmInfo().getAlgorithmName());
        List<AlgorithmParams> paramses = analysisDTO.getAlgorithmInfo().getAlgorithmParams();
        List<AnalysisDatasDTO> analysisDatasDTOs = null;
        try {
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

    /**
     * 异步分析数据
     * @param data
     * @return
     */
    @RequestMapping("asyncAnalysisData")
    @ResponseBody
    public Object asyncAnalysisData(@RequestParam(name = "params") String data){
        Map<String,Object> result = new HashMap<>();

        DefaultSessionManager sessionManager = (DefaultSessionManager)SpringContextHolder.getBean("sessionManager");
        //正常代码：
        Subject subject = SecurityUtils.getSecurityManager().createSubject(new DefaultSubjectContext());

        SecurityUtils.getSecurityManager().getSession(new DefaultSessionKey());


        AnalysisDTO analysisDTO = JsonMapper.nonEmptyMapper().fromJson(data, AnalysisDTO.class);
        Calculator.getInstance().put(analysisDTO);
        result.put("success",Constant.EXEC_SUCCESS);
        return result;
    }

    /**
     * 保存分析后的数据
     *
     * @param cacheKey
     * @return
     */
    @RequestMapping("save")
    public String saveAnalysis(Long cacheKey) {

        analysisService.saveAnalysisData(cacheKey);
        return "/analysis/analysis_list";
    }

    /**
     * 跳转导入数据页面
     *
     * @param cacheKey
     * @param request
     * @return
     */
    @RequestMapping("toImportDataPage")
    public String toImportDataPage(Long cacheKey, HttpServletRequest request) {
        Map<String, Object> analysisInfo = AnalysisCache.getInstance().getCache().get(cacheKey);
        AnalysisDTO analysisDTO = (AnalysisDTO) analysisInfo.get("params");
        logger.info("导入数据:{}", JsonMapper.nonEmptyMapper().toJson(analysisDTO));
        Algorithm algorithm = new Algorithm();
        algorithm.setName(analysisDTO.getAlgorithmInfo().getAlgorithmName());
        List<AlgorithmParams> paramses = analysisDTO.getAlgorithmInfo().getAlgorithmParams();
        //查询分析用到的原始数据
        List<DataPlus> datas = datasService.queryDatasByIds(analysisDTO.getDataIds());
        request.setAttribute("datas", datas);
        request.setAttribute("params", paramses);
        request.setAttribute("algorithm", algorithm);
        return "/analysis/import_data";
    }



    @RequestMapping("toImportResultPage")
    public String toImportDataPage(String idsStr, HttpServletRequest request) {
        JavaType type = JsonMapper.nonEmptyMapper().constructCollectionType(List.class,String.class);
        List<String> ids = JsonMapper.nonEmptyMapper().fromJson(idsStr, type);
        logger.info("导入数据:{}", idsStr);
        //查询分析用到的原始数据
        List<DataPlus> datas = datasService.queryDatasByIds(ids);
        request.setAttribute("datas", datas);
        return "/analysis/import_data";
    }

    /**
     * 导入自定义算法处理后的数据
     *
     * @param request
     * @param algorithmInfo
     * @return
     */
    @RequestMapping("importData")
    public String importData(HttpServletRequest request, String algorithmInfo) {
        logger.info("算法信息：{}", algorithmInfo);
        AlgorithmAddDTO algorithmAddDTO = JsonMapper.nonEmptyMapper().fromJson(algorithmInfo, AlgorithmAddDTO.class);
        //算法名称
        Algorithm algorithm = new Algorithm();
        algorithm.setName(algorithmAddDTO.getAlgorithmName());
        //算法参数
        List<AlgorithmParams> paramses = algorithmAddDTO.getAlgorithmParams();
        //导入文件
        MultipartHttpServletRequest req = (MultipartHttpServletRequest) request;
        Map<String, MultipartFile> fileMap = req.getFileMap();
        User user = (User) SecurityUtils.getSubject().getSession().getAttribute("user");
        List<AnalysisDatasDTO> analysisDatasDTOs = Lists.newArrayList();
        //原始数据ids
        List<String> ids = Lists.newArrayList();

        List<AnalysisDatasPlusDTO> analysisDatasPlusDTOs = Lists.newArrayList();
        for (String key : fileMap.keySet()) {
            ids.add(key);
            AnalysisDatasPlusDTO analysisDatasPlusDTO = new AnalysisDatasPlusDTO();
            MultipartFile file = fileMap.get(key);
            try {
                List<String> lines = CharStreams.readLines(new InputStreamReader(file.getInputStream()));
                List<ValuesDTO> valuesDTOs = datasService.handlerData(lines);
                AnalysisDatasDTO analysisDatasDTO = datasService.createAnalysisDatasDTO(key, valuesDTOs);
                analysisDatasPlusDTO.setId(key);
                analysisDatasPlusDTO.setValuesDTOs(valuesDTOs);
                analysisDatasPlusDTOs.add(analysisDatasPlusDTO);
                analysisDatasDTOs.add(analysisDatasDTO);
            } catch (Exception e) {
                logger.error("上传文件出错：" + Throwables.getStackTraceAsString(e));
            }
        }
        List<DataPlus> datas = datasService.queryDatasByIds(ids);
        //将分析信息存入缓存中
        Long key = new DateTime().getMillis();
        AnalysisDTO analysisDTO = new AnalysisDTO();
        analysisDTO.setDataIds(ids);
        analysisDTO.setAlgorithmInfo(algorithmAddDTO);
        analysisDTO.setAnalysisDatasPlusDTOs(analysisDatasPlusDTOs);
        AnalysisCache.getInstance().getCache().put(key, getAnalysisInfoMap(analysisDTO, analysisDatasDTOs));
        request.setAttribute("algorithm", algorithm);
        request.setAttribute("params", paramses);
        request.setAttribute("x", datas.get(0).getX());
        request.setAttribute("y", datas.get(0).getY());
        request.setAttribute("datas", datas);
        request.setAttribute("cacheKey", key);
        request.setAttribute("analysisDatas", JsonMapper.nonEmptyMapper().toJson(analysisDatasDTOs));
        return "/analysis/analysis_info";
    }

    /**
     * 查看分析详情
     *
     * @param id
     * @param request
     * @return
     */
    @RequestMapping("view")
    public String view(String id, HttpServletRequest request) {
        Map<String, Object> result = null;
        try {
            result = analysisService.queryAnalysisById(id);
        } catch (Exception e) {

            logger.error("查看分析详情{}", Throwables.getStackTraceAsString(e));
        }
        request.setAttribute("algorithmInfo", result.get("algorithmInfo"));
        request.setAttribute("values", JsonMapper.nonEmptyMapper().toJson(result.get("values")));
        request.setAttribute("datas", result.get("datas"));
        request.setAttribute("x", result.get("x"));
        request.setAttribute("y", result.get("y"));
        return "/analysis/analysis_comment";
    }

    /**
     * 评价
     *
     * @param request
     * @param dto
     * @return
     */
    @RequestMapping("comment")
    @ResponseBody
    public Map<String, Object> comment(HttpServletRequest request, CommentDTO dto) {
        Map<String, Object> result = Maps.newHashMap();
        User user = (User) SecurityUtils.getSubject().getSession().getAttribute("user");
        MultipartHttpServletRequest req = (MultipartHttpServletRequest) request;
        Map<String, MultipartFile> fileMap = req.getFileMap();
        MultipartFile dataFile = fileMap.get("file1");
        MultipartFile modalFile = fileMap.get("file2");
        dto.setDataFile(dataFile);
        dto.setModalFile(modalFile);
        dto.setUploader(user.getId());
        logger.debug("上传模型：{}", JsonMapper.nonEmptyMapper().toJson(dto));
        try {
            analysisService.comment(dto);
        } catch (Exception e) {
            logger.error("评价出错：{}", Throwables.getStackTraceAsString(e));
        }
        result.put("success", Constant.EXEC_SUCCESS);
        return result;
    }

    @RequestMapping("queryCommentById")
    @ResponseBody
    public Map<String, Object> queryCommentById(String id){
        Map<String,Object> result = Maps.newHashMap();
        Comment comment = analysisService.queryCommentById(id);
        result.put("success",Constant.EXEC_SUCCESS);
        result.put("comment", comment);
        return result;
    }


    private Map<String, Object> getAnalysisInfoMap(AnalysisDTO analysisDTO, List<AnalysisDatasDTO> analysisDatasDTOs) {
        Map<String, Object> analysisInfo = Maps.newHashMap();
        //分析参数
        analysisInfo.put(AnalysisCache.PARAM_KEY, analysisDTO);
        //分析结果
        analysisInfo.put(AnalysisCache.RESULT_KEY, analysisDatasDTOs);
        return analysisInfo;
    }

}
