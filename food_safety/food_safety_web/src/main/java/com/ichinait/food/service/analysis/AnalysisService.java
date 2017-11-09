package com.ichinait.food.service.analysis;

import com.fasterxml.jackson.databind.JavaType;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.ichinait.food.cache.AnalysisCache;
import com.ichinait.food.constant.Constant;
import com.ichinait.food.db.entity.Analysis;
import com.ichinait.food.db.entity.Comment;
import com.ichinait.food.db.entity.Datas;
import com.ichinait.food.db.entity.User;
import com.ichinait.food.db.entity.plus.AnalysisPlus;
import com.ichinait.food.db.entity.plus.DataPlus;
import com.ichinait.food.db.mapper.AnalysisMapper;
import com.ichinait.food.db.mapper.CommentMapper;
import com.ichinait.food.db.mapper.plus.AnalysisMapperPlus;
import com.ichinait.food.db.mapper.plus.DataMapperPlus;
import com.ichinait.food.dto.algorithm.AlgorithmAddDTO;
import com.ichinait.food.dto.analysis.AnalysisDTO;
import com.ichinait.food.dto.analysis.AnalysisDatasDTO;
import com.ichinait.food.dto.analysis.AnalysisDatasPlusDTO;
import com.ichinait.food.dto.analysis.CommentDTO;
import com.ichinait.food.dto.data.ValuesDTO;
import com.ichinait.food.service.attachment.AttachmentService;
import com.ichinait.food.service.datas.DatasService;
import com.ichinait.food.service.instrument.InstrumentService;
import com.ichinait.food.util.JsonMapper;
import com.ichinait.food.util.PropertiesLoader;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by ichinait on 2016/3/28.
 */
@Service
@Transactional(isolation = Isolation.READ_COMMITTED)
public class AnalysisService {
    private final Logger logger = LoggerFactory.getLogger(AnalysisService.class);

    @Autowired
    private AnalysisMapper analysisMapper;

    @Resource
    private DataMapperPlus dataMapperPlus;
    @Autowired
    private AnalysisMapperPlus analysisMapperPlus;
    @Autowired
    private DatasService datasService;
    @Autowired
    private InstrumentService   instrumentService;
    @Autowired
    private AttachmentService attachmentService;
    @Autowired
    private CommentMapper commentMapper;

    /**
     * 保存分析数据
     * @param cacheKey
     */
    public void saveAnalysisData(Long cacheKey){
        Map<String,Object> cache = AnalysisCache.getInstance().getCache().get(cacheKey);
        AnalysisDTO analysisDTO = (AnalysisDTO)cache.get(AnalysisCache.PARAM_KEY);
        List<AnalysisDatasDTO> analysisDatasDTOs = (List<AnalysisDatasDTO>) cache.get(AnalysisCache.RESULT_KEY);
        logger.info("保存分析结果:{}", JsonMapper.nonEmptyMapper().toJson(analysisDTO));
        String projectId = null;
        if(analysisDTO.getAnalysisDatasPlusDTOs() == null){//没有导入数据
            List<AnalysisDatasPlusDTO> analysisDatasPlusDTOs = Lists.newArrayList();
            List<Datas> datases = dataMapperPlus.selectDatasByIds(analysisDTO.getDataIds());
            projectId = datases.get(0).getProjectId();
            JavaType javaType = JsonMapper.nonEmptyMapper().constructCollectionType(List.class, ValuesDTO.class);
            for(AnalysisDatasDTO dto:analysisDatasDTOs){
                AnalysisDatasPlusDTO analysisDatasPlusDTO =  new AnalysisDatasPlusDTO();
                analysisDatasPlusDTO.setId(dto.getDataId());
                List<ValuesDTO> valuesDTOs = Lists.newArrayList();
                List<Double> xDoubles = dto.getxDatas();
                List<Double> yDoubles = dto.getyDatas();
                for(int i = 0;i<xDoubles.size();i++){
                    ValuesDTO valuesDTO = new ValuesDTO();
                    valuesDTO.setX(xDoubles.get(i));
                    valuesDTO.setY(yDoubles.get(i));
                    valuesDTOs.add(valuesDTO);
                }
                analysisDatasPlusDTO.setValuesDTOs(valuesDTOs);
                analysisDatasPlusDTOs.add(analysisDatasPlusDTO);
            }
            analysisDTO.setAnalysisDatasPlusDTOs(analysisDatasPlusDTOs);
        }else{
            String dataId = analysisDTO.getDataIds().get(0);
            projectId = datasService.queryDatasDetail(dataId).getProjectId();
        }
        User user = (User) SecurityUtils.getSubject().getSession().getAttribute("user");
        Analysis analysis = new Analysis();
        analysis.setId(UUID.randomUUID().toString());
        analysis.setProjectId(projectId);
        analysis.setAlgorithmInfo(JsonMapper.nonEmptyMapper().toJson(analysisDTO.getAlgorithmInfo()));
        analysis.setDatasInfo(JsonMapper.nonEmptyMapper().toJson(analysisDTO.getAnalysisDatasPlusDTOs()));
        analysis.setOperator(user.getId());
        analysis.setCt(new Date());
        analysis.setUt(new Date());
        analysisMapper.insert(analysis);
        //保存后，从内存中移除
        AnalysisCache.getInstance().getCache().remove(cacheKey);
    }

