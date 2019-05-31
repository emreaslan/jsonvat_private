package com.jsonvat.test.jsonvat;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.jsonvat.test.jsonvat.model.Period;
import com.jsonvat.test.jsonvat.model.Root;
import com.jsonvat.test.jsonvat.order.NComparableIfc;
import com.jsonvat.test.jsonvat.print.PrintableIfc;

public class Utils implements PrintableIfc, NComparableIfc {
	private static final Utils instance = new Utils();
	private PrintableIfc printableIfc;
	private NComparableIfc nComparableIfc;

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

	public Map<String, Double> extractCountryVatMap(Root rootNode) {
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
		
		for(Period period : periods) {
			boolean isReferenceUpdated = false;
			if (period.getRates() != null) {
				
				LocalDate localDate = LocalDate.from(dtf.parse(period.getEffective_from()));
				if (localDate == null) {
					isReferenceUpdated = false;
				}
				else if (referenceDate == null) {
					referenceDate = localDate;
					isReferenceUpdated = true;
				}					
				else if(referenceDate.isBefore(localDate)){					
					referenceDate = localDate;
					isReferenceUpdated = true;
				}
				
				if (period.getRates().getStandard() != null && isReferenceUpdated) {
					referenceVat = Double.parseDouble(period.getRates().getStandard());
				}
			}
		}
		return referenceVat;
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

}
