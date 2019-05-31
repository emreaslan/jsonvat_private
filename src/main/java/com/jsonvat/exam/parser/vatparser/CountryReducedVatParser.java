package com.jsonvat.exam.parser.vatparser;

import com.jsonvat.exam.datatypes.VatType;
import com.jsonvat.exam.parser.ParsableCountryVatIfc;

public class CountryReducedVatParser extends CountryVatParserAbs implements ParsableCountryVatIfc{
	public CountryReducedVatParser() {
		super.setVatType(VatType.REDUCED);
	}
}
