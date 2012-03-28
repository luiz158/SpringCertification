package com.baselogic.service;

// Hamcrest
//import static org.hamcrest.CoreMatchers.*;
//import static org.hamcrest.core.IsNull.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

// JUnit
//import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

// Mockito
import static org.mockito.Matchers.any;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

// PowerMock
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.core.classloader.annotations.SuppressStaticInitializationFor;
import org.powermock.modules.junit4.PowerMockRunner;

// Project
import com.baselogic.dao.OrderDAO;
import com.baselogic.domain.Order;

import com.baselogic.util.ExampleUtils;

import java.util.Date;

/**
 * OrderMockServiceTests
 * 
 * <p>Spring Certification objective: 1.2 Lifecycle</p>
 * 
 * @see <a href="http://springcert.sourceforge.net/core-3/index.html#beans">Objective 1.2 Lifecycle</a>
 *
 * @author Mick Knutson
 * @see <a href="http://www.baselogic.com">Blog: http://baselogic.com</a>
 * @see <a href="http://linkedin.com/in/mickknutson">LinkedIN: http://linkedin.com/in/mickknutson</a>
 * @see <a href="http://twitter.com/mickknutson">Twitter: http://twitter.com/mickknutson</a>
 * @see <a href="http://github.com/mickknutson">Git hub: http://github.com/mickknutson</a>
 * 
 * @see <a href="http://www.packtpub.com/java-ee6-securing-tuning-extending-enterprise-applications-cookbook/book">JavaEE 7 Cookbook Packt</a>
 * @see <a href="http://www.amazon.com/Cookbook-securing-extending-enterprise-applications/dp/1849683166">JavaEE 7 Cookbook Amazon</a>
 * 
 * @since 2012
 * 
 */
@RunWith(MockitoJUnitRunner.class)
public class OrderMockServiceTests {

	@InjectMocks OrderServiceImpl classUnderTest = new OrderServiceImpl();

	@Mock OrderDAO orderDao;
	
	@Mock ExampleUtils exampleUtils;
	
	@Mock UnImplementedService unImplementedService;
	
	@Before
	public void beforeEachUnitTest(){
		
		MockitoAnnotations.initMocks(this);
	}

	@After
	public void afterEachUnitTest(){}

	//----- Test methods ----------------------------------------------------//    

	@Test
	public void testGetMessage() throws Exception {
		String result = classUnderTest.getMessage();
		
		assertThat(result, is(nullValue()));
	}


	@Test
	public void testNonMockedTest() throws Exception {
		
		String result = classUnderTest.getMessage();
		
		result = classUnderTest.staticFunction();
		
		result = classUnderTest.localFunction();
		
		result = classUnderTest.voidedFunction();
		
		result = classUnderTest.staticFunctions();
		
		result = classUnderTest.complexFunctions();
		
		result = classUnderTest.duplicateCallsFunction();
		
		result = classUnderTest.executeInternalPrivate();
		
		//assertThat(result, is(nullValue()));
	}


	//----- Mock Test methods -----------------------------------------------//
	/*-----------------------------------------------------------------------//
	 * Mockito:
	 * http://code.google.com/p/mockito/
	 * 
	 * PowerMock:
	 * http://code.google.com/p/powermock/
	 * 
	 * PowerMock Mockito Extensions:
	 * http://code.google.com/p/powermock/wiki/MockitoUsage13
	 *-----------------------------------------------------------------------*/

	/**
	 * Basic
	 * 
	 * Simple Mock used in a Class under test.
	 * 
	 * @throws Exception
	 */
	@Test // basic
	public void simpleMock() throws Exception {
		
		Order order = new Order();		
		order.setDescription("Mick's Order");
		
		Order mockOrder = new Order();
		mockOrder.setDescription("Someone Else's Order");
		
		when(orderDao.placeOrder(any(Order.class)))
			.thenReturn(mockOrder);
		
		// execute
		Order newOrder = classUnderTest.placeOrder(order);
		
		assertThat(newOrder.getDescription(), is("Someone Else's Order"));

		verify(orderDao).placeOrder(any(Order.class));
	}

