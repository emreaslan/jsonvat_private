package com.jsonvat.test.jsonvat;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.Map;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jsonvat.test.jsonvat.model.Root;
import com.jsonvat.test.jsonvat.order.NCompare;
import com.jsonvat.test.jsonvat.parser.CountryVatParser;
import com.jsonvat.test.jsonvat.print.Printer;

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
			Utils.getInstance().setPrintableIfc(new Printer());
			Utils.getInstance().setNComparableIfc(new NCompare());
			Utils.getInstance().setParsableCountryVatIfc(new CountryVatParser());

			Root rootNode = mapper.readValue(jsonInput, Root.class);

			Map<String, Double> countryVatMap = Utils.getInstance().parse(rootNode);

			System.out.println("Highest VATs");
			Utils.getInstance().print(Utils.getInstance().find(countryVatMap, 3, OrderBy.DESC));

			System.out.println("Lowest VATs");
			Utils.getInstance().print(Utils.getInstance().find(countryVatMap, 3, OrderBy.ASC));

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
}
