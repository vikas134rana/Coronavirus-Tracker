package com.vikas.coronavirustracker.model;

public class CoronavirusCase {

	private String state;
	private String country;
	private int totalReportedCases;
	private int newReportedCases;
	private int totalRecoveredCases;
	private int newRecoveredCases;
	private int totalDeathCases;
	private int newDeathCases;

	public CoronavirusCase(String state, String country, int totalReportedCases, int newReportedCases, int totalRecoveredCases, int newRecoveredCases,
			int totalDeathCases, int newDeathCases) {
		super();
		this.state = state;
		this.country = country;
		this.totalReportedCases = totalReportedCases;
		this.newReportedCases = newReportedCases;
		this.totalRecoveredCases = totalRecoveredCases;
		this.newRecoveredCases = newRecoveredCases;
		this.totalDeathCases = totalDeathCases;
		this.newDeathCases = newDeathCases;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
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
		return newReportedCases;
	}

	public void setNewReportedCases(int newReportedCases) {
		this.newReportedCases = newReportedCases;
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
		return "CoronavirusCase [state=" + state + ", country=" + country + ", totalReportedCases=" + totalReportedCases + ", newReoprtedCases="
				+ newReportedCases + ", totalRecoveredCases=" + totalRecoveredCases + ", newRecoveredCases=" + newRecoveredCases + ", totalDeathCases="
				+ totalDeathCases + ", newDeathCases=" + newDeathCases + "]";
	}

}
