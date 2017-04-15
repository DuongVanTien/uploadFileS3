package com.duongvantien.service;

import com.duongvantien.dao.ICurrencyDAO;
import com.duongvantien.dao.IStaticDAO;
import com.duongvantien.entity.Currency;
import com.duongvantien.entity.Static;
import com.duongvantien.util.HibernateUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yahoofinance.YahooFinance;
import yahoofinance.quotes.fx.FxQuote;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
public class StaticService extends HibernateUtil implements IStaticService {

	private static final Logger logger = Logger.getLogger(StaticService.class);
	private static final String DEFAULT_CURRENCY = "VND";

	@Autowired
	private ICurrencyDAO currencyDAO;

	@Autowired
	private IStaticDAO staticDAO;

	public String save() throws Exception {
			List<Currency> currencies = currencyDAO.getAllHQL();
			String output = "";
			for (Currency currency : currencies) {
				Static oneStatic = new Static();
				oneStatic.setCurrencyId(currency.getId());
				oneStatic.setTime(new Date());
				float inRate = getRate(currency.getUnitName(), DEFAULT_CURRENCY).floatValue();
				float outRate = currency.getRateDefault() * inRate;
				oneStatic.setRateDynamic(outRate);
				staticDAO.save(oneStatic);
				output = output + new Date() + " \n (IN)  --- 1 " + currency.getUnitName() + " = " + inRate
						+ DEFAULT_CURRENCY + " \n (OUT) --- 1 " + currency.getUnitName() + " = " + outRate
						+ DEFAULT_CURRENCY + "\n";
			}
		return output;
	}

	/*
	 * inCurrency and outCurrency are currencies code of one country, that was
	 * international standard, ex: Vietnam is VND, United State is USD
	 */
	public BigDecimal getRate(String inCurrency, String outCurrency) throws IOException {
		FxQuote inPerOut = YahooFinance.getFx(inCurrency + outCurrency + "=X");
		return inPerOut.getPrice();
	}
}
