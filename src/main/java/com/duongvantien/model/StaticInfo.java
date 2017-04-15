package com.duongvantien.model;

import com.duongvantien.entity.Currency;

import java.util.Date;

public class StaticInfo {
	
	private Integer id;
	private Currency currency;
	private float rateDynamic;
	private Date time;
	
	public StaticInfo() {
		super();
	}

	public StaticInfo(Integer id, Currency currency, float rateDynamic, Date time) {
		super();
		this.id = id;
		this.currency = currency;
		this.rateDynamic = rateDynamic;
		this.time = time;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Currency getCurrency() {
		return currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}

	public float getRateDynamic() {
		return rateDynamic;
	}

	public void setRateDynamic(float rateDynamic) {
		this.rateDynamic = rateDynamic;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}
	
	

}
