package com.jsonvat.test.jsonvat.print;

import java.util.List;
import java.util.Map.Entry;

public class Printer implements PrintableIfc {

	@Override
	public <K, V extends Comparable<? super V>> void print(List<Entry<K, V>> entries) {
		entries.forEach(item -> {
			System.out.println(item.getKey() + ":" + item.getValue());
		});
	}

}
