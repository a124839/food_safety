package com.ichinait.food.dto.dataAnalysis;

import java.util.List;

public class AlgorithmSstDTO {

    private String dataId;
    
    private List<String> xmdataIds;
    private List<String> xsdataIds;
    private List<String> xcdataIds;
    
    private Integer numcomp;

    public String getDataId() {
        return dataId;
    }

    public void setDataId(String dataId) {
        this.dataId = dataId;
    }

	public List<String> getXmdataIds() {
		return xmdataIds;
	}

	public void setXmdataIds(List<String> xmdataIds) {
		this.xmdataIds = xmdataIds;
	}

	public List<String> getXsdataIds() {
		return xsdataIds;
	}

	public void setXsdataIds(List<String> xsdataIds) {
		this.xsdataIds = xsdataIds;
	}

	public List<String> getXcdataIds() {
		return xcdataIds;
	}

	public void setXcdataIds(List<String> xcdataIds) {
		this.xcdataIds = xcdataIds;
	}

	public Integer getNumcomp() {
		return numcomp;
	}

	public void setNumcomp(Integer numcomp) {
		this.numcomp = numcomp;
	}

	@Override
	public String toString() {
		return "AlgorithmSstDTO [dataId=" + dataId + ", xmdataIds=" + xmdataIds + ", xsdataIds=" + xsdataIds
				+ ", xcdataIds=" + xcdataIds + ", numcomp=" + numcomp + "]";
	}

    
    
    
}
