package com.vikas.coronavirustracker.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.vikas.coronavirustracker.model.ReportedCase;
import com.vikas.coronavirustracker.service.ReportedCasesService;

@Controller
public class CountryWiseCasesController {

	@Autowired 
	ReportedCasesService reportedCasesService;
	
	@RequestMapping("/country_wise_cases")
	public String country(@RequestParam("country") String country,Model model) {
		
		List<ReportedCase> reportedCasesList = reportedCasesService.getReportedCases();
		List<ReportedCase> reportedCaseCountryList = reportedCasesList.stream().filter(r->r.getCountry().equals(country)).collect(Collectors.toList());
		int totalReportedCountryCases = reportedCaseCountryList.stream().mapToInt(r->r.getTotalCases()).sum();
		int totalNewReportedCountryCases = reportedCaseCountryList.stream().mapToInt(r->r.getNewCases()).sum();
		
		model.addAttribute("country", country);
		model.addAttribute("reportedCaseCountryList", reportedCaseCountryList);
		model.addAttribute("totalReportedCountryCases", totalReportedCountryCases);
		model.addAttribute("totalNewReportedCountryCases", totalNewReportedCountryCases);
		
		return "country";
	}
}
