package com.duongvantien.dao;

import com.duongvantien.entity.Currency;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CurrencyDAO extends GenericDAO<Currency, Long> implements ICurrencyDAO {
	/**
	 * by tungtv
	 */

	public CurrencyDAO() {
		super(Currency.class);
	}

	public Currency findByUnitName(String unitName) {
		Criteria criteria = getCurrentSession().createCriteria(getPersistentClass());
		criteria.add(Restrictions.eq("unitName", unitName));
		Object obj = criteria.uniqueResult();
		if (obj == null) {
			return null;
		}

		return (Currency) obj;
	}
	
	public Currency updateHQL(Currency currency) {
		String sql = "update " + Currency.class.getName()
				+ " set name =:name, unitName =:unitName, icon =:icon, rateDefault = :rateDefault" + " where id =:id";
		Query query = getCurrentSession().createQuery(sql);
		query.setParameter("name", currency.getName());
		query.setParameter("unitName", currency.getUnitName());
		query.setParameter("icon", currency.getIcon());
		query.setParameter("rateDefault", currency.getRateDefault());
		query.setParameter("id", currency.getId());
		query.executeUpdate();
		return currency;
	}

	public Currency add(Currency currency) {
		getCurrentSession().save(currency);
		getCurrentSession().flush();
		return currency;
	}

	public List<Currency> getAllHQL() {
		String sql = "select c from " + Currency.class.getName() + " c " + " order by c.id";
		Query query = getCurrentSession().createQuery(sql);
		return query.list();
	}

	public Currency findByIdHQL(Long id) {
		String sql = "select c from " + Currency.class.getName() + " c " + " where c.id =:id";
		Query query = getCurrentSession().createQuery(sql);
		query.setParameter("id", id);
		Object obj = query.uniqueResult();
		if (obj == null)
			return null;
		return (Currency) obj;

	}
}
