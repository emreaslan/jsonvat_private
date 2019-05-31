package com.jsonvat.exam;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.jsonvat.exam.datatypes.OrderByType;
import com.jsonvat.exam.model.Root;
import com.jsonvat.exam.order.NComparableIfc;
import com.jsonvat.exam.parser.ParsableCountryVatIfc;
import com.jsonvat.exam.print.PrintableIfc;

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
	public <K, V extends Comparable<? super V>> List<Entry<K, V>> find(Map<K, V> map, int n, OrderByType orderBy) {
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
