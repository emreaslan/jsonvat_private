package com.jsonvat.test;

import java.net.URL;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.jsonvat.exam.App;
import com.jsonvat.exam.Utils;
import com.jsonvat.exam.datatypes.OrderByType;
import com.jsonvat.exam.datatypes.VatType;
import com.jsonvat.exam.model.Root;
import com.jsonvat.exam.order.NCompare;
import com.jsonvat.exam.parser.DataParser;
import com.jsonvat.exam.parser.vatparser.CountryVatParserFactory;
import com.jsonvat.exam.print.Printer;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
	/**
	 * Create the test case
	 *
	 * @param testName name of the test case
	 */
	public AppTest(String testName) {
		super(testName);
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(AppTest.class);
	}

    public void testHighestLowestThreeStandartVat()
    {
    	URL url = App.class.getResource("data.json");
		DataParser dataParser = new DataParser(url);
		Root rootNode = dataParser.parseRoot();
		assertNotNull(rootNode);
		
		Utils.getInstance().setPrintableIfc(new Printer());
		Utils.getInstance().setNComparableIfc(new NCompare());
		Utils.getInstance().setParsableCountryVatIfc(CountryVatParserFactory.getInstance().getVatParser(VatType.STANDARD));
		Map<String, Double> countryVatMap = Utils.getInstance().parse(rootNode);
		
		List<Entry<String, Double>> lowestThree = Utils.getInstance().find(countryVatMap, 3, OrderByType.ASC);		
		List<Entry<String, Double>> expectedLowestThree = new ArrayList<>();
		expectedLowestThree.add(new AbstractMap.SimpleEntry<String, Double>("Luxembourg", 17.0));
		expectedLowestThree.add(new AbstractMap.SimpleEntry<String, Double>("Malta", 18.0));
		expectedLowestThree.add(new AbstractMap.SimpleEntry<String, Double>("Germany", 19.0));
		
		int i = 0;
		for(Entry<String, Double> entry : lowestThree) {
			assertEquals(entry.getKey(), expectedLowestThree.get(i).getKey());
			assertEquals(entry.getValue(), expectedLowestThree.get(i).getValue());
			++i;
		}

		List<Entry<String, Double>> highestThree = Utils.getInstance().find(countryVatMap, 3, OrderByType.DESC);
		List<Entry<String, Double>> expectedHighestThree = new ArrayList<>();
		expectedHighestThree.add(new AbstractMap.SimpleEntry<String, Double>("Hungary", 27.0));
		expectedHighestThree.add(new AbstractMap.SimpleEntry<String, Double>("Croatia", 25.0));
		expectedHighestThree.add(new AbstractMap.SimpleEntry<String, Double>("Denmark", 25.0));
		i = 0;
		for(Entry<String, Double> entry : highestThree) {
			assertEquals(entry.getKey(), expectedHighestThree.get(i).getKey());
			assertEquals(entry.getValue(), expectedHighestThree.get(i).getValue());
			++i;
		}
    }
    
    public void testHighestLowestThreeReducedVat()
    {
    	URL url = App.class.getResource("data.json");
		DataParser dataParser = new DataParser(url);
		Root rootNode = dataParser.parseRoot();
		assertNotNull(rootNode);
		
		Utils.getInstance().setPrintableIfc(new Printer());
		Utils.getInstance().setNComparableIfc(new NCompare());
		Utils.getInstance().setParsableCountryVatIfc(CountryVatParserFactory.getInstance().getVatParser(VatType.REDUCED));
		Map<String, Double> countryVatMap = Utils.getInstance().parse(rootNode);
		
		List<Entry<String, Double>> lowestThree = Utils.getInstance().find(countryVatMap, 3, OrderByType.ASC);		
		List<Entry<String, Double>> expectedLowestThree = new ArrayList<>();
		expectedLowestThree.add(new AbstractMap.SimpleEntry<String, Double>("United Kingdom", 5.0));
		expectedLowestThree.add(new AbstractMap.SimpleEntry<String, Double>("Netherlands", 6.0));
		expectedLowestThree.add(new AbstractMap.SimpleEntry<String, Double>("Germany", 7.0));

		int i = 0;
		for(Entry<String, Double> entry : lowestThree) {
			assertEquals(entry.getKey(), expectedLowestThree.get(i).getKey());
			assertEquals(entry.getValue(), expectedLowestThree.get(i).getValue());
			++i;
		}

		List<Entry<String, Double>> highestThree = Utils.getInstance().find(countryVatMap, 3, OrderByType.DESC);
		List<Entry<String, Double>> expectedHighestThree = new ArrayList<>();
		expectedHighestThree.add(new AbstractMap.SimpleEntry<String, Double>("Czech Republic", 15.0));
		expectedHighestThree.add(new AbstractMap.SimpleEntry<String, Double>("Latvia", 12.0));
		expectedHighestThree.add(new AbstractMap.SimpleEntry<String, Double>("Spain", 10.0));
		i = 0;
		for(Entry<String, Double> entry : highestThree) {
			assertEquals(entry.getKey(), expectedHighestThree.get(i).getKey());
			assertEquals(entry.getValue(), expectedHighestThree.get(i).getValue());
			++i;
		}
    }
}
