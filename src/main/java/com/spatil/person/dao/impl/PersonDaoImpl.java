package com.spatil.person.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Expression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.spatil.person.dao.PersonDao;
import com.spatil.person.model.Person;

@Repository("personDao")
public class PersonDaoImpl implements PersonDao {

	@Autowired
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	
	public void setSessionFactory(SessionFactory sessionFactory) {
		System.out.println("Setting the session Factoyr...");
		this.sessionFactory = sessionFactory;
	}
	
	/*Note:
		   To use @Transactional follow below step
		   1. remove <current_session_context_class> hibernate property within config XML
		   2. @Transactional
		   3. Transaction begin and commit is handled by hibernateTransactionManager

		   http://docs.spring.io/spring/docs/4.0.x/spring-framework-reference/htmlsingle/#transaction  
	*/	 

		 
	
	@Transactional(propagation=Propagation.MANDATORY)
	public void savePerson(Person person) {	
		getSessionFactory().getCurrentSession().save(person);
	}
	
	// Here i had to manually handle the transaction commit() & rollback()
	/*@Override
	public void savePerson(Person person) {
		 
		Transaction tx = null;
		try {
			Session session = sessionFactory.getCurrentSession();
			tx = session.beginTransaction();
			session.save(person);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			throw e;
		}
	}*/

	@Transactional
	public void updatePerson(Person person) {
		getSessionFactory().getCurrentSession().saveOrUpdate(person);		
	}

	@Transactional
	public void deletePerson(Integer personId) {		
		Person person = findPersonById(personId);
		sessionFactory.getCurrentSession().delete(person);	
	}

	@Override
	@Transactional
	public Person findPersonById(Integer personId) {
		return (Person)sessionFactory.getCurrentSession().load(Person.class, personId);
	}

	@Transactional
	public Person findPersonByName(String personName) {

		Session session = sessionFactory.getCurrentSession();

		Criteria criteria = session.createCriteria(Person.class);
		criteria.add(Expression.eq("name", personName));

		// Only return first matching person to upper layer.
		Person person = (Person) criteria.list().get(0);

		return person;
	}

	@Transactional
	public List<Person> getAllPersons() {

		Session session = sessionFactory.getCurrentSession();
		
		Criteria criteria = session.createCriteria(Person.class);
		List<Person> persons = (List<Person>) criteria.list();		

		// return (List<Person>) session.createQuery("from person").list();

		return persons;

	}

}
