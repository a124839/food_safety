package com.ichinait.food.dto.dataAnalysis;

import java.util.Arrays;

public class ModelCalibrationResultDTO {
		
	//模型转移算法后的光谱矩阵
	private double[][] result;
	
	private AlgorithmPdsDTO pdsDto;
	
	private AlgorithmSstDTO sstDTO;
	
	public double[][] getResult() {
		return result;
	}

	public void setResult(double[][] result) {
		this.result = result;
	}

	public AlgorithmPdsDTO getPdsDto() {
		return pdsDto;
	}

	public void setPdsDto(AlgorithmPdsDTO pdsDto) {
		this.pdsDto = pdsDto;
	}

	public AlgorithmSstDTO getSstDTO() {
		return sstDTO;
	}

	public void setSstDTO(AlgorithmSstDTO sstDTO) {
		this.sstDTO = sstDTO;
	}

	@Override
	public String toString() {
		return "ModelCalibrationResultDTO [result=" + Arrays.toString(result) + ", pdsDto=" + pdsDto + ", sstDTO="
				+ sstDTO + "]";
	}
	
}
