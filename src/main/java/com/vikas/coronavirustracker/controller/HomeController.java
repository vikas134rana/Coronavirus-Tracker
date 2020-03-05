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
public class HomeController {

	@Autowired
	ReportedCasesService reportedCasesService;

	@RequestMapping("/")
	public String home(Model model) {

		List<ReportedCase> reportedCasesList = reportedCasesService.getReportedCases();
		int totalReportedCases = reportedCasesList.stream().mapToInt(r -> r.getTotalCases()).sum();
		int totalNewCases = reportedCasesList.stream().mapToInt(r -> r.getNewCases()).sum();

		model.addAttribute("totalReportedCases", totalReportedCases);
		model.addAttribute("totalNewCases", totalNewCases);
		model.addAttribute("reportedCasesList", reportedCasesList);

		return "home";
	}

	@RequestMapping("/home")
	public String homeWithSort(Model model, @RequestParam("sortby") String sortby) {

		List<ReportedCase> tempReportedCasesList = reportedCasesService.getReportedCases();
		List<ReportedCase> reportedCasesList;

		if (sortby.contains("state")) {
			if (sortby.contains("desc")) {
				reportedCasesList = tempReportedCasesList.stream().sorted((r1, r2) -> r2.getState().compareTo(r1.getState())).collect(Collectors.toList());
			} else {
				reportedCasesList = tempReportedCasesList.stream().sorted((r1, r2) -> r1.getState().compareTo(r2.getState())).collect(Collectors.toList());
			}
		} else if (sortby.contains("country")) {
			if (sortby.contains("desc")) {
				reportedCasesList = tempReportedCasesList.stream().sorted((r1, r2) -> r2.getCountry().compareTo(r1.getCountry())).collect(Collectors.toList());
			} else {
				reportedCasesList = tempReportedCasesList.stream().sorted((r1, r2) -> r1.getCountry().compareTo(r2.getCountry())).collect(Collectors.toList());
			}
		} else if (sortby.contains("totalCase")) {
			if (sortby.contains("desc")) {
				reportedCasesList = tempReportedCasesList.stream().sorted((r1, r2) -> r2.getTotalCases() - r1.getTotalCases()).collect(Collectors.toList());
			} else {
				reportedCasesList = tempReportedCasesList.stream().sorted((r1, r2) -> r1.getTotalCases() - r2.getTotalCases()).collect(Collectors.toList());
			}
		} else if (sortby.contains("newCase")) {
			if (sortby.contains("desc")) {
				reportedCasesList = tempReportedCasesList.stream().sorted((r1, r2) -> r2.getNewCases() - r1.getNewCases()).collect(Collectors.toList());
			} else {
				reportedCasesList = tempReportedCasesList.stream().sorted((r1, r2) -> r1.getNewCases() - r2.getNewCases()).collect(Collectors.toList());
			}
		} else {
			reportedCasesList = tempReportedCasesList;
		}

		int totalReportedCases = reportedCasesList.stream().mapToInt(r -> r.getTotalCases()).sum();
		int totalNewCases = reportedCasesList.stream().mapToInt(r -> r.getNewCases()).sum();

		model.addAttribute("totalReportedCases", totalReportedCases);
		model.addAttribute("totalNewCases", totalNewCases);
		model.addAttribute("reportedCasesList", reportedCasesList);

		return "home";
	}

}
