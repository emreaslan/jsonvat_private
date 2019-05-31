package com.jsonvat.exam.order;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.jsonvat.exam.datatypes.OrderByType;

public interface NComparableIfc {

	<K, V extends Comparable<? super V>> List<Entry<K, V>> find(Map<K, V> map, int n, OrderByType orderBy);

}