package com.ichinait.food.controller.algorithm;

import com.github.pagehelper.PageInfo;
import com.google.common.base.Throwables;
import com.google.common.collect.Maps;
import com.ichinait.food.constant.Constant;
import com.ichinait.food.controller.base.BaseController;
import com.ichinait.food.db.entity.Algorithm;
import com.ichinait.food.db.entity.AlgorithmParams;
import com.ichinait.food.db.entity.User;
import com.ichinait.food.dto.algorithm.AlgorithmAddDTO;
import com.ichinait.food.exception.ErrorMessage;
import com.ichinait.food.service.algorithm.AlgorithmService;
import com.ichinait.food.service.attachment.AttachmentService;
import com.ichinait.food.util.FileUtil;
import com.ichinait.food.util.JsonMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created by ichinait on 2016/3/21.
 */
@Controller
@RequestMapping("algorithm")
public class AlgorithmController extends BaseController<Algorithm,AlgorithmService>{

    private Logger logger = LoggerFactory.getLogger(AlgorithmController.class);

    @Autowired
    private AlgorithmService algorithmService;

    @Autowired
    private AttachmentService attachmentService;


    @RequestMapping("toAlgorithmManagePage")
    public String toAlgorithmManagePage(){
        return "/algorithm/algorithm_list";
    }


    /**
     *分页查询算法列表
     * @return
     */
    @RequestMapping(value="query",method= RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> queryAlgorithmByConditions(
            @RequestParam(value="curr",defaultValue="1",required=true)int curror, String algorithmName){
        logger.info("=======================================查询算法列表：{}", algorithmName);
        Map<String,Object> result = Maps.newHashMap();
//        JavaType type = JsonMapper.nonEmptyMapper().constructMapType(Map.class,String.class,Object.class);
//        Map<String,Object> params = JsonMapper.nonEmptyMapper().fromJson(data,type);
        Map<String,Object> params = Maps.newHashMap();
        params.put("algorithmName",algorithmName);
        try {
            PageInfo<Algorithm> pageInfo = algorithmService.queryAlgorithmByConditions(curror,params);
            result.put("recordsTotal", pageInfo.getTotal());//总记录数
            result.put("totalPages", pageInfo.getPages());//总页数
            result.put("list", pageInfo.getList());
            result.put("success", Constant.EXEC_SUCCESS);
        } catch (Exception e) {
            logger.error("=======================================查询算法列表失败：{}", Throwables.getStackTraceAsString(e));
            result.put("errorCode", ErrorMessage.SYSTEM_ERROR.getCode());
            result.put("errorMessage", ErrorMessage.SYSTEM_ERROR.getMsg());
            result.put("success", Constant.EXEC_ERROR);
        }
        return result;
    }

    @RequestMapping("delete")
    @ResponseBody
    public Map<String,Object> delete(@RequestParam(name = "algorithmId") String id){
        Map<String,Object> result = Maps.newHashMap();
        logger.info("=======================================删除算法：{}",id);
        algorithmService.updateAlgorithm(id);
        result.put("success",1);
        return result;
    }

    @RequestMapping("deleteParams")
    @ResponseBody
    public Map<String,Object> deleteParams(@RequestParam(name = "paramId") String id){
        Map<String,Object> result = Maps.newHashMap();
        logger.info("=======================================删除算法参数：{}",id);
        algorithmService.deleteParam(id);
        result.put("success",1);
        return result;
    }

    @RequestMapping("add")
    public String toAddAlgorithmPage(){
        return "/algorithm/algorithm_add";
    }

    @RequestMapping("save")
    @ResponseBody
    public Map<String,Object> save(@RequestParam(name="data") String data,HttpServletRequest request){
        logger.info("=======================================保存算法参数：{}",data);
        Map<String,Object> result = Maps.newHashMap();
        AlgorithmAddDTO dto = JsonMapper.nonEmptyMapper().fromJson(data, AlgorithmAddDTO.class);
        MultipartHttpServletRequest req = (MultipartHttpServletRequest) request;
        Map<String, MultipartFile> fileMap = req.getFileMap();
        MultipartFile file = fileMap.get("attachment");
        User user = super.getSessionUser();
        String savePath = FileUtil.getAlgorithmAttachmentUploadPath();
        try {
            if(file != null){
               String attachmentId =  attachmentService.saveAttachment(file,user.getId(),savePath);
                dto.setAttachmentId(attachmentId);
            }
            dto.setOperator(user.getId());
            algorithmService.saveOrUpdate(dto);
            result.put("success",Constant.EXEC_SUCCESS);
        } catch (Exception e) {
            logger.error("=======================================保存算法出错：{}",Throwables.getStackTraceAsString(e));
            result.put("success",Constant.EXEC_ERROR);
        }
        return result;
    }

    @RequestMapping("info")
    public String algorithmInfo(@RequestParam(name="algorithmId") String id, HttpServletRequest request){
        Algorithm algorithm = algorithmService.queryAlgorithmById(id);
        List<AlgorithmParams> algorithmParamses = algorithmService.queryAlgorithmParams(id);
        request.setAttribute("algorithm",algorithm);
        request.setAttribute("params",algorithmParamses);
        return "/algorithm/algorithm_info";
    }


    @RequestMapping("edit")
    public String editAlgorithmInfo(@RequestParam(name="algorithmId") String id, HttpServletRequest request){
        Algorithm algorithm = algorithmService.queryAlgorithmById(id);
        List<AlgorithmParams> algorithmParams = algorithmService.queryAlgorithmParams(id);
        request.setAttribute("algorithm",algorithm);
        request.setAttribute("params",algorithmParams);
        return "/algorithm/algorithm_edit";
    }


    @RequestMapping("queryNoPage")
    @ResponseBody
    public Map<String,Object> queryAlgorithm(int category){
        Map<String,Object> result = Maps.newHashMap();
        List<Algorithm> algorithms = algorithmService.queryAlgorithmNoPage(category);
        result.put("algorithms",algorithms);
        result.put("success",Constant.EXEC_SUCCESS);
        return result;
    }

    /**
     * 通过算法ID，查询算法参数
     * @param algorithmId
     * @return
     */
    @RequestMapping("queryParamsNoPage")
    @ResponseBody
    public Map<String,Object> queryAlgorithmParms(String algorithmId){
        Map<String,Object> result = Maps.newHashMap();
        List<AlgorithmParams> algorithmParams = algorithmService.queryAlgorithmParamsByAlgorithmId(algorithmId);
        result.put("params",algorithmParams);
        result.put("success",Constant.EXEC_SUCCESS);
        return result;
    }




}
