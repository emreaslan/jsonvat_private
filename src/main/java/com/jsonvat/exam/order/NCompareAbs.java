package com.jsonvat.exam.order;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Map.Entry;

public abstract class NCompareAbs implements NComparableIfc, NComparatorIfc {
	@Override
	public <K, V extends Comparable<? super V>> Comparator<? super Entry<K, V>> getComparatorLowest() {
		Comparator<? super Entry<K, V>> comparator = new Comparator<Entry<K, V>>() {
			@Override
			public int compare(Entry<K, V> e0, Entry<K, V> e1) {
				V v0 = e0.getValue();
				V v1 = e1.getValue();
				return v1.compareTo(v0);
			}
		};
		return comparator;
	}

	@Override
	public <K, V extends Comparable<? super V>> Comparator<? super Entry<K, V>> getComparatorHighest() {
		Comparator<? super Entry<K, V>> comparator = new Comparator<Entry<K, V>>() {
			@Override
			public int compare(Entry<K, V> e0, Entry<K, V> e1) {
				V v0 = e0.getValue();
				V v1 = e1.getValue();
				return v0.compareTo(v1);
			}
		};
		return comparator;
	}

	protected <K, V extends Comparable<? super V>> List<Entry<K, V>> findHighest(Map<K, V> map, int n) {
		return this.findNEntries(map, n, this.getComparatorHighest());
	}

	protected <K, V extends Comparable<? super V>> List<Entry<K, V>> findLowest(Map<K, V> map, int n) {
		return this.findNEntries(map, n, this.getComparatorLowest());
	}

	protected <K, V extends Comparable<? super V>> List<Entry<K, V>> findNEntries(Map<K, V> map, int n,
			Comparator<? super Entry<K, V>> comparator) {
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

}
