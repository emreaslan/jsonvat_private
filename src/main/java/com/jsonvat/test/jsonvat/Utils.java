package com.jsonvat.test.jsonvat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Map.Entry;

import com.jsonvat.test.jsonvat.model.Root;
import com.jsonvat.test.jsonvat.order.NComparableIfc;
import com.jsonvat.test.jsonvat.print.PrintableIfc;

public class Utils implements PrintableIfc, NComparableIfc {
	private static final Utils instance = new Utils();
	private PrintableIfc printer;

	private Utils() {

	}	

	public static Utils getInstance() {
		return instance;
	}		

	public void setPrinter(PrintableIfc printer) {
		this.printer = printer;
	}

	public Map<String, Double> extractCountryVatMap(Root rootNode) {
		Map<String, Double> countryVatMap = new HashMap<>();

		rootNode.getRates().forEach(item -> {
			if (item.getName() != null) {
				if (item.getPeriods() != null) {
					if (item.getPeriods().get(0).getRates() != null) {
						if (item.getPeriods().get(0).getRates().getStandard() != null) {
							String country = item.getName();
							double vat = Double.parseDouble((item.getPeriods().get(0).getRates().getStandard()));
							countryVatMap.put(country, vat);
						}
					}
				}
			}
		});
		return countryVatMap;
	}

	private <K, V extends Comparable<? super V>> List<Entry<K, V>> findNEntries(Map<K, V> map, int n, Comparator<? super Entry<K, V>> comparator){
		PriorityQueue<Entry<K, V>> pQueue = new PriorityQueue<Entry<K, V>>(n, comparator);

		for (Entry<K, V> entry : map.entrySet()) {
			pQueue.offer(entry);
			while (pQueue.size() > n) {
				pQueue.poll();
			}
		}

		List<Entry<K, V>> result = new ArrayList<Map.Entry<K, V>>();
		while (pQueue.size() > 0) {
			result.add(pQueue.poll());
		}
		Collections.reverse(result);
		return result;
	}
	
	private <K, V extends Comparable<? super V>> List<Entry<K, V>> findHighest(Map<K, V> map, int n) {

		Comparator<? super Entry<K, V>> comparator = new Comparator<Entry<K, V>>() {
			@Override
			public int compare(Entry<K, V> e0, Entry<K, V> e1) {
				V v0 = e0.getValue();
				V v1 = e1.getValue();
				return v0.compareTo(v1);
			}
		};
		return this.findNEntries(map, n, comparator);
	}

	private <K, V extends Comparable<? super V>> List<Entry<K, V>> findLowest(Map<K, V> map, int n) {

		Comparator<? super Entry<K, V>> comparator = new Comparator<Entry<K, V>>() {
			@Override
			public int compare(Entry<K, V> e0, Entry<K, V> e1) {
				V v0 = e0.getValue();
				V v1 = e1.getValue();
				return v1.compareTo(v0);
			}
		};
		return this.findNEntries(map, n, comparator);
	}

	@Override
	public <K, V extends Comparable<? super V>> void print(List<Entry<K, V>> entries) {
		if (this.printer != null) {
			this.printer.print(entries);
		}
	}

	@Override
	public <K, V extends Comparable<? super V>> List<Entry<K, V>> find(Map<K, V> map, int n, OrderBy orderBy) {
		List<Entry<K, V>> entries = null;
		if (orderBy.equals(OrderBy.DESC)) {
			entries = findHighest(map, n);
		} else {
			entries = findLowest(map, n);
		}
		return entries;
	}	
}
