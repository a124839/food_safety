package com.ichinait.service.dto;

public class DataDto {
	private Double x;
	private Double y;
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
	
	@Override
	public String toString() {
		return "x:"+this.x+",y:"+this.y;
	}
	
	
	
	public static void main(String[] args) {
		DataDto data = new DataDto();
		data.setX(9d);
		data.setY(10d);
		System.out.println(data.toString());
	}
}
