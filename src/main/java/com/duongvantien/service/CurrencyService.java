package com.duongvantien.service;

import com.duongvantien.dao.ICurrencyDAO;
import com.duongvantien.dao.IStaticDAO;
import com.duongvantien.entity.Currency;
import com.duongvantien.entity.Static;
import com.duongvantien.model.CurrencyInfo;
import com.duongvantien.model.RateInfo;
import com.duongvantien.util.HibernateUtil;
import com.duongvantien.util.S3Util;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class CurrencyService extends HibernateUtil implements ICurrencyService {
	private static final Logger logger = Logger.getLogger(StaticService.class);

	@Autowired
	private ICurrencyDAO currencyDAO;

	@Autowired
	private IStaticDAO staticDAO;

	public List<RateInfo> getRate(String unitName, String date) {
		List<RateInfo> rateInfos = new ArrayList<RateInfo>();
		try {
			Currency currency = currencyDAO.findByUnitName(unitName);
			if (currency == null)
				return rateInfos;
			for (Static oneStatic : staticDAO.findByCurrencyId(currency.getId())) {
				if (date.equals(new SimpleDateFormat("MM-dd-yyyy").format(oneStatic.getTime()))) {

					rateInfos.add(new RateInfo(currency.getUnitName(), "" + oneStatic.getRateDynamic(),
							"" + oneStatic.getTime()));
				}
			}
		} catch (Exception e) {
			logger.error("getRate@CurrencyService Error: ", e);
		}
		return rateInfos;
	}

	public boolean update(CurrencyInfo currencyInfo) throws Exception {
		if (currencyInfo != null) {
			Currency currency = new Currency();
			BeanUtils.copyProperties(currencyInfo, currency);
			currencyDAO.updateHQL(currency);
			return true;
		}
		return false;
	}

	public Long add(CurrencyInfo currencyInfo) throws Exception {
		if (currencyInfo != null) {
			Currency currency = new Currency();
			BeanUtils.copyProperties(currencyInfo, currency);
			Currency result = currencyDAO.add(currency);
			return result.getId();
		}
		return null;
	}

	public List<CurrencyInfo> getAll() {
		List<CurrencyInfo> currencyInfos = new ArrayList<CurrencyInfo>();
		try {
			List<Currency> currencies = currencyDAO.getAllHQL();
			for (Currency currency : currencies) {
				CurrencyInfo currencyInfo = new CurrencyInfo();
				BeanUtils.copyProperties(currency, currencyInfo);
				currencyInfos.add(currencyInfo);
			}
		} catch (Exception e) {
			logger.error("getAll@CurrencyService Error: ", e);
		}

		return currencyInfos;
	}

	public CurrencyInfo findById(Long id) {
		CurrencyInfo currencyInfo = new CurrencyInfo();
		try {
			BeanUtils.copyProperties(currencyDAO.findByIdHQL(id), currencyInfo);
		} catch (Exception e) {
			logger.error("findById@CurrencyService Error: ", e);
		}
		return currencyInfo;
	}

	public String upToS3(CommonsMultipartFile imageFile, String localUrl, String fileName) {
		try {
			String directUrl = localUrl.substring(5, localUrl.length()) + fileName;
			imageFile.transferTo(new File(directUrl));
			return new S3Util().upload(fileName, directUrl);

		} catch (Exception e) {
			logger.error("upToS3@CurrencyService Error: ", e);
			return "";
		}

	}
	public List<RateInfo> getRate(String unitName, int length) {
		List<RateInfo> rateInfos = new ArrayList<RateInfo>();
		try {
			Currency currency = currencyDAO.findByUnitName(unitName);
			if (currency == null)
				return rateInfos;

			for (Static oneStatic : staticDAO.findByCurrencyId(currency.getId())) {
				rateInfos.add(new RateInfo(currency.getUnitName(), "" + oneStatic.getRateDynamic(),
						"" + oneStatic.getTime()));
			}
		} catch (Exception e) {
			logger.error("getRate@CurrencyService Error: ", e);
		}
		if (rateInfos.size() < length)
			return rateInfos;
		return rateInfos.subList(0, length);
	}
}
