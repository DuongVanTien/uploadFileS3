package com.duongvantien.service;

import com.duongvantien.model.CurrencyInfo;
import com.duongvantien.model.RateInfo;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.util.List;

public interface ICurrencyService {

	public List<RateInfo> getRate(String unitName, String date);

	public boolean update(CurrencyInfo currencyInfo) throws Exception;

	public Long add(CurrencyInfo currencyInfo) throws Exception;
	
	public List<CurrencyInfo> getAll();
	
	public CurrencyInfo findById(Long id);

	public String upToS3(CommonsMultipartFile imageFile, String localUrl, String fileName);
	
	public List<RateInfo> getRate(String unitName, int length);
}
