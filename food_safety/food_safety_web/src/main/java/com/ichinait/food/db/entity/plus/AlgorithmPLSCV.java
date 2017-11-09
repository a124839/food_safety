package com.ichinait.food.db.entity.plus;

import java.util.Date;

public class AlgorithmPLSCV {

    
    private String id;
    private Double x;
    private Double y;
    //主成分数
    private Integer numcomp;
   
    //预测结果
    private Double Ycv;
    //交叉验证的x的得分
    private Double Tcv;
    //
    private Double RMSECV;
    private Double R2CV;
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
    public Double getX() {
        return x;
    }
    public void setX(Double x) {
        this.x = x;
    }
    public Double getY() {
        return y;
    }
    public void setY(Double y) {
        this.y = y;
    }
    public Integer getNumcomp() {
        return numcomp;
    }
    public void setNumcomp(Integer numcomp) {
        this.numcomp = numcomp;
    }
    public Double getYcv() {
        return Ycv;
    }
    public void setYcv(Double ycv) {
        Ycv = ycv;
    }
    public Double getTcv() {
        return Tcv;
    }
    public void setTcv(Double tcv) {
        Tcv = tcv;
    }
    public Double getRMSECV() {
        return RMSECV;
    }
    public void setRMSECV(Double rMSECV) {
        RMSECV = rMSECV;
    }
    public Double getR2CV() {
        return R2CV;
    }
    public void setR2CV(Double r2cv) {
        R2CV = r2cv;
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
        return "AlgorithmPLSCV [id=" + id + ", x=" + x + ", y=" + y + ", numcomp=" + numcomp + ", Ycv=" + Ycv + ", Tcv="
                + Tcv + ", RMSECV=" + RMSECV + ", R2CV=" + R2CV + ", operator=" + operator + ", ct=" + ct + "]";
    }
    
    
    
}
