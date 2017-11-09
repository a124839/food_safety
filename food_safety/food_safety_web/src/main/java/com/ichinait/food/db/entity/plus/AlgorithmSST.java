package com.ichinait.food.db.entity.plus;

import java.util.Date;

public class AlgorithmSST {

    
    private String id;
    //主机标准谱数据
    private Double Xm;
    //从机标准谱数据
    private Double Xs;
    //从机待测数据
    private Double Xc;
    //主成分数
    private Integer numcomp;
    //转换结果
    private Double result;
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
    public Double getXm() {
        return Xm;
    }
    public void setXm(Double xm) {
        Xm = xm;
    }
    public Double getXs() {
        return Xs;
    }
    public void setXs(Double xs) {
        Xs = xs;
    }
    public Double getXc() {
        return Xc;
    }
    public void setXc(Double xc) {
        Xc = xc;
    }
    public Integer getNumcomp() {
        return numcomp;
    }
    public void setNumcomp(Integer numcomp) {
        this.numcomp = numcomp;
    }
    public Double getResult() {
        return result;
    }
    public void setResult(Double result) {
        this.result = result;
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
    @Override
    public String toString() {
        return "AlgorithmSST [id=" + id + ", Xm=" + Xm + ", Xs=" + Xs + ", Xc=" + Xc + ", numcomp=" + numcomp
                + ", result=" + result + ", operator=" + operator + ", ct=" + ct + "]";
    }
    
    
}