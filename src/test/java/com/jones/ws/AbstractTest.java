package com.jones.ws;




import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;






/**
 * The AbstractTest class is the parent of all JUnit test classes. This class
 * configures the test ApplicationContext and test runner environment.
 * 
 * @author 
 */


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes=Application.class)


public abstract class AbstractTest {

/*	
	The logger instance for all classes of the unit test Framework

*/

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	

}


