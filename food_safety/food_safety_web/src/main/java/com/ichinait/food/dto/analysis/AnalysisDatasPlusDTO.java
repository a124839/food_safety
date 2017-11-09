package com.ichinait.food.dto.analysis;

import com.ichinait.food.dto.data.ValuesDTO;

import java.util.List;

/**
 * Created by ichinait on 2016/3/28.
 */
public class AnalysisDatasPlusDTO {
    //原始数据Id
    private String id;
    //新数据
    private List<ValuesDTO> valuesDTOs;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<ValuesDTO> getValuesDTOs() {
        return valuesDTOs;
    }

    public void setValuesDTOs(List<ValuesDTO> valuesDTOs) {
        this.valuesDTOs = valuesDTOs;
    }
}
