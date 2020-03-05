package com.vikas.coronavirustracker.model;

public class ReportedCasesCountry {

	private String country;
	private int totalCases;
	private int newCases;

	public ReportedCasesCountry(String country, int totalCases, int newCases) {
		super();
		this.country = country;
		this.totalCases = totalCases;
		this.newCases = newCases;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public int getTotalCases() {
		return totalCases;
	}

	public void setTotalCases(int totalCases) {
		this.totalCases = totalCases;
	}

	public int getNewCases() {
		return newCases;
	}

	public void setNewCases(int newCases) {
		this.newCases = newCases;
	}

	@Override
	public String toString() {
		return "ReportedCasesCountry [country=" + country + ", totalCases=" + totalCases + ", newCases=" + newCases + "]";
	}

}
