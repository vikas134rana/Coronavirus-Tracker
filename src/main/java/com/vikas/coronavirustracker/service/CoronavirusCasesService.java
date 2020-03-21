package com.vikas.coronavirustracker.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.vikas.coronavirustracker.model.CoronavirusCase;

@Service

public class CoronavirusCasesService {

	// https://github.com/CSSEGISandData/COVID-19/tree/master

	private static final String REPORTED_CASES_URL = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_19-covid-Confirmed.csv";
	private static final String RECOVERED_CASES_URL = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_19-covid-Recovered.csv";
	private static final String DEATHS_CASES_URL = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_19-covid-Deaths.csv";

	private List<CoronavirusCase> coronavirusCaseList = new ArrayList<CoronavirusCase>();

	public List<CoronavirusCase> getCoronavirusCases() {
		return coronavirusCaseList;
	}

	@PostConstruct
	@Scheduled(cron = "0 0 0 * * ?")
	public void start() {

		List<CSVRecord> reportedRecords = getRecords(REPORTED_CASES_URL);
		List<CSVRecord> recoveredRecords = getRecords(RECOVERED_CASES_URL);
		List<CSVRecord> deathsRecords = getRecords(DEATHS_CASES_URL);

		for (int i = 0; i < reportedRecords.size(); i++) {

			CSVRecord reportedRecord = reportedRecords.get(i);
			CSVRecord recoveredRecord = recoveredRecords.get(i);
			CSVRecord deathsRecord = deathsRecords.get(i);

			String state = reportedRecord.get("Province/State");
			String country = reportedRecord.get("Country/Region");

			String totalReportedCases_s = reportedRecord.get(reportedRecord.size() - 1);
			int totalReportedCases = totalReportedCases_s.isEmpty() ? 0 : Integer.parseInt(totalReportedCases_s);
			String previousReportedCases_s = reportedRecord.get(reportedRecord.size() - 2);
			int previousReportedCases = previousReportedCases_s.isEmpty() ? 0 : Integer.parseInt(previousReportedCases_s);
			int newReportedCases = totalReportedCases - previousReportedCases;

			String totalRecoveredCases_s = recoveredRecord.get(reportedRecord.size() - 1);
			int totalRecoveredCases = totalRecoveredCases_s.isEmpty() ? 0 : Integer.parseInt(totalRecoveredCases_s);
			String previousRecoveredCases_s = recoveredRecord.get(reportedRecord.size() - 2);
			int previousRecoveredCases = previousRecoveredCases_s.isEmpty() ? 0 : Integer.parseInt(previousRecoveredCases_s);
			int newRecoveredCases = totalRecoveredCases - previousRecoveredCases;

			String totalDeathCases_s = deathsRecord.get(reportedRecord.size() - 1);
			int totalDeathCases = totalDeathCases_s.isEmpty() ? 0 : Integer.parseInt(totalDeathCases_s);
			String previousDeathCases_s = deathsRecord.get(reportedRecord.size() - 2);
			int previousDeathCases = previousDeathCases_s.isEmpty() ? 0 : Integer.parseInt(deathsRecord.get(reportedRecord.size() - 2));
			int newDeathCases = totalDeathCases - previousDeathCases;

			CoronavirusCase coronavirusCase = new CoronavirusCase(state, country, totalReportedCases, newReportedCases, totalRecoveredCases,
					newRecoveredCases, totalDeathCases, newDeathCases);
			coronavirusCaseList.add(coronavirusCase);
			System.out.println(coronavirusCase);
		}

	}

	private List<CSVRecord> getRecords(String URL) {
		String fileData = "";

		try {

			URL url = new URL(URL);

			// read text returned by server
			BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
			String line;
			while ((line = in.readLine()) != null) {
				fileData = fileData + line + "\n";
			}
			in.close();

		} catch (MalformedURLException e) {
			System.out.println("Malformed URL: " + e.getMessage());
		} catch (IOException e) {
			System.out.println("I/O Error: " + e.getMessage());
			e.printStackTrace();
		}

		StringReader dataReader = new StringReader(fileData);

		List<CSVRecord> records = null;
		try {
			records = CSVFormat.RFC4180.withFirstRecordAsHeader().parse(dataReader).getRecords();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return records;
	}

}
