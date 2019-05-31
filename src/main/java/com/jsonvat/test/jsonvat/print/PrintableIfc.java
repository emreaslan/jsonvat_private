package com.jsonvat.test.jsonvat.print;

import java.util.List;
import java.util.Map.Entry;

public interface PrintableIfc {
	<K, V extends Comparable<? super V>> void print(List<Entry<K, V>> entries);
}