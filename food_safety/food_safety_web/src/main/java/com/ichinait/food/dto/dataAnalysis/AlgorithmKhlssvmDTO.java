package com.ichinait.food.dto.dataAnalysis;

import java.util.List;

public class AlgorithmKhlssvmDTO {

    
    private String dataId;
    private List<Double> trainset;
    private List<Double> trainlabel;
    private List<Double> testset;
    private List<Double> testlabel;
    //kh优化lssvm的总运行次数
    private Integer NR;
    //kh算法中磷虾个数
    private Integer NK;
    //kh算法中最大迭代次数
    private Integer MI;
    //lssvm（svm）中gam最大值
    private Double UBg;
    //lssvm（svm）中gam最小值
    private Double LBg;
    //lssvm（svm）中sig最大值
    private Double UBc;
    //lssvm（svm）中sig最小值
    private Double LBc;
    public String getDataId() {
        return dataId;
    }
    public void setDataId(String dataId) {
        this.dataId = dataId;
    }
    public List<Double> getTrainset() {
        return trainset;
    }
    public void setTrainset(List<Double> trainset) {
        this.trainset = trainset;
    }
    public List<Double> getTrainlabel() {
        return trainlabel;
    }
    public void setTrainlabel(List<Double> trainlabel) {
        this.trainlabel = trainlabel;
    }
    public List<Double> getTestset() {
        return testset;
    }
    public void setTestset(List<Double> testset) {
        this.testset = testset;
    }
    public List<Double> getTestlabel() {
        return testlabel;
    }
    public void setTestlabel(List<Double> testlabel) {
        this.testlabel = testlabel;
    }
    public Integer getNR() {
        return NR;
    }
    public void setNR(Integer nR) {
        NR = nR;
    }
    public Integer getNK() {
        return NK;
    }
    public void setNK(Integer nK) {
        NK = nK;
    }
    public Integer getMI() {
        return MI;
    }
    public void setMI(Integer mI) {
        MI = mI;
    }
    public Double getUBg() {
        return UBg;
    }
    public void setUBg(Double uBg) {
        UBg = uBg;
    }
    public Double getLBg() {
        return LBg;
    }
    public void setLBg(Double lBg) {
        LBg = lBg;
    }
    public Double getUBc() {
        return UBc;
    }
    public void setUBc(Double uBc) {
        UBc = uBc;
    }
    public Double getLBc() {
        return LBc;
    }
    public void setLBc(Double lBc) {
        LBc = lBc;
    }
    @Override
    public String toString() {
        return "AlgorithmKhlssvmDTO [dataId=" + dataId + ", trainset=" + trainset + ", trainlabel=" + trainlabel
                + ", testset=" + testset + ", testlabel=" + testlabel + ", NR=" + NR + ", NK=" + NK + ", MI=" + MI
                + ", UBg=" + UBg + ", LBg=" + LBg + ", UBc=" + UBc + ", LBc=" + LBc + "]";
    }
    
    
}
