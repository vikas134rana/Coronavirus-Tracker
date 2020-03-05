package com.vikas.coronavirustracker.model;

public class ReportedCase {

	private String state;
	private String country;
	private int totalCases;
	private int newCases;

	public ReportedCase(String state, String country, int totalCases, int newCases) {
		super();
		this.state = state;
		this.country = country;
		this.totalCases = totalCases;
		this.newCases = newCases;
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
		return "ReportedCase [state=" + state + ", country=" + country + ", totalCases=" + totalCases + ", newCases=" + newCases + "]";
	}

}
