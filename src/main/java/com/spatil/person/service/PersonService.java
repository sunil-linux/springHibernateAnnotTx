package com.spatil.person.service;

import java.util.List;

import com.spatil.person.model.Person;

public interface PersonService {

	public void addPerson(Person person);	
	public void updatePerson(Person person);
	public void deletePerson(Integer personId);
	public Person findPersonById(Integer personId);
	public Person findPersonByName(String personName);
	public List<Person> getAllPersons();
		
}
