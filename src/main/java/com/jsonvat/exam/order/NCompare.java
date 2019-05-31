package com.jsonvat.exam.order;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.jsonvat.exam.OrderBy;

public class NCompare extends NCompareAbs implements NComparableIfc {

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
