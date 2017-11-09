package com.ichinait.food.db.entity.plus;

import java.util.Date;

/**
 * Created by ichinait on 2016/4/7.
 */
public class ExcelDatas {
    private String data;
    private String sampleName;
    private String sampleCode;
    private String sampleManufactor;
    private String sampleProducingArea;
    private Date sampleProductionDate;
    private String sampleBatches;
    private String instrumentName;
    private String instrumentSN;
    private String instrumentCategory;
    private String instrumentType;
    private String instrumentModel;
    private String instrumentManufactor;

    public String getSampleCode() {
        return sampleCode;
    }

    public void setSampleCode(String sampleCode) {
        this.sampleCode = sampleCode;
    }

    public String getSampleName() {
        return sampleName;
    }

    public void setSampleName(String sampleName) {
        this.sampleName = sampleName;
    }

    public String getSampleManufactor() {
        return sampleManufactor;
    }

    public void setSampleManufactor(String sampleManufactor) {
        this.sampleManufactor = sampleManufactor;
    }

    public String getSampleProducingArea() {
        return sampleProducingArea;
    }

    public void setSampleProducingArea(String sampleProducingArea) {
        this.sampleProducingArea = sampleProducingArea;
    }

    public Date getSampleProductionDate() {
        return sampleProductionDate;
    }

    public void setSampleProductionDate(Date sampleProductionDate) {
        this.sampleProductionDate = sampleProductionDate;
    }

    public String getSampleBatches() {
        return sampleBatches;
    }

    public void setSampleBatches(String sampleBatches) {
        this.sampleBatches = sampleBatches;
    }

    public String getInstrumentName() {
        return instrumentName;
    }

    public void setInstrumentName(String instrumentName) {
        this.instrumentName = instrumentName;
    }

    public String getInstrumentSN() {
        return instrumentSN;
    }

    public void setInstrumentSN(String instrumentSN) {
        this.instrumentSN = instrumentSN;
    }

    public String getInstrumentCategory() {
        return instrumentCategory;
    }

    public void setInstrumentCategory(String instrumentCategory) {
        this.instrumentCategory = instrumentCategory;
    }

    public String getInstrumentType() {
        return instrumentType;
    }

    public void setInstrumentType(String instrumentType) {
        this.instrumentType = instrumentType;
    }

    public String getInstrumentModel() {
        return instrumentModel;
    }

    public void setInstrumentModel(String instrumentModel) {
        this.instrumentModel = instrumentModel;
    }

    public String getInstrumentManufactor() {
        return instrumentManufactor;
    }

    public void setInstrumentManufactor(String instrumentManufactor) {
        this.instrumentManufactor = instrumentManufactor;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
