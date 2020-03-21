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

		List<String> countryList = coronavirusCasesList.stream().map(r -> r.getCountry()).sorted().distinct().collect(Collectors.toList());

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

		model.addAttribute("countryList", countryList);

		return "home";
	}

	@RequestMapping("/home")
	public String homeWithSort(Model model, @RequestParam("sortby") String sortby) {

		List<CoronavirusCase> tempCoronavirusCasesList = coronavirusCasesService.getCoronavirusCases();
		List<CoronavirusCase> coronavirusCasesList;

		Comparator<CoronavirusCase> comparator = getComparator(sortby);

		coronavirusCasesList = tempCoronavirusCasesList.stream().sorted(comparator).collect(Collectors.toList());

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

	private Comparator<CoronavirusCase> getComparator(String sortby) {

		Comparator<CoronavirusCase> c = null;

		if (sortby.contains("state")) {
			c = Comparator.comparing(CoronavirusCase::getState);
		} else if (sortby.contains("country")) {
			c = Comparator.comparing(CoronavirusCase::getCountry);
		} else if (sortby.contains("totalReportedCase")) {
			c = Comparator.comparing(CoronavirusCase::getTotalReportedCases);
		} else if (sortby.contains("newReportedCase")) {
			c = Comparator.comparing(CoronavirusCase::getNewReportedCases);
		} else if (sortby.contains("totalRecoveredCase")) {
			c = Comparator.comparing(CoronavirusCase::getTotalRecoveredCases);
		} else if (sortby.contains("newRecoveredCase")) {
			c = Comparator.comparing(CoronavirusCase::getNewRecoveredCases);
		} else if (sortby.contains("totalDeathCase")) {
			c = Comparator.comparing(CoronavirusCase::getTotalDeathCases);
		} else if (sortby.contains("newDeathCase")) {
			c = Comparator.comparing(CoronavirusCase::getNewDeathCases);
		}

		if (sortby.contains("desc"))
			c = c.reversed();

		return c;
	}

}
