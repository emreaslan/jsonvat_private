package com.jsonvat.test.model;

import com.jsonvat.exam.model.Period;
import com.jsonvat.exam.model.Rate;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class PeriodTest extends TestCase {
	/**
	 * Create the test case
	 *
	 * @param testName name of the test case
	 */
	public PeriodTest(String testName) {
		super(testName);
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(PeriodTest.class);
	}

	public void testEffectiveFrom() {
		String expected = "2019-05-31";
		Period p = new Period();
		p.setEffective_from(expected);
		assertEquals(expected, p.getEffective_from());
	}

	public void testRates() {
		Period p = new Period();
		Rate expected = new Rate();
		expected.setStandard(25.5);
		p.setRates(expected);
		assertEquals(expected, p.getRates());
	}
}
