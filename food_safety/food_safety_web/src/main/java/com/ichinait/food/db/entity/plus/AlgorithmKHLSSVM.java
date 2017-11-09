package com.ichinait.food.db.entity.plus;

import java.util.Date;

/**
 * @author k570
 * KHLSSVM原始算法还要改
 */
public class AlgorithmKHLSSVM {

    
    private String id;
    //总运行次数
    private Integer NR;
    //磷虾个数
    private Integer NK;
    //磷虾迭代次数
    private Integer MI;    
    //gam最大值
    private Double UBg;
    //gam最小值
    private Double LBg;
    //sig最大值
    private Double UBc;
    //sig最小值
    private Double LBc;
    
    private Double trainset;
    private Double trainlabel;
    private Double testset;
    private Double testlabel;
    //结果
    private Double Ypre;
    private Double R2;
    private Double RMSECV;
    
    //操作人
    private String operator;
    //创建时间
    private Date ct;
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public Double getTrainset() {
        return trainset;
    }
    public void setTrainset(Double trainset) {
        this.trainset = trainset;
    }
    public Double getTrainlabel() {
        return trainlabel;
    }
    public void setTrainlabel(Double trainlabel) {
        this.trainlabel = trainlabel;
    }
    public Double getTestset() {
        return testset;
    }
    public void setTestset(Double testset) {
        this.testset = testset;
    }
    public Double getTestlabel() {
        return testlabel;
    }
    public void setTestlabel(Double testlabel) {
        this.testlabel = testlabel;
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
    public Double getYpre() {
        return Ypre;
    }
    public void setYpre(Double ypre) {
        Ypre = ypre;
    }
    public Double getR2() {
        return R2;
    }
    public void setR2(Double r2) {
        R2 = r2;
    }
    public Double getRMSECV() {
        return RMSECV;
    }
    public void setRMSECV(Double rMSECV) {
        RMSECV = rMSECV;
    }
    public String getOperator() {
        return operator;
    }
    public void setOperator(String operator) {
        this.operator = operator;
    }
    public Date getCt() {
        return ct;
    }
    public void setCt(Date ct) {
        this.ct = ct;
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
    @Override
    public String toString() {
        return "AlgorithmKHLSSVM [id=" + id + ", trainset=" + trainset + ", trainlabel=" + trainlabel + ", testset="
                + testset + ", testlabel=" + testlabel + ", UBg=" + UBg + ", LBg=" + LBg + ", UBc=" + UBc + ", LBc="
                + LBc + ", Ypre=" + Ypre + ", R2=" + R2 + ", RMSECV=" + RMSECV + ", operator=" + operator + ", ct=" + ct
                + "]";
    }
    
    
}
