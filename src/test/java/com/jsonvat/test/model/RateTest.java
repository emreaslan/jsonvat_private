package com.jsonvat.test.model;

import com.jsonvat.exam.model.Rate;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class RateTest extends TestCase {
	/**
	 * Create the test case
	 *
	 * @param testName name of the test case
	 */
	public RateTest(String testName) {
		super(testName);
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(RateTest.class);
	}

	public void testParking() {
		Double expected = 11.1;
		Rate r = new Rate();
		r.setParking(expected);
		assertEquals(expected, r.getParking());
	}

	public void testReduced() {
		Double expected = 10.2;
		Rate r = new Rate();
		r.setReduced(expected);
		assertEquals(expected, r.getReduced());
	}

	public void testReduced1() {
		Double expected = null;
		Rate r = new Rate();
		r.setReduced1(expected);
		assertEquals(expected, r.getReduced1());
	}

	public void testReduced2() {
		Double expected = 2.1;
		Rate r = new Rate();
		r.setReduced2(expected);
		assertEquals(expected, r.getReduced2());
	}

	public void testStandard() {
		Double expected = 12.2;
		Rate r = new Rate();
		r.setStandard(expected);
		assertEquals(expected, r.getStandard());
	}

	public void testSuperReduced() {
		Double expected = 1.0;
		Rate r = new Rate();
		r.setSuper_reduced(expected);
		assertEquals(expected, r.getSuper_reduced());
	}
}
