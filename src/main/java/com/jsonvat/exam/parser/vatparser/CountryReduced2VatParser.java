package com.jsonvat.exam.parser.vatparser;

import com.jsonvat.exam.datatypes.VatType;
import com.jsonvat.exam.parser.ParsableCountryVatIfc;

public class CountryReduced2VatParser extends CountryVatParserAbs implements ParsableCountryVatIfc{
	public CountryReduced2VatParser() {
		super.setVatType(VatType.REDUCED2);
	}
}
