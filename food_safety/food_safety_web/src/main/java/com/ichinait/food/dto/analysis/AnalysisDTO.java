package com.ichinait.food.dto.analysis;

import com.ichinait.food.dto.algorithm.AlgorithmAddDTO;
import org.apache.shiro.session.Session;

import java.util.List;

/**
 * Created by ichinait on 2016/3/23.
 */
public class AnalysisDTO {
    private AlgorithmAddDTO algorithmInfo;
    private List<String> dataIds;
    //新导入的数据
    private List<AnalysisDatasPlusDTO> analysisDatasPlusDTOs;



    public AlgorithmAddDTO getAlgorithmInfo() {
        return algorithmInfo;
    }

    public void setAlgorithmInfo(AlgorithmAddDTO algorithmInfo) {
        this.algorithmInfo = algorithmInfo;
    }

    public List<String> getDataIds() {
        return dataIds;
    }

    public void setDataIds(List<String> dataIds) {
        this.dataIds = dataIds;
    }

    public List<AnalysisDatasPlusDTO> getAnalysisDatasPlusDTOs() {
        return analysisDatasPlusDTOs;
    }

    public void setAnalysisDatasPlusDTOs(List<AnalysisDatasPlusDTO> analysisDatasPlusDTOs) {
        this.analysisDatasPlusDTOs = analysisDatasPlusDTOs;
    }


}
