package com.indeconAC.apacheCamel.models;

import java.io.Serializable;
import java.util.HashMap;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Values implements Serializable{
    private String key;
	private String name;
	private String unit;

	private HashMap<String,Double> values;

    public Values() {
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

    public HashMap<String, Double> getValues() {
        return values;
    }

    public void setValues(HashMap<String, Double> values) {
        this.values = values;
    }

}
