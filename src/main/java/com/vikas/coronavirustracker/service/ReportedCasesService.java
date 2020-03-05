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

import com.vikas.coronavirustracker.model.ReportedCase;

@Service

public class ReportedCasesService {

	private static final String REPORTED_CASES_URL = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_19-covid-Confirmed.csv";

	private List<ReportedCase> reportedCases = new ArrayList<ReportedCase>();

	public List<ReportedCase> getReportedCases() {
		return reportedCases;
	}

	@PostConstruct
	@Scheduled(cron = "0 0 0 * * ?")
	public void start() {

		String fileData = "";

		try {

			URL url = new URL(REPORTED_CASES_URL);

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
		}

		StringReader dataReader = new StringReader(fileData);

		Iterable<CSVRecord> records = null;
		try {
			records = CSVFormat.RFC4180.withFirstRecordAsHeader().parse(dataReader);
		} catch (IOException e) {
			e.printStackTrace();
		}
		for (CSVRecord record : records) {
			String state = record.get("Province/State");
			String country = record.get("Country/Region");
			int lastestCases = Integer.parseInt(record.get(record.size() - 1));
			int previousCases = Integer.parseInt(record.get(record.size() - 2));
			int newCases = lastestCases - previousCases;

			ReportedCase reportedCaseRecord = new ReportedCase(state, country, lastestCases, newCases);
			reportedCases.add(reportedCaseRecord);
			System.out.println(reportedCaseRecord);
		}

	}

}
