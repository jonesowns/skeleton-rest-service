package com.jones.ws.service;

import java.util.Collection;

import com.jones.ws.model.Greeting;

public interface GreetingService {

	Collection<Greeting> findall();

	Greeting findOne(Long id);

	Greeting create(Greeting greeting);

	Greeting update(Greeting greeting);

	void delete(Long id);

}
