package com.jsonvat.exam.parser.vatparser;

import com.jsonvat.exam.datatypes.VatType;
import com.jsonvat.exam.parser.ParsableCountryVatIfc;

public class CountrySuperReducedVatParser extends CountryVatParserAbs implements ParsableCountryVatIfc{
	public CountrySuperReducedVatParser() {
		super.setVatType(VatType.SUPER_REDUCED);
	}
}
