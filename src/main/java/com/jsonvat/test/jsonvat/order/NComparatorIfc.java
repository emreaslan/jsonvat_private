package com.jsonvat.test.jsonvat.order;

import java.util.Comparator;
import java.util.Map.Entry;

public interface NComparatorIfc {	
	
	<K, V extends Comparable<? super V>> Comparator<? super Entry<K, V>> getComparatorLowest();
	<K, V extends Comparable<? super V>> Comparator<? super Entry<K, V>> getComparatorHighest();
	
}
