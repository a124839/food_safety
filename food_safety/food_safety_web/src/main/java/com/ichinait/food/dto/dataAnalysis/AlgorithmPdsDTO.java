package com.ichinait.food.dto.dataAnalysis;

import java.util.List;

/**
 * @author k570
 * pds数据
 * 
 */
public class AlgorithmPdsDTO {
    
    private List<String> xmdataIds;
    private List<String> xsdataIds;
    private List<String> xcdataIds;
   
    private Integer wleft;
    private Integer wright;
    private Integer numcomp;
    
    
   
    public Integer getWleft() {
        return wleft;
    }
    public void setWleft(Integer wleft) {
        this.wleft = wleft;
    }
    public Integer getWright() {
        return wright;
    }
    public void setWright(Integer wright) {
        this.wright = wright;
    }
    public Integer getNumcomp() {
        return numcomp;
    }
    public void setNumcomp(Integer numcomp) {
        this.numcomp = numcomp;
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
	@Override
	public String toString() {
		return "AlgorithmPdsDTO [xmdataIds=" + xmdataIds + ", xsdataIds=" + xsdataIds + ", xcdataIds=" + xcdataIds
				+ ", wleft=" + wleft + ", wright=" + wright + ", numcomp=" + numcomp + "]";
	}
    
    
    
   
    
    
    
    
    
}
