package com.duongvantien.model;

import com.duongvantien.entity.Static;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.util.HashSet;
import java.util.Set;

public class CurrencyInfo {

	private Long id;
	private String name;
	private String unitName;
	private String icon;
	private float rateDefault;
	private Set<Static> statics = new HashSet<Static>(0);
	private CommonsMultipartFile image;

	public CurrencyInfo() {

	}

	public CurrencyInfo(Long id, String name, String unitName, String icon, float rateDefault, Set<Static> statics) {
		super();
		this.id = id;
		this.name = name;
		this.unitName = unitName;
		this.icon = icon;
		this.rateDefault = rateDefault;
		this.statics = statics;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public float getRateDefault() {
		return rateDefault;
	}

	public void setRateDefault(float rateDefault) {
		this.rateDefault = rateDefault;
	}

	public Set<Static> getStatics() {
		return statics;
	}

	public void setStatics(Set<Static> statics) {
		this.statics = statics;
	}

	public CommonsMultipartFile getImage() {
		return image;
	}

	public void setImage(CommonsMultipartFile image) {
		this.image = image;
	}
}
