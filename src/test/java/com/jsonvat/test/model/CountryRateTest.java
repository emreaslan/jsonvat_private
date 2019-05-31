package com.jsonvat.test.model;

import java.util.ArrayList;
import java.util.List;

import com.jsonvat.exam.model.CountryRate;
import com.jsonvat.exam.model.Period;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class CountryRateTest extends TestCase{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public CountryRateTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( CountryRateTest.class );
    }
    
    public void testCode() {
    	String code = "AA";
    	CountryRate cr = new CountryRate();
    	cr.setCode(code);
    	assertEquals(code, cr.getCode());
    }
    
    public void testCountryCode() {
    	String countryCode = "AA";
    	CountryRate cr = new CountryRate();
    	cr.setCountry_code(countryCode);
    	assertEquals(countryCode, cr.getCountry_code());
    }
    
    public void testName() {
    	String name = "NAME";
    	CountryRate cr = new CountryRate();
    	cr.setName(name);
    	assertEquals(name, cr.getName());
    }
    
    public void testPeriods() {
    	List<Period> expecteds = new ArrayList<>();
    	Period period = new Period();
    	expecteds.add(period);
    	CountryRate cr = new CountryRate();
    	cr.setPeriods(expecteds);
    	List<Period> actuals = cr.getPeriods();
    	assertEquals(expecteds.size(), actuals.size());
    	assertEquals(expecteds.get(0), actuals.get(0));
    }
}
