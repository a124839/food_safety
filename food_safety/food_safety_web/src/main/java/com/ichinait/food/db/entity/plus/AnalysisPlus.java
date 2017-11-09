package com.ichinait.food.db.entity.plus;

import com.google.common.base.Strings;
import com.ichinait.food.dto.algorithm.AlgorithmAddDTO;
import com.ichinait.food.util.JsonMapper;

import java.util.Date;

/**
 * Created by ichinait on 2016/3/28.
 */
public class AnalysisPlus {
    private String id;
    private String projectName;
    private String algorithmInfo;
    private String operator;
    private Date ct;
    private String algorithmName;
    private String commentId;
    private String modalAtmId;
    private String dataAtmId;


    public String getCommentId() {
        return commentId;
    }

    public void setCommentId(String commentId) {
        this.commentId = commentId;
    }

    public String getModalAtmId() {
        return modalAtmId;
    }

    public void setModalAtmId(String modalAtmId) {
        this.modalAtmId = modalAtmId;
    }

    public String getDataAtmId() {
        return dataAtmId;
    }

    public void setDataAtmId(String dataAtmId) {
        this.dataAtmId = dataAtmId;
    }

    public String getAlgorithmInfo() {
        return algorithmInfo;
    }

    public void setAlgorithmInfo(String algorithmInfo) {
        this.algorithmInfo = algorithmInfo;
        if(!Strings.isNullOrEmpty(algorithmInfo)){
            AlgorithmAddDTO algorithmAddDTO =  JsonMapper.nonEmptyMapper().fromJson(algorithmInfo, AlgorithmAddDTO.class);
            this.algorithmName = algorithmAddDTO.getAlgorithmName();
        }
    }

    public String getAlgorithmName() {
        return algorithmName;
    }

    public void setAlgorithmName(String algorithmName) {
        this.algorithmName = algorithmName;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Date getCt() {
        return ct;
    }

    public void setCt(Date ct) {
        this.ct = ct;
    }
}
