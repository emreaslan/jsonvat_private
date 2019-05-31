package com.jsonvat.test.jsonvat.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Root {
	private String details;
	private String version;
	private List<CountryRate> rates = new ArrayList<CountryRate>();

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public List<CountryRate> getRates() {
		return rates;
	}

	public void setRates(List<CountryRate> rates) {
		this.rates = rates;
	}

	
}
