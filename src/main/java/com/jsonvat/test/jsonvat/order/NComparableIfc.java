package com.jsonvat.test.jsonvat.order;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.jsonvat.test.jsonvat.OrderBy;

public interface NComparableIfc {

	<K, V extends Comparable<? super V>> List<Entry<K, V>> find(Map<K, V> map, int n, OrderBy orderBy);

}