package com.duongvantien.model;

public class RateInfo {

	private String unitName;
	private String rate;
	private String date;

	public RateInfo() {

	}

	public RateInfo(String unitName, String rate, String date) {
		super();
		this.unitName = unitName;
		this.rate = rate;
		this.date = date;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public String getRate() {
		return rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

}
