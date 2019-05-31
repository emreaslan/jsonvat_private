package com.jsonvat.exam.parser;

import java.util.Map;

import com.jsonvat.exam.model.Root;

public interface ParsableIfc <K, V extends Comparable<? super V>> {
	Map<K, V> parse(Root rootNode);
}
