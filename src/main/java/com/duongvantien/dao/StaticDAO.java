package com.duongvantien.dao;

import com.duongvantien.entity.Static;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StaticDAO extends GenericDAO<Static, Long> implements IStaticDAO {
	/**
	 * by tungtv
	 */

	public StaticDAO() {
		super(Static.class);
	}

	public List<Static> findByCurrencyId(Long currencyId) {
		Criteria criteria = getCurrentSession().createCriteria(getPersistentClass());
		criteria.add(Restrictions.eq("currencyId", currencyId));
		Object obj = criteria.list();
		if (obj == null) {
			return null;
		}
		return (List<Static>) obj;

	}

}
