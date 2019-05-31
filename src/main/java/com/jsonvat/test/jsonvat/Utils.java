package com.jsonvat.test.jsonvat;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.jsonvat.test.jsonvat.model.Root;
import com.jsonvat.test.jsonvat.order.NComparableIfc;
import com.jsonvat.test.jsonvat.parser.ParsableCountryVatIfc;
import com.jsonvat.test.jsonvat.print.PrintableIfc;

public class Utils implements PrintableIfc, NComparableIfc, ParsableCountryVatIfc {
	private static final Utils instance = new Utils();
	private PrintableIfc printableIfc;
	private NComparableIfc nComparableIfc;
	private ParsableCountryVatIfc parsableCountryVatIfc;

	private Utils() {
	}

	public static Utils getInstance() {
		return instance;
	}

	public void setPrintableIfc(PrintableIfc printableIfc) {
		this.printableIfc = printableIfc;
	}

	public void setNComparableIfc(NComparableIfc nComparableIfc) {
		this.nComparableIfc = nComparableIfc;
	}

	public void setParsableCountryVatIfc(ParsableCountryVatIfc parsableCountryVatIfc) {
		this.parsableCountryVatIfc = parsableCountryVatIfc;
	}
	
	@Override
	public <K, V extends Comparable<? super V>> void print(List<Entry<K, V>> entries) {
		if (this.printableIfc != null) {
			this.printableIfc.print(entries);
		}
	}

	@Override
	public <K, V extends Comparable<? super V>> List<Entry<K, V>> find(Map<K, V> map, int n, OrderBy orderBy) {
		if (this.nComparableIfc != null) {
			return this.nComparableIfc.find(map, n, orderBy);
		}
		return null;
	}

	@Override
	public Map<String, Double> parse(Root rootNode) {
		if (this.parsableCountryVatIfc != null) {
			return this.parsableCountryVatIfc.parse(rootNode);
		}
		return null;
	}	

}
