package com.ichinait.food.db.entity.plus;

import java.util.Date;

public class DataPlus {
	private String id;
	private String projectName;
	private String instrumentName;
	private String x;
	private String y;
	private String sampleName;
	private String resolution;

	private String wavelengthRange;

	private Integer scanningTimes;

	private String scanningDuration;


	private String fileName;
	private String uploader;
	private Date createTime;
	private String attachmentId;
	
	
	
	public String getAttachmentId() {
		return attachmentId;
	}

	public void setAttachmentId(String attachmentId) {
		this.attachmentId = attachmentId;
	}

	public String getInstrumentName() {
		return instrumentName;
	}

	public void setInstrumentName(String instrumentName) {
		this.instrumentName = instrumentName;
	}

	public String getResolution() {
		return resolution;
	}

	public void setResolution(String resolution) {
		this.resolution = resolution;
	}

	public String getWavelengthRange() {
		return wavelengthRange;
	}

	public void setWavelengthRange(String wavelengthRange) {
		this.wavelengthRange = wavelengthRange;
	}

	public Integer getScanningTimes() {
		return scanningTimes;
	}

	public void setScanningTimes(Integer scanningTimes) {
		this.scanningTimes = scanningTimes;
	}

	public String getScanningDuration() {
		return scanningDuration;
	}

	public void setScanningDuration(String scanningDuration) {
		this.scanningDuration = scanningDuration;
	}

	public String getSampleName() {
		return sampleName;
	}

	public void setSampleName(String sampleName) {
		this.sampleName = sampleName;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getIntrumentName() {
		return instrumentName;
	}
	public void setIntrumentName(String intrumentName) {
		this.instrumentName = intrumentName;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getUploader() {
		return uploader;
	}
	public void setUploader(String uploader) {
		this.uploader = uploader;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;
    }

    public String getY() {
        return y;
    }

    public void setY(String y) {
        this.y = y;
    }

}
