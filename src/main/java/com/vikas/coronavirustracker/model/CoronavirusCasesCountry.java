package com.vikas.coronavirustracker.model;

public class CoronavirusCasesCountry {

	private String country;
	private int totalReportedCases;
	private int newReoprtedCases;
	private int totalRecoveredCases;
	private int newRecoveredCases;
	private int totalDeathCases;
	private int newDeathCases;

	public CoronavirusCasesCountry(String country, int totalReportedCases, int newReoprtedCases, int totalRecoveredCases, int newRecoveredCases,
			int totalDeathCases, int newDeathCases) {
		super();
		this.country = country;
		this.totalReportedCases = totalReportedCases;
		this.newReoprtedCases = newReoprtedCases;
		this.totalRecoveredCases = totalRecoveredCases;
		this.newRecoveredCases = newRecoveredCases;
		this.totalDeathCases = totalDeathCases;
		this.newDeathCases = newDeathCases;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public int getTotalReportedCases() {
		return totalReportedCases;
	}

	public void setTotalReportedCases(int totalReportedCases) {
		this.totalReportedCases = totalReportedCases;
	}

	public int getNewReportedCases() {
		return newReoprtedCases;
	}

	public void setNewReoprtedCases(int newReoprtedCases) {
		this.newReoprtedCases = newReoprtedCases;
	}

	public int getTotalRecoveredCases() {
		return totalRecoveredCases;
	}

	public void setTotalRecoveredCases(int totalRecoveredCases) {
		this.totalRecoveredCases = totalRecoveredCases;
	}

	public int getNewRecoveredCases() {
		return newRecoveredCases;
	}

	public void setNewRecoveredCases(int newRecoveredCases) {
		this.newRecoveredCases = newRecoveredCases;
	}

	public int getTotalDeathCases() {
		return totalDeathCases;
	}

	public void setTotalDeathCases(int totalDeathCases) {
		this.totalDeathCases = totalDeathCases;
	}

	public int getNewDeathCases() {
		return newDeathCases;
	}

	public void setNewDeathCases(int newDeathCases) {
		this.newDeathCases = newDeathCases;
	}

	@Override
	public String toString() {
		return "CoronavirusCasesCountry [country=" + country + ", totalReportedCases=" + totalReportedCases + ", newReoprtedCases=" + newReoprtedCases
				+ ", totalRecoveredCases=" + totalRecoveredCases + ", newRecoveredCases=" + newRecoveredCases + ", totalDeathCases=" + totalDeathCases
				+ ", newDeathCases=" + newDeathCases + "]";
	}

}
