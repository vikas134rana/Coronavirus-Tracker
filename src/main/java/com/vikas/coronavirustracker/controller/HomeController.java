package com.vikas.coronavirustracker.controller;

import java.util.Comparator;
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
public class HomeController {

	@Autowired
	CoronavirusCasesService coronavirusCasesService;

	@RequestMapping("/")
	public String home(Model model) {

		List<CoronavirusCase> coronavirusCasesList = coronavirusCasesService.getCoronavirusCases();
		int totalReportedCases = coronavirusCasesList.stream().mapToInt(r -> r.getTotalReportedCases()).sum();
		int totalNewReportedCases = coronavirusCasesList.stream().mapToInt(r -> r.getNewReportedCases()).sum();

		int totalRecoveredCases = coronavirusCasesList.stream().mapToInt(r -> r.getTotalRecoveredCases()).sum();
		int totalNewRecoveredCases = coronavirusCasesList.stream().mapToInt(r -> r.getNewRecoveredCases()).sum();

		int totalDeathCases = coronavirusCasesList.stream().mapToInt(r -> r.getTotalDeathCases()).sum();
		int totalNewDeathCases = coronavirusCasesList.stream().mapToInt(r -> r.getNewDeathCases()).sum();

		model.addAttribute("totalReportedCases", totalReportedCases);
		model.addAttribute("totalNewReportedCases", totalNewReportedCases);
		model.addAttribute("totalRecoveredCases", totalRecoveredCases);
		model.addAttribute("totalNewRecoveredCases", totalNewRecoveredCases);
		model.addAttribute("totalDeathCases", totalDeathCases);
		model.addAttribute("totalNewDeathCases", totalNewDeathCases);
		model.addAttribute("coronavirusCasesList", coronavirusCasesList);

		return "home";
	}

	@RequestMapping("/home")
	public String homeWithSort(Model model, @RequestParam("sortby") String sortby) {

		List<CoronavirusCase> tempCoronavirusCasesList = coronavirusCasesService.getCoronavirusCases();
		List<CoronavirusCase> coronavirusCasesList;

		Comparator<CoronavirusCase> comparator = (r1, r2) -> {

			if (sortby.contains("state")) {
				return r1.getState().compareTo(r2.getState());
			} else if (sortby.contains("country")) {
				return r1.getCountry().compareTo(r2.getCountry());
			} else if (sortby.contains("totalReportedCase")) {
				return r1.getTotalReportedCases() - r2.getTotalReportedCases();
			} else if (sortby.contains("newReportedCase")) {
				return r1.getNewReportedCases() - r2.getNewReportedCases();
			} else if (sortby.contains("totalRecoveredCase")) {
				return r1.getTotalRecoveredCases() - r2.getTotalRecoveredCases();
			} else if (sortby.contains("newRecoveredCase")) {
				return r1.getNewRecoveredCases() - r2.getNewRecoveredCases();
			} else if (sortby.contains("totalDeathCase")) {
				return r1.getTotalDeathCases() - r2.getTotalDeathCases();
			} else if (sortby.contains("newDeathCase")) {
				return r1.getNewDeathCases() - r2.getNewDeathCases();
			}
			return 0;
		};

		if (sortby.contains("desc")) {
			coronavirusCasesList = tempCoronavirusCasesList.stream().sorted(comparator.reversed()).collect(Collectors.toList());
		} else {
			coronavirusCasesList = tempCoronavirusCasesList.stream().sorted(comparator).collect(Collectors.toList());
		}

		int totalReportedCases = coronavirusCasesList.stream().mapToInt(r -> r.getTotalReportedCases()).sum();
		int totalNewReportedCases = coronavirusCasesList.stream().mapToInt(r -> r.getTotalReportedCases()).sum();

		int totalRecoveredCases = coronavirusCasesList.stream().mapToInt(r -> r.getTotalRecoveredCases()).sum();
		int totalNewRecoveredCases = coronavirusCasesList.stream().mapToInt(r -> r.getTotalRecoveredCases()).sum();

		int totalDeathCases = coronavirusCasesList.stream().mapToInt(r -> r.getTotalDeathCases()).sum();
		int totalNewDeathCases = coronavirusCasesList.stream().mapToInt(r -> r.getTotalDeathCases()).sum();

		model.addAttribute("totalReportedCases", totalReportedCases);
		model.addAttribute("totalNewReportedCases", totalNewReportedCases);
		model.addAttribute("totalRecoveredCases", totalRecoveredCases);
		model.addAttribute("totalNewRecoveredCases", totalNewRecoveredCases);
		model.addAttribute("totalDeathCases", totalDeathCases);
		model.addAttribute("totalNewDeathCases", totalNewDeathCases);
		model.addAttribute("coronavirusCasesList", coronavirusCasesList);

		return "home";
	}

}
