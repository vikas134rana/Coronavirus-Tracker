package com.vikas.coronavirustracker.controller;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.vikas.coronavirustracker.model.CoronavirusCase;
import com.vikas.coronavirustracker.model.CoronavirusCasesCountry;
import com.vikas.coronavirustracker.service.CoronavirusCasesService;

@Controller
public class ChartController {

	@Autowired
	CoronavirusCasesService reportedCasesService;

	@RequestMapping("/chart")
	private String chart(Model model, @RequestParam("chart_case") String chart_case) {

		if (chart_case.equals("reported")) {
			return reportedCasesChart(model);
		} else if (chart_case.equals("recovered")) {
			return recoveredCasesChart(model);
		} else if (chart_case.equals("death")) {
			return deathCasesChart(model);
		} else {
			return "";
		}
	}

	private String reportedCasesChart(Model model) {

		Set<CoronavirusCasesCountry> reportedCountriesCasesList = countriesCasesList();

		List<CoronavirusCasesCountry> topTotalCasesCountryList = reportedCountriesCasesList.stream().filter(r -> !r.getCountry().equals("Others"))
				.sorted((r1, r2) -> r2.getTotalReportedCases() - r1.getTotalReportedCases()).limit(5).collect(Collectors.toList());
		List<CoronavirusCasesCountry> topNewCasesCountryList = reportedCountriesCasesList.stream()
				.sorted((r1, r2) -> r2.getNewReportedCases() - r1.getNewReportedCases()).limit(5).collect(Collectors.toList());

		model.addAttribute("chart_case", "Reported");
		model.addAttribute("topTotalCasesCountries", topTotalCasesCountryList.stream().map(r->r.getCountry()).collect(Collectors.toList()).toArray());
		model.addAttribute("topTotalCasesData", topTotalCasesCountryList.stream().map(r->r.getTotalReportedCases()).collect(Collectors.toList()).toArray());
		
		model.addAttribute("topNewCasesCountries", topNewCasesCountryList.stream().map(r->r.getCountry()).collect(Collectors.toList()).toArray());
		model.addAttribute("topNewCasesData", topNewCasesCountryList.stream().map(r->r.getNewReportedCases()).collect(Collectors.toList()).toArray());
		
		return "chart";
	}

	private String recoveredCasesChart(Model model) {

		Set<CoronavirusCasesCountry> countriesCasesList = countriesCasesList();

		List<CoronavirusCasesCountry> topTotalCasesCountryList = countriesCasesList.stream().filter(r -> !r.getCountry().equals("Others"))
				.sorted((r1, r2) -> r2.getTotalRecoveredCases() - r1.getTotalRecoveredCases()).limit(5).collect(Collectors.toList());
		List<CoronavirusCasesCountry> topNewCasesCountryList = countriesCasesList.stream()
				.sorted((r1, r2) -> r2.getNewRecoveredCases() - r1.getNewRecoveredCases()).limit(5).collect(Collectors.toList());

		model.addAttribute("chart_case", "Recovered");
		model.addAttribute("topTotalCasesCountries", topTotalCasesCountryList.stream().map(r->r.getCountry()).collect(Collectors.toList()).toArray());
		model.addAttribute("topTotalCasesData", topTotalCasesCountryList.stream().map(r->r.getTotalRecoveredCases()).collect(Collectors.toList()).toArray());
		
		model.addAttribute("topNewCasesCountries", topNewCasesCountryList.stream().map(r->r.getCountry()).collect(Collectors.toList()).toArray());
		model.addAttribute("topNewCasesData", topNewCasesCountryList.stream().map(r->r.getNewRecoveredCases()).collect(Collectors.toList()).toArray());

		return "chart";
	}

	private String deathCasesChart(Model model) {

		Set<CoronavirusCasesCountry> countriesCasesList = countriesCasesList();

		List<CoronavirusCasesCountry> topTotalCasesCountryList = countriesCasesList.stream().filter(r -> !r.getCountry().equals("Others"))
				.sorted((r1, r2) -> r2.getTotalDeathCases() - r1.getTotalDeathCases()).limit(5).collect(Collectors.toList());
		List<CoronavirusCasesCountry> topNewCasesCountryList = countriesCasesList.stream()
				.sorted((r1, r2) -> r2.getNewDeathCases() - r1.getNewDeathCases()).limit(5).collect(Collectors.toList());

		model.addAttribute("chart_case", "Death");
		model.addAttribute("topTotalCasesCountries", topTotalCasesCountryList.stream().map(r->r.getCountry()).collect(Collectors.toList()).toArray());
		model.addAttribute("topTotalCasesData", topTotalCasesCountryList.stream().map(r->r.getTotalDeathCases()).collect(Collectors.toList()).toArray());
		
		model.addAttribute("topNewCasesCountries", topNewCasesCountryList.stream().map(r->r.getCountry()).collect(Collectors.toList()).toArray());
		model.addAttribute("topNewCasesData", topNewCasesCountryList.stream().map(r->r.getNewDeathCases()).collect(Collectors.toList()).toArray());

		return "chart";
	}

	private Set<CoronavirusCasesCountry> countriesCasesList() {
		List<CoronavirusCase> reportedCasesList = reportedCasesService.getCoronavirusCases();

		Set<CoronavirusCasesCountry> countriesCasesList = reportedCasesList.stream().collect(Collectors.groupingBy(CoronavirusCase::getCountry)).entrySet()
				.stream().collect(Collectors.toMap(x -> {
					int totalReportedCases = x.getValue().stream().mapToInt(CoronavirusCase::getTotalReportedCases).sum();
					int newReportedCases = x.getValue().stream().mapToInt(CoronavirusCase::getNewReportedCases).sum();
					int totalRecoveredCases = x.getValue().stream().mapToInt(CoronavirusCase::getTotalRecoveredCases).sum();
					int newRecoveredCases = x.getValue().stream().mapToInt(CoronavirusCase::getNewRecoveredCases).sum();
					int totalDeathCases = x.getValue().stream().mapToInt(CoronavirusCase::getTotalDeathCases).sum();
					int newDeathCases = x.getValue().stream().mapToInt(CoronavirusCase::getNewDeathCases).sum();
					return new CoronavirusCasesCountry(x.getKey(), totalReportedCases, newReportedCases, totalRecoveredCases, newRecoveredCases,
							totalDeathCases, newDeathCases);
				}, Map.Entry::getValue)).keySet();

		return countriesCasesList;
	}
}
