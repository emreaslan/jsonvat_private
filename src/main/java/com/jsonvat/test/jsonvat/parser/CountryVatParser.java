package com.jsonvat.test.jsonvat.parser;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jsonvat.test.jsonvat.model.Period;
import com.jsonvat.test.jsonvat.model.Root;

public class CountryVatParser implements ParsableCountryVatIfc{

	@Override
	public Map<String, Double> parse(Root rootNode) {
		Map<String, Double> countryVatMap = new HashMap<>();

		rootNode.getRates().forEach(item -> {
			if (item.getName() != null) {
				if (item.getPeriods() != null) {
					Double currentVat = extractCurrentVat(item.getPeriods());
					if (currentVat != null) {
						countryVatMap.put(item.getName(), currentVat);
					}
				}
			}
		});
		return countryVatMap;
	}
	
	private Double extractCurrentVat(List<Period> periods) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("uuuu-MM-dd");
		LocalDate referenceDate = null;
		Double referenceVat = null;
		
		for(Period period : periods) {
			boolean isReferenceUpdated = false;
			if (period.getRates() != null) {
				
				LocalDate localDate = LocalDate.from(dtf.parse(period.getEffective_from()));
				if (localDate == null) {
					isReferenceUpdated = false;
				}
				else if (referenceDate == null) {
					referenceDate = localDate;
					isReferenceUpdated = true;
				}					
				else if(referenceDate.isBefore(localDate)){					
					referenceDate = localDate;
					isReferenceUpdated = true;
				}
				
				if (period.getRates().getStandard() != null && isReferenceUpdated) {
					referenceVat = Double.parseDouble(period.getRates().getStandard());
				}
			}
		}
		return referenceVat;
	}
}
