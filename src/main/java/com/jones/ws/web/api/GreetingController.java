package com.jones.ws.web.api;


import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jones.ws.model.Greeting;
import com.jones.ws.service.GreetingService;

@RestController
public class GreetingController {

	@Autowired
	private GreetingService greetingService;
	
	
		
	@RequestMapping(value = "/api/greetings", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Greeting>> getGreeting() {

		Collection<Greeting> greetings = greetingService.findall();
		return new ResponseEntity<Collection<Greeting>>(greetings, HttpStatus.OK);

	}

	@RequestMapping(value = "/api/greetings/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)

	public ResponseEntity<Greeting> getGreetingById(@PathVariable("id") Long id) {

		Greeting greeting = greetingService.findOne(id);

		if (greeting == null) {
			return new ResponseEntity<Greeting>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<Greeting>(greeting, HttpStatus.OK);
		}

	}

	@RequestMapping(value = "/api/greetings", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Greeting> createGreeting(@RequestBody Greeting greeting) {

		Greeting savedGreeting = greetingService.create(greeting);
		return new ResponseEntity<Greeting>(savedGreeting, HttpStatus.CREATED);

	}

	@RequestMapping(value = "/api/greetings/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Greeting> updateGreeting(@RequestBody Greeting greeting) {

		Greeting updatedGreating = greetingService.update(greeting);
		
		if (updatedGreating == null) {
			return new ResponseEntity<Greeting>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		
		return new ResponseEntity<Greeting>(updatedGreating, HttpStatus.OK);

	}
	
	@RequestMapping(value = "/api/greetings/{id}", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Greeting> deleteGreeting(@PathVariable("id") Long id) {


		greetingService.delete(id);
		
		/*if (greetingDeleted == false) {
			return new ResponseEntity<Greeting>(HttpStatus.NOT_FOUND);
			}
		*/
		return new ResponseEntity<Greeting>( HttpStatus.NO_CONTENT);

	}
	
	
}
