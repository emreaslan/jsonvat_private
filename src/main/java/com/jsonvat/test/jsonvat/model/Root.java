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

	public Map<String, Double> extractCountryVatMap() {
		Map<String, Double> countryVatMap = new HashMap<>();

		rates.forEach(item -> {
			if (item.getName() != null) {
				if (item.getPeriods() != null) {
					if (item.getPeriods().get(0).getRates() != null) {
						if (item.getPeriods().get(0).getRates().getStandard() != null) {
							String country = item.getName();
							double vat = Double.parseDouble((item.getPeriods().get(0).getRates().getStandard()));
							countryVatMap.put(country, vat);
						}
					}
				}
			}
		});
		return countryVatMap;
	}
}
