package com.vikas.coronavirustracker.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.vikas.coronavirustracker.model.CoronavirusCase;
import com.vikas.coronavirustracker.service.CoronavirusCasesService;

@Controller
public class CountryWiseCasesController {

	@Autowired
	CoronavirusCasesService coronavirusCasesService;

	@RequestMapping("/country_wise_cases")
	public String country(@RequestParam("country") String country, Model model) {

		List<CoronavirusCase> coronavirusCaseList = coronavirusCasesService.getCoronavirusCases();
		List<CoronavirusCase> coronavirusCaseCountryList = coronavirusCaseList.stream().filter(r -> r.getCountry().equals(country)).collect(Collectors.toList());

		int totalReportedCases = coronavirusCaseCountryList.stream().mapToInt(r -> r.getTotalReportedCases()).sum();
		int totalNewReportedCases = coronavirusCaseCountryList.stream().mapToInt(r -> r.getNewReportedCases()).sum();

		int totalRecoveredCases = coronavirusCaseCountryList.stream().mapToInt(r -> r.getTotalRecoveredCases()).sum();
		int totalNewRecoveredCases = coronavirusCaseCountryList.stream().mapToInt(r -> r.getNewRecoveredCases()).sum();

		int totalDeathCases = coronavirusCaseCountryList.stream().mapToInt(r -> r.getTotalDeathCases()).sum();
		int totalNewDeathCases = coronavirusCaseCountryList.stream().mapToInt(r -> r.getNewDeathCases()).sum();

		model.addAttribute("country", country);
		model.addAttribute("totalReportedCases", totalReportedCases);
		model.addAttribute("totalNewReportedCases", totalNewReportedCases);
		model.addAttribute("totalRecoveredCases", totalRecoveredCases);
		model.addAttribute("totalNewRecoveredCases", totalNewRecoveredCases);
		model.addAttribute("totalDeathCases", totalDeathCases);
		model.addAttribute("totalNewDeathCases", totalNewDeathCases);
		model.addAttribute("coronavirusCaseCountryList", coronavirusCaseCountryList);

		return "country";
	}
}
