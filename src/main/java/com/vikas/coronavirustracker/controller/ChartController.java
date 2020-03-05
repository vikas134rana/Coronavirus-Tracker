package com.vikas.coronavirustracker.controller;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vikas.coronavirustracker.model.ReportedCase;
import com.vikas.coronavirustracker.model.ReportedCasesCountry;
import com.vikas.coronavirustracker.service.ReportedCasesService;

@Controller
public class ChartController {

	@Autowired
	ReportedCasesService reportedCasesService;

	@RequestMapping("/chart")
	public String pieChart(Model model) {
		List<ReportedCase> reportedCasesList = reportedCasesService.getReportedCases();

		Set<ReportedCasesCountry> reportedCountriesCasesList = reportedCasesList.stream().collect(Collectors.groupingBy(ReportedCase::getCountry)).entrySet()
				.stream().collect(Collectors.toMap(x -> {
					int totalCases = x.getValue().stream().mapToInt(ReportedCase::getTotalCases).sum();
					int newCases = x.getValue().stream().mapToInt(ReportedCase::getNewCases).sum();
					return new ReportedCasesCountry(x.getKey(), totalCases, newCases);
				}, Map.Entry::getValue)).keySet();

		
		List<ReportedCasesCountry> topTotalCasesCountryList = reportedCountriesCasesList.stream().filter(r->!r.getCountry().equals("Others")).sorted((r1,r2)->r2.getTotalCases()-r1.getTotalCases()).limit(5).collect(Collectors.toList());
		List<ReportedCasesCountry> topNewCasesCountryList = reportedCountriesCasesList.stream().sorted((r1,r2)->r2.getNewCases()-r1.getNewCases()).limit(5).collect(Collectors.toList());
		System.out.println("topTotalCasesCountryList: "+topTotalCasesCountryList);
		System.out.println("topNewCasesCountryList: "+topNewCasesCountryList);
		
		model.addAttribute("topTotalCasesCountryList", topTotalCasesCountryList);
		model.addAttribute("topNewCasesCountryList", topNewCasesCountryList);

		return "chart";
	}

}
