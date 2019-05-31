package com.jsonvat.exam.model;

public class Period {
	private String effective_from;
	private Rate rates;
	
	public String getEffective_from() {
		return effective_from;
	}
	public void setEffective_from(String effective_from) {
		this.effective_from = effective_from;
	}
	public Rate getRates() {
		return rates;
	}
	public void setRates(Rate rates) {
		this.rates = rates;
	}
	
}
