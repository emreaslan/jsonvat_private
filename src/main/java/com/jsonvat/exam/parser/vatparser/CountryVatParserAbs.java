package com.jsonvat.exam.parser.vatparser;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jsonvat.exam.datatypes.VatType;
import com.jsonvat.exam.model.Period;
import com.jsonvat.exam.model.Root;
import com.jsonvat.exam.parser.ParsableCountryVatIfc;

public abstract class CountryVatParserAbs implements ParsableCountryVatIfc {
	private VatType vatType;

	protected void setVatType(VatType vatType) {
		this.vatType = vatType;
	}

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

		for (Period period : periods) {
			boolean isReferenceUpdated = false;
			if (period.getRates() != null) {

				LocalDate localDate = LocalDate.from(dtf.parse(period.getEffective_from()));
				if (localDate == null) {
					isReferenceUpdated = false;
				} else if (referenceDate == null) {
					referenceDate = localDate;
					isReferenceUpdated = true;
				} else if (referenceDate.isBefore(localDate)) {
					referenceDate = localDate;
					isReferenceUpdated = true;
				}

				if (isReferenceUpdated) {
					if (vatType.equals(VatType.PARKING) && period.getRates().getParking() != null) {
						referenceVat = period.getRates().getParking();
					} else if (vatType.equals(VatType.REDUCED) && period.getRates().getReduced() != null) {
						referenceVat = period.getRates().getReduced();
					} else if (vatType.equals(VatType.REDUCED1) && period.getRates().getReduced1() != null) {
						referenceVat = period.getRates().getReduced1();
					} else if (vatType.equals(VatType.REDUCED2) && period.getRates().getReduced2() != null) {
						referenceVat = period.getRates().getReduced2();
					} else if (vatType.equals(VatType.STANDARD) && period.getRates().getStandard() != null) {
						referenceVat = period.getRates().getStandard();
					} else if (vatType.equals(VatType.SUPER_REDUCED) && period.getRates().getSuper_reduced() != null) {
						referenceVat = period.getRates().getSuper_reduced();
					}
				}
			}
		}
		return referenceVat;
	}
}
