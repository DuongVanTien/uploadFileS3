package com.duongvantien.dao;

import com.duongvantien.entity.Static;

import java.util.List;

public interface IStaticDAO extends IGenericDAO<Static, Long> {

	public List<Static> findByCurrencyId(Long currencyId);
}

