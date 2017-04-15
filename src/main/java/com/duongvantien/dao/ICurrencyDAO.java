package com.duongvantien.dao;

import com.duongvantien.entity.Currency;

import java.util.List;

public interface ICurrencyDAO extends IGenericDAO<Currency, Long> {

	public Currency findByUnitName(String unitName);


	public List<Currency> getAllHQL();

	public Currency updateHQL(Currency currency);

	public Currency add(Currency currency);

	public Currency findByIdHQL(Long id);

}
