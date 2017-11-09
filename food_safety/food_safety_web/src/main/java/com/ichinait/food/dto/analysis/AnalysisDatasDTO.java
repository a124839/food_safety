package com.ichinait.food.dto.analysis;

import java.util.List;

/**
 * Created by ichinait on 2016/3/23.
 */
public class AnalysisDatasDTO {
    private String dataId;
    private List<Double> xDatas;
    private List<Double> yDatas;

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

    public String getDataId() {
        return dataId;
    }

    public void setDataId(String dataId) {
        this.dataId = dataId;
    }
}
