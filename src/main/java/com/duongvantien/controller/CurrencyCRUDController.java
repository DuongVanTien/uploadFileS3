package com.duongvantien.controller;

import com.duongvantien.model.CurrencyInfo;
import com.duongvantien.service.ICurrencyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Locale;

@Controller
@RequestMapping(value = "/currencies")
public class CurrencyCRUDController {

	private static final Logger logger = LoggerFactory.getLogger(CurrencyController.class);
	private static final String SUFFIX = ".JPG";

	@Autowired
	private ICurrencyService currencyService;

	@Autowired
	@Qualifier("currencyValidator")
	private Validator validator;

	@InitBinder
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(validator);
	}

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String getAll(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		model.addAttribute("currenciesList", currencyService.getAll());
		return "listCurrencies";
	}

	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public String create(Model model, Locale locale) {
		logger.info("Welcome home! The client locale is {}.", locale);
		CurrencyInfo obj = new CurrencyInfo();
		model.addAttribute("currencyForm", obj);
		return "createCurrency";
	}

	@RequestMapping(value = "", method = RequestMethod.POST)
	public ModelAndView create(Model model,
			@ModelAttribute(value = "currencyForm") @Validated CurrencyInfo currencyForm, BindingResult result,
			Locale locale) throws Exception {
		logger.info("Welcome home! The client locale is {}.", locale);
		if (result.hasErrors()) {
			return new ModelAndView("createCurrency");
		}

		currencyForm.setId(currencyService.add(currencyForm));
		String urlIcon = currencyService.upToS3(currencyForm.getImage(),
				CurrencyCRUDController.class.getProtectionDomain().getCodeSource().getLocation().toString(),
				currencyForm.getId().toString() + SUFFIX);
		currencyForm.setIcon(urlIcon);
		currencyService.update(currencyForm);
		return new ModelAndView("redirect:/currencies");
	}

	@RequestMapping(value = "/{currencyId}/edit", method = RequestMethod.GET)
	public String update(@PathVariable(value = "currencyId") Long currencyId, Model model, Locale locale) {
		logger.info("Welcome home! The client locale is {}.", locale);
		if (currencyId != null) {
			CurrencyInfo currencyInfo = currencyService.findById(currencyId);
			model.addAttribute("currencyForm", currencyInfo);
			model.addAttribute("currencyId", currencyInfo.getId());
			return "updateCurrency";
		} else {
			return "redirect:/currencies";
		}

	}

	@RequestMapping(value = "/{currencyId}", method = RequestMethod.POST)
	public String update(Model model, @ModelAttribute(value = "currencyForm") @Validated CurrencyInfo currencyForm,
			BindingResult result, Locale locale) throws Exception {
		logger.info("Welcome home! The client locale is {}.", locale);
		if (result.hasErrors()) {
			return "updateCurrency";
		}
		String urlIcon = currencyService.upToS3(currencyForm.getImage(),
				CurrencyCRUDController.class.getProtectionDomain().getCodeSource().getLocation().toString(),
				currencyForm.getId().toString() + SUFFIX);
		currencyForm.setIcon(urlIcon);
		currencyService.update(currencyForm);
		return "redirect:/currencies";
	}

}