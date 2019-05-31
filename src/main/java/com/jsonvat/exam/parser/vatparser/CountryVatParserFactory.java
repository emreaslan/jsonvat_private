package com.jsonvat.exam.parser.vatparser;

import com.jsonvat.exam.datatypes.VatType;
import com.jsonvat.exam.parser.ParsableCountryVatIfc;

public class CountryVatParserFactory {
	private static final CountryVatParserFactory instance = new CountryVatParserFactory();

	private CountryVatParserFactory() {

	}

	public static CountryVatParserFactory getInstance() {
		return instance;
	}

	public ParsableCountryVatIfc getVatParser(VatType vatType) {
		if (vatType.equals(VatType.PARKING)) {
			return new CountryParkingVatParser();
		} else if (vatType.equals(VatType.REDUCED)) {
			return new CountryReducedVatParser();
		} else if (vatType.equals(VatType.REDUCED1)) {
			return new CountryReduced1VatParser();
		} else if (vatType.equals(VatType.REDUCED2)) {
			return new CountryReduced2VatParser();
		} else if (vatType.equals(VatType.STANDARD)) {
			return new CountryStandartVatParser();
		} else if (vatType.equals(VatType.SUPER_REDUCED)) {
			return new CountrySuperReducedVatParser();
		}
		return null;
	}
}
