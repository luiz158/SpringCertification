package com.baselogic.demos;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.zip.DataFormatException;

import javax.naming.InsufficientResourcesException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class AspectTests {
	
	private final Logger logger = LoggerFactory
			.getLogger(AspectTests.class);

	@Test
	public void testHolder(){
		logger.info("testHolder");
	}
	
	/*

	// TESTING Autowired. See Log Output, after running.
	@Autowired
	private MyExceptionThrower myExceptionThrower;

	@Test
	public void testExceptionAOP() throws Exception {
		logger.debug("***** TEST Throwing Data Format Exception *****");
		try {
			myExceptionThrower.throwDFE();
		} catch (DataFormatException dae) {
			assertTrue(true);
		} catch (Exception e) {
			fail("Expected DataFormatException");
		}

	}

	@Test
	public void testExceptionAOP2() throws Exception {
		logger.debug("***** TEST Throwing Insufficient Resources Exception *****");
		try {
			myExceptionThrower.throwIRE();
		} catch (InsufficientResourcesException ire) {
			assertTrue(true);
		} catch (Exception e) {
			fail("Expected InsufficientResourcesException");
		}

	}*/

}