package com.jsonvat.exam;

import java.net.URL;
import java.util.Map;
import java.util.Scanner;

import com.jsonvat.exam.datatypes.OrderByType;
import com.jsonvat.exam.datatypes.VatType;
import com.jsonvat.exam.model.Root;
import com.jsonvat.exam.order.NCompare;
import com.jsonvat.exam.parser.DataParser;
import com.jsonvat.exam.parser.vatparser.CountryVatParserFactory;
import com.jsonvat.exam.print.Printer;

/**
 * JSon Parser Application
 *
 */
public class App {
	public static void main(String[] args) {
		int n = 1;
		URL url = App.class.getResource("data.json");
		DataParser dataParser = new DataParser(url);
		Root rootNode = dataParser.parseRoot();
		if (rootNode != null) {
			Utils.getInstance().setPrintableIfc(new Printer());
			Utils.getInstance().setNComparableIfc(new NCompare());
			Utils.getInstance().setParsableCountryVatIfc(CountryVatParserFactory.getInstance().getVatParser(VatType.REDUCED));
			Map<String, Double> countryVatMap = Utils.getInstance().parse(rootNode);
			Scanner in = new Scanner(System.in);
			try {
				do {
					System.out.print("Please enter n (" + 1 + "-" + countryVatMap.size() + " to process query, other values to exit):");
					n = in.nextInt();
					if (n > 0 && n <= countryVatMap.size()) {
						System.out.println("Highest VATs");
						Utils.getInstance().print(Utils.getInstance().find(countryVatMap, n, OrderByType.DESC));
						System.out.println();
						System.out.println("Lowest VATs");
						Utils.getInstance().print(Utils.getInstance().find(countryVatMap, n, OrderByType.ASC));
						System.out.println();
					} else {
						System.out.println("Closing the program");
					}
				} while (n > 0);
			} catch (Exception e) {
				System.out.println("Entered text instead of number.");
			}finally {
				in.close();
			}
		}
	}
}
