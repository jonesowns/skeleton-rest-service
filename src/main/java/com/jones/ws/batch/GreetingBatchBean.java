package com.jones.ws.batch;


import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.jones.ws.model.Greeting;
import com.jones.ws.service.GreetingService;


@Profile("batch") 
@Component
public class GreetingBatchBean {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass()); 

	@Autowired
	private GreetingService greetingService;
	
	
	@Scheduled (
			cron = "${batch.greeting.cron}"	)
	public void cronjob() {
		logger.info(">cronJob");
		
		//Add Scheduled logic here
		Collection<Greeting> greetings = greetingService.findall();
		logger.info("There are {} greetings in the data store", 
				      greetings.size());
		
		logger.info("<cronjob");
		
	}
	
	
	@Scheduled (
		initialDelayString = "${batch.greeting.initialDelay}",
			fixedRateString = "${batch.greeting.fixedRate}")
	public void fixedRateJobWithInitialDelay() {
		logger.info(">fixedRateJobWithInitialDelay");
		
		//Add schedule logic here
		//Simulating job processing time
		
		long pause = 5000;
		long start = System.currentTimeMillis();
		do {
			if (start + pause < System.currentTimeMillis()) {
				break;
			} 	
		} while (true);
		logger.info("processing time was {} seconds", pause/1000);
	

		logger.info("<fixedRateJobWithInitialDelay");
	}
	
	
	@Scheduled (
    		initialDelayString = "${batch.greeting.initialDelay}",
		fixedDelayString = "${batch.greeting.fixedDelay}")
		public void fixedDelayJobWithInitialDelay() {
			logger.info(">fixedDelayJobWithInitialDelay");
			
			//Add schedule logic here
			//Simulating job processing time
			
			long pause = 5000;
			long start = System.currentTimeMillis();
			do {
				if (start + pause < System.currentTimeMillis()) {
					break;
				} 	
			} while (true);
			logger.info("processing time was {} seconds", pause/1000);
		

			logger.info("<fixedDelayJobWithInitialDelay");
		}
	
}
