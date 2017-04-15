package com.duongvantien.entity;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * @author FRAMGIA\do.khanh.toan
 */
@Entity
@Table(name = "currency", catalog = "CurrencyMonitor")
public class Currency implements java.io.Serializable {

	/**
	 * 
	 */
	private Long id;
	private String name;
	private String unitName;
	private String icon;
	private float rateDefault;

	public Currency() {
		super();
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "name", nullable = false, length = 50)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "unit_name", nullable = false, length = 50)
	public String getUnitName() {
		return this.unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	@Column(name = "icon", length = 200)
	public String getIcon() {
		return this.icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	@Column(name = "rate_default")
	public float getRateDefault() {
		return this.rateDefault;
	}

	public void setRateDefault(float rateDefault) {
		this.rateDefault = rateDefault;
	}

}
