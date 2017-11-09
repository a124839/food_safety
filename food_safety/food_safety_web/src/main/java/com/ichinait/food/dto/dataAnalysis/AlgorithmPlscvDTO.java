package com.ichinait.food.dto.dataAnalysis;

import java.util.List;

public class AlgorithmPlscvDTO {

    private String dataId;
    private List<Double> xDatas;
    private List<Double> yDatas;
    
    private Integer numcopm;

    public String getDataId() {
        return dataId;
    }

    public void setDataId(String dataId) {
        this.dataId = dataId;
    }

    public List<Double> getxDatas() {
        return xDatas;
    }

    public void setxDatas(List<Double> xDatas) {
        this.xDatas = xDatas;
    }

    public List<Double> getyDatas() {
        return yDatas;
    }

    public void setyDatas(List<Double> yDatas) {
        this.yDatas = yDatas;
    }

    public Integer getNumcopm() {
        return numcopm;
    }

    public void setNumcopm(Integer numcopm) {
        this.numcopm = numcopm;
    }

    @Override
    public String toString() {
        return "AlgorithmPlscvDTO [dataId=" + dataId + ", xDatas=" + xDatas + ", yDatas=" + yDatas + ", numcopm="
                + numcopm + "]";
    }
    
    
}
