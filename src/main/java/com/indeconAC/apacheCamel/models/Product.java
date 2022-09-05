package com.indeconAC.apacheCamel.models;

public class Product {

	private String key;
	private String name;
	private String unit;
	
	private Long date;
	private Double value;
	
	public Product() {
		
	}
	public Product(String key, String name, String unit, Long date, Double value) {
		this.key = key;
		this.name = name;
		this.unit = unit;
		this.date = date;
		this.value = value;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public Long getDate() {
		return date;
	}
	public void setDate(Long date) {
		this.date = date;
	}
	public Double getValue() {
		return value;
	}
	public void setValue(Double value) {
		this.value = value;
	}
	
}
