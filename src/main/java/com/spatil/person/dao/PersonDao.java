package com.spatil.person.dao;

import java.util.List;

import com.spatil.person.model.Person;

public interface PersonDao {

	public void savePerson(Person person);
	public void updatePerson(Person person);
	public void deletePerson(Integer personId);
	public Person findPersonById(Integer personId);
	public Person findPersonByName(String personName);
	public List<Person> getAllPersons();
	
}
