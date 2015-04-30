package com.spatil.person.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spatil.person.dao.PersonDao;
import com.spatil.person.model.Person;
import com.spatil.person.service.PersonService;

@Service("personService")
public class PersonServiceImpl implements PersonService{

	@Autowired
	private PersonDao personDao;
	
	public PersonDao getPersonDao() {
		return personDao;
	}

	public void setPersonDao(PersonDao personDao) {
		this.personDao = personDao;
	}

	@Transactional
	public void addPerson(Person person) {
		getPersonDao().savePerson(person);
		
	}

	@Override
	public void updatePerson(Person person) {
		getPersonDao().updatePerson(person);		
	}

	@Override
	public void deletePerson(Integer personId) {
		getPersonDao().deletePerson(personId);	
		
	}

	@Override
	public Person findPersonById(Integer personId) {
		return getPersonDao().findPersonById(personId);
	}

	@Override
	public Person findPersonByName(String personName) {
		return getPersonDao().findPersonByName(personName);
	}

	@Override
	@Transactional
	public List<Person> getAllPersons() {		
		return getPersonDao().getAllPersons();
	}

}