    /**
     * 分页查询分析列表
     * @param curr
     * @param projectName
     * @return
     */
    public PageInfo<AnalysisPlus> queryAnalysisByConditions(int curr, String projectName){
        PageHelper.startPage(curr, Constant.PAGE_LIMIT);
        Map<String,Object> params = Maps.newHashMap();
        params.put("projectName",projectName);
        List<AnalysisPlus> analysisPluses = analysisMapperPlus.selectAnalysisByConditions(params);
        PageInfo<AnalysisPlus> pageInfo = new PageInfo<>();
        pageInfo.setList(analysisPluses);
        return pageInfo;
    }


    public Map<String,Object> queryAnalysisById(String id) throws Exception{
        Map<String,Object> result = Maps.newHashMap();
        Analysis analysis = analysisMapper.selectByPrimaryKey(id);
        AlgorithmAddDTO algorithmAddDTO = JsonMapper.nonEmptyMapper().fromJson(analysis.getAlgorithmInfo(),AlgorithmAddDTO.class);
        JavaType javaType = JsonMapper.nonEmptyMapper().constructCollectionType(List.class,AnalysisDatasPlusDTO.class);
        List<AnalysisDatasPlusDTO> analysisDatasPlusDTOs = JsonMapper.nonEmptyMapper().fromJson(analysis.getDatasInfo(),javaType);
        List<String> ids = Lists.newArrayList();
        List<AnalysisDatasDTO> analysisDatasDTOs = Lists.newArrayList();
        JavaType type = JsonMapper.nonEmptyMapper().constructCollectionType(List.class,ValuesDTO.class);
        for(AnalysisDatasPlusDTO analysisDatasPlusDTO:analysisDatasPlusDTOs){
            ids.add(analysisDatasPlusDTO.getId());
            List<ValuesDTO> valuesDTOs = analysisDatasPlusDTO.getValuesDTOs();
            AnalysisDatasDTO analysisDatasDTO = datasService.createAnalysisDatasDTO(analysisDatasPlusDTO.getId(),valuesDTOs);
            analysisDatasDTOs.add(analysisDatasDTO);
        }
        List<DataPlus> dataPluses = dataMapperPlus.selectDatasPlusByIds(ids);
        result.put("algorithmInfo",algorithmAddDTO);
        result.put("values",analysisDatasDTOs);
        result.put("datas",dataPluses);
        if(!CollectionUtils.isEmpty(dataPluses)){
           result.put("x",dataPluses.get(0).getX());
           result.put("y",dataPluses.get(0).getY());
       }
        return result;
    }


    public void comment(CommentDTO dto) throws  Exception{
        PropertiesLoader loader = new PropertiesLoader("/application.properties");
        String path = loader.getProperty(Constant.PROPER_KEY_COMMENT_ATTACHMENT_UPLOAD_PATH);
        String modalAtmId =  attachmentService.saveAttachment(dto.getModalFile(),dto.getUploader(),path);
        String dataAtmId = attachmentService.saveAttachment(dto.getDataFile(),dto.getUploader(),path);
        Comment comment = new Comment();
        comment.setId(UUID.randomUUID().toString());
        comment.setAnalysisId(dto.getAnalysisId());
        comment.setCt(new Date());
        comment.setName(dto.getName());
        comment.setDataAttachmentId(dataAtmId);
        comment.setModalAttachmentId(modalAtmId);
        comment.setJmyps(dto.getJmyps());
        comment.setMxzcfs(dto.getZcfs());
        comment.setMemo(dto.getMemo());
        comment.setRmscv(dto.getRmscv());
        comment.setrR(dto.getR());
        comment.setUt(new Date());
        comment.setOperator(dto.getUploader());
        commentMapper.insert(comment);
    }


    public Comment queryCommentById(String id){
        return commentMapper.selectByPrimaryKey(id);
    }

}
