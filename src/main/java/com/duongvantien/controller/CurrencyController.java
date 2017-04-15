package com.duongvantien.controller;

import com.duongvantien.model.RateInfo;
import com.duongvantien.service.ICurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Controller
@RequestMapping(value = "/rest")
public class CurrencyController {

	private static final int CHART_LENGTH = 30;
	
	@Autowired
	private ICurrencyService currencyService;

	@RequestMapping(value = "/{unitName}/{date}.xml", method = RequestMethod.GET, produces = {
			MediaType.APPLICATION_XML_VALUE })
	@ResponseBody
	public List<RateInfo> getRateAsXML(@PathVariable("unitName") String unitName, @PathVariable("date") String date) {
		List<RateInfo> rateInfos = new ArrayList<RateInfo>();
		if (unitName == null || date == null) {
			rateInfos.add(new RateInfo("nodata", "nodata", "nodata"));
			return rateInfos;
		}
		rateInfos = currencyService.getRate(unitName, date);
		return rateInfos;

	}

	@RequestMapping(value = "/{unitName}/{date}.json", method = RequestMethod.GET, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public List<RateInfo> getRateAsJson(@PathVariable("unitName") String unitName, @PathVariable("date") String date) {

		List<RateInfo> rateInfos = new ArrayList<RateInfo>();
		if (unitName != null || date != null) {
			rateInfos.add(new RateInfo("nodata", "nodata", "nodata"));
		}
		rateInfos = currencyService.getRate(unitName, date);
		return rateInfos;
	}

	@RequestMapping(value = "/{unitName}/{date}.xls")
	public ModelAndView getRateAsXLS(@PathVariable("unitName") String unitName, @PathVariable("date") String date) {
		List<RateInfo> rateInfos = new ArrayList<RateInfo>();
		if (unitName == null || date == null || date.equals("") || unitName.equals("")) {
			return new ModelAndView("rateExcelView", "listRates", rateInfos);
		}
		return new ModelAndView("rateExcelView", "listRates", currencyService.getRate(unitName, date));
	}

	@RequestMapping(value = "/{unitName}/{firstDate}/{lastDate}.xls")
	public ModelAndView getRateBetweenDateAsXLS(@PathVariable("unitName") String unitName,
			@PathVariable("firstDate") String firstDate, @PathVariable("lastDate") String lastDate)
			throws ParseException {
		List<RateInfo> rateInfos = new ArrayList<RateInfo>();
		if (unitName == null || firstDate == null || firstDate.equals("") || unitName.equals("") || lastDate == null
				|| lastDate.equals("")) {
			return new ModelAndView("rateExcelView", "listRates", rateInfos);
		}

		SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");

		if (sdf.parse(firstDate).before(sdf.parse(lastDate))) {
			rateInfos = currencyService.getRate(unitName, firstDate);
			String currentDate = firstDate;
			while (!currentDate.equals(lastDate)) {

				Calendar c = Calendar.getInstance();
				c.setTime(sdf.parse(currentDate));
				c.add(Calendar.DATE, 1);
				currentDate = sdf.format(c.getTime());

				List<RateInfo> rateInfosCurent = new ArrayList<RateInfo>();
				rateInfosCurent = currencyService.getRate(unitName, currentDate);
				for (RateInfo rateInfo : rateInfosCurent) {
					rateInfos.add(rateInfo);
				}
			}
		}

		return new ModelAndView("rateExcelView", "listRates", rateInfos);
	}
	@RequestMapping(value = "/{unitName}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public List<RateInfo> getRateForChart(@PathVariable("unitName") String unitName) {

		List<RateInfo> rateInfos = new ArrayList<RateInfo>();
		if (unitName != null) {
			rateInfos.add(new RateInfo("nodata", "nodata", "nodata"));
		}
		return currencyService.getRate(unitName, CHART_LENGTH);
	}

}
