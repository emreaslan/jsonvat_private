package com.jsonvat.test.jsonvat.parser;

import java.util.Map;

import com.jsonvat.test.jsonvat.model.Root;

public interface ParsableIfc <K, V extends Comparable<? super V>> {
	Map<K, V> parse(Root rootNode);
}
