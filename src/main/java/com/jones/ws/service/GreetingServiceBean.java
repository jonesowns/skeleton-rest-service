package com.jones.ws.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.jones.ws.model.Greeting;
import com.jones.ws.repository.GreetingRepository;

@Service
@Transactional(
			propagation = Propagation.SUPPORTS,
			readOnly = true)
public class GreetingServiceBean implements GreetingService {

	
	@Autowired
	GreetingRepository greetingRepository;
	
	
	@Override
	public Collection<Greeting> findall() {
		Collection<Greeting> greetings = greetingRepository.findAll();
		return greetings;
	}

	@Override
	@Cacheable( 
			value = "greeting",
			key = "#id")
	public Greeting findOne(Long id) {
		Greeting greeting = greetingRepository.findOne(id);
		return greeting;
	}

	@Override
	@Transactional(
			propagation = Propagation.REQUIRED,
			readOnly = false)
	@CachePut( 
			value="greeting",
			key = "#result.id")
	public Greeting create(Greeting greeting) {
		if(greeting.getId() != null) {
			// cannot create if id is provided
			return null;
		}
		Greeting savedGreeting = greetingRepository.save(greeting);
		return savedGreeting;
	}

	@Override
	@Transactional(
			propagation = Propagation.REQUIRED,
			readOnly = false)
	@CachePut( 
			value = "greeting",
			key = "#greeting.id" )
	public Greeting update(Greeting greeting) {
		Greeting greetingPersisted = greetingRepository.findOne(greeting.getId());
		if (greetingPersisted == null) {
			return null;
		}
		
		Greeting updatedGreeting = greetingRepository.save(greeting);
		return updatedGreeting;
	}

	@Override
	@Transactional(
			propagation = Propagation.REQUIRED,
			readOnly = false)
	@CacheEvict(
			value = "greeting",
			key = "#id")
	public void delete(Long id) {
		greetingRepository.delete(id);

	}

	
	@Override
	@CacheEvict (
			value = "greeting", 
			allEntries = true)
	public void evictCache() {
			
	}
	
}
