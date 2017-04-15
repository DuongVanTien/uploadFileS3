package com.duongvantien.entity;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * 
 */
@Entity
@Table(name = "static", catalog = "CurrencyMonitor")
public class Static implements java.io.Serializable {

	/**
	 * 
	 */
	private Long id;
	private Long currencyId;
	private float rateDynamic;
	private Date time;

	public Static() {
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

	@Column(name = "currency_id", nullable = false)
	public Long getCurrencyId() {
		return currencyId;
	}

	public void setCurrencyId(Long currencyId) {
		this.currencyId = currencyId;
	}

	@Column(name = "rate_dynamic")
	public float getRateDynamic() {
		return this.rateDynamic;
	}

	public void setRateDynamic(float rateDynamic) {
		this.rateDynamic = rateDynamic;
	}

	@Column(name = "time", length = 19)
	public Date getTime() {
		return this.time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

}
