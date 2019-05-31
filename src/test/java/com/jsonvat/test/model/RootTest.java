package com.jsonvat.test.model;

import java.util.ArrayList;
import java.util.List;

import com.jsonvat.exam.model.CountryRate;
import com.jsonvat.exam.model.Period;
import com.jsonvat.exam.model.Rate;
import com.jsonvat.exam.model.Root;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class RootTest extends TestCase {
	/**
	 * Create the test case
	 *
	 * @param testName name of the test case
	 */
	public RootTest(String testName) {
		super(testName);
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(RootTest.class);
	}

	public void testDetails() {
		String expected = "Some details...";
		Root r = new Root();
		r.setDetails(expected);
		assertEquals(expected, r.getDetails());
	}

	public void testRates() {
		List<CountryRate> expected = new ArrayList<>();
		CountryRate cr = new CountryRate();
		cr.setCode("TR");
		cr.setCountry_code("TR");
		cr.setName("Turkey");

		List<Period> periods = new ArrayList<>();
		Period period = new Period();
		period.setEffective_from("2019-05-31");

		Rate rate = new Rate();
		rate.setStandard(50.0);
		period.setRates(rate);

		periods.add(period);

		Root r = new Root();
		r.setRates(expected);
		assertEquals(expected, r.getRates());
	}

	public void testVersion() {
		String expected = "v0.0.1";
		Root r = new Root();
		r.setVersion(expected);
		assertEquals(expected, r.getVersion());
	}
}
