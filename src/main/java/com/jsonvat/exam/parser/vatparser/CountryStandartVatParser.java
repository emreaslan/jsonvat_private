package com.jsonvat.exam.parser.vatparser;

import com.jsonvat.exam.datatypes.VatType;
import com.jsonvat.exam.parser.ParsableCountryVatIfc;

public class CountryStandartVatParser extends CountryVatParserAbs implements ParsableCountryVatIfc{
	public CountryStandartVatParser() {
		super.setVatType(VatType.STANDARD);
	}
}
