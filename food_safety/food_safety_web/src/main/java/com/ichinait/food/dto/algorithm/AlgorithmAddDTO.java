package com.ichinait.food.dto.algorithm;

import com.ichinait.food.db.entity.AlgorithmParams;

import java.util.List;

/**
 * Created by ichinait on 2016/3/22.
 */
public class AlgorithmAddDTO {
    private String id;
    private String algorithmName;
    private String algorithmType;
    private String sampleCode;
    private String example;
    private List<AlgorithmParams> algorithmParams;
    private String attachmentId;
    private String operator;
    private String memo;

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getSampleCode() {
        return sampleCode;
    }

    public void setSampleCode(String sampleCode) {
        this.sampleCode = sampleCode;
    }

    public String getExample() {
        return example;
    }

    public void setExample(String example) {
        this.example = example;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public List<AlgorithmParams> getAlgorithmParams() {
        return algorithmParams;
    }

    public void setAlgorithmParams(List<AlgorithmParams> algorithmParams) {
        this.algorithmParams = algorithmParams;
    }

    public String getAlgorithmType() {
        return algorithmType;
    }

    public void setAlgorithmType(String algorithmType) {
        this.algorithmType = algorithmType;
    }

    public String getAlgorithmName() {

        return algorithmName;
    }

    public void setAlgorithmName(String algorithmName) {
        this.algorithmName = algorithmName;
    }

    public String getAttachmentId() {
        return attachmentId;
    }

    public void setAttachmentId(String attachmentId) {
        this.attachmentId = attachmentId;
    }
}