	/**
	 * Basic
	 * 
	 * Partially mock the Class under test to mock certain methods.
	 * 
	 * @throws Exception
	 */
	@Test
	public void partialMock() throws Exception {
		
		// Not mocked
		String result = classUnderTest.getMessage();
		
		assertThat(result, is(nullValue()));
		
		// Mock
		OrderServiceImpl partialOrderServiceMock = spy(classUnderTest);
		
		when(partialOrderServiceMock.getMessage())
			.thenReturn("partially mocked message");
		
		String partialResult = partialOrderServiceMock.getMessage();
		
		assertThat(partialResult, is("partially mocked message"));

		verify(partialOrderServiceMock).getMessage();
	}

	/**
	 * Basic
	 * 
	 * Mock a class to throw an exception inside the Class under test.
	 * 
	 * @throws Exception
	 */
	@Test
	public void mockException() throws Exception {		

		when(exampleUtils.nestedFunction())
			.thenThrow(new RuntimeException("mock Exception"));
		
		String result = classUnderTest.localFunction();

		assertThat(result, is("OrderServiceImpl: localFunction: mock Exception"));

		verify(exampleUtils).nestedFunction();
	}

	/*TODO: @Test //basic
	public void mockNotInvoked() throws Exception {

		when(exampleUtils.nestedFunction())
			.thenReturn("un-used mocked message");

		// Omit the Mock to simulate this test
		//orderService.exampleUtils = exampleUtils;
		/////orderService.exampleUtils = new ExampleUtils();
		
		String result = orderService.shadowedeFunction();

		assertThat(result, is("OrderServiceImpl: localFunction: nestedFunction(): privateFunction"));

		// This will fail:
		//verify(exampleUtils).nestedFunction();

		verify(exampleUtils, never()).nestedFunction();
	}*/
	
	/*TODO: @Test //int
	public void mockMultipleExceptions() throws Exception {
		//orderService.complexFunctions()
		
	}*/

	/**
	 * Basic
	 * 
	 * Stub the call to a method that returns void inside the 
	 * Class under test.
	 * 
	 * @throws Exception
	 */
	@Test
	public void stubVoidMock() throws Exception {

		doThrow(new RuntimeException("void stubbed void method"))
			.when(exampleUtils)
			.voidMethod();

		String result = classUnderTest.voidedFunction();

		assertThat(result, is("OrderServiceImpl: localFunction: void stubbed void method"));

		verify(exampleUtils).voidMethod();
	}

	/**
	 * Intermediate
	 * 
	 * Create a mock to be called multiple times by the Class under test.
	 * 
	 * @throws Exception
	 */
	@Test
	public void multipleCallsToMock() throws Exception {		

		when(exampleUtils.nestedFunction())
			.thenReturn("1st mocked message")
			.thenReturn("2nd mocked message")
			.thenReturn("nth mocked message");
		
		String result = classUnderTest.duplicateCallsFunction();

		assertThat(result, is("OrderServiceImpl: duplicateCallsFunction(): 1st mocked message: 2nd mocked message: nth mocked message: nth mocked message"));

		verify(exampleUtils, atLeastOnce()).nestedFunction();

		verify(exampleUtils, atLeast(2)).nestedFunction();

		verify(exampleUtils, times(4)).nestedFunction();
	}

	/**
	 * Intermediate
	 * 
	 * Mock an un implemented Interface such that would be
	 * done during Test Driven Development (TDD)
	 * 
	 * @throws Exception
	 */
	@Test
	public void mockUnImplementedService() throws Exception {

		when(unImplementedService.getMessage())
			.thenReturn("unImplementedService message");
		
		String result = unImplementedService.getMessage();

		assertThat(result, is("unImplementedService message"));

		verify(unImplementedService).getMessage();
	}
	
	/**
	 * Advanced
	 * 
	 * Create a mock that delays the response back to the
	 * Class under test.
	 * 
	 * @throws Exception
	 */
	@Test
	public void timeDelayMock() throws Exception {
		
		when(exampleUtils.nestedFunction())
			.thenAnswer(				
				delayedAnswerWithObject("delayed response", 50)
			);

		long start = new Date().getTime();

		String result = classUnderTest.localFunction();
		
		long end = new Date().getTime();
		
		assertThat(result, is("OrderServiceImpl: localFunction: delayed response"));

		assertThat(end - start, is(greaterThan(49L)));

		verify(exampleUtils, times(1)).nestedFunction();
	}

	// Helper method for mock delays.
	@SuppressWarnings("rawtypes")
	private static Answer delayedAnswerWithObject(final Object o,
			final long delay) {
		
		return new Answer() {

			public Object answer(InvocationOnMock invocation) throws Throwable {
				Thread.sleep(delay);
				return o;
			}
		};
	}

} // the end...