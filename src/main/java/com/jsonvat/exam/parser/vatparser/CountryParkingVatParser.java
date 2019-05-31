package com.jsonvat.exam.parser.vatparser;

import com.jsonvat.exam.datatypes.VatType;
import com.jsonvat.exam.parser.ParsableCountryVatIfc;

public class CountryParkingVatParser extends CountryVatParserAbs implements ParsableCountryVatIfc{
	public CountryParkingVatParser() {
		super.setVatType(VatType.PARKING);
	}
}
