package com.jsonvat.test.jsonvat;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jsonvat.test.jsonvat.model.Root;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		ObjectMapper mapper = new ObjectMapper();
		JsonParser jsonInput = null;
		try {
			URL url = App.class.getResource("data.json");
			jsonInput = new JsonFactory().createParser(new FileReader(new File(url.getPath())));
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			Root rootNode = mapper.readValue(jsonInput, Root.class);
			Map<String, Double> countryVatMap = rootNode.extractCountryVatMap();
			countryVatMap.forEach((k, v) -> {
				System.out.println(k + ":" + v);
			});

			System.out.println(countryVatMap.size());
			Entry<String, Double> minVat = Collections.min(countryVatMap.entrySet(),
					Comparator.comparing(Entry::getValue));
			System.out.println("Lowest VAT: " + minVat.getKey() + ":" + minVat.getValue());
			List<Entry<String, Double>> highest = findHighest(countryVatMap, 3);
			
			for(Entry<String,Double> entry : highest) {
				System.out.println(entry.getKey() + ":" + entry.getValue());
			}
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static <K, V extends Comparable<? super V>> List<Entry<K, V>> findHighest(Map<K, V> map, int n) {
		
		Comparator<? super Entry<K, V>> comparator = new Comparator<Entry<K, V>>() {
			@Override
			public int compare(Entry<K, V> e0, Entry<K, V> e1) {
				V v0 = e0.getValue();
				V v1 = e1.getValue();
				return v0.compareTo(v1);
			}
		};
		PriorityQueue<Entry<K, V>> highest = new PriorityQueue<Entry<K, V>>(n, comparator);
		
		for (Entry<K, V> entry : map.entrySet()) {
			highest.offer(entry);
			while (highest.size() > n) {
				highest.poll();
			}
		}

		List<Entry<K, V>> result = new ArrayList<Map.Entry<K, V>>();
		while (highest.size() > 0) {
			result.add(highest.poll());
		}
		Collections.reverse(result);
		return result;
	}
}
