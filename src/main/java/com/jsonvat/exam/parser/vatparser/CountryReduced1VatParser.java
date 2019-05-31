package com.jsonvat.exam.parser.vatparser;

import com.jsonvat.exam.datatypes.VatType;
import com.jsonvat.exam.parser.ParsableCountryVatIfc;

public class CountryReduced1VatParser extends CountryVatParserAbs implements ParsableCountryVatIfc{
	public CountryReduced1VatParser() {
		super.setVatType(VatType.REDUCED1);
	}
}
