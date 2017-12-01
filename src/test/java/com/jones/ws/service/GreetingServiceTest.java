package com.jones.ws.service;

import java.util.Collection;

import org.junit.After;
import org.junit.Assert;	
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.jones.ws.AbstractTest;
import com.jones.ws.model.Greeting;


/**
 * Unit test methods for the GreetingService and GreetingServiceBean.
 * 
 * @author 
 */



@Transactional
public class GreetingServiceTest extends AbstractTest {

	
	@Autowired
	private GreetingService service;
	
	@Before
	public void setup() {
		service.evictCache();
	}
	
	
	@After
	public void cleanup() {
		//clean up after each test method
	}
	
	
	@Test
	public void  testFindAll()
	{
		Collection<Greeting> list = service.findall(); 
		
		Assert.assertNotNull("faiure: expected not null", list);
		Assert.assertEquals("failure: expected size", 2, list.size());
	
	
	}
	
}
