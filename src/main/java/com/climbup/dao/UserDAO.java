package com.climbup.dao;
import com.climbup.util.HibernateUtil;

import com.climbup.model.User;
//User: The model/entity class representing a table in the database.
import org.hibernate.Session;
//Session: A Hibernate interface used to interact with the database.
import org.hibernate.Transaction;
//Transaction: Manages atomic operations (begin, commit, rollback).
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
//Used to configure Hibernate settings from the hibernate.cfg.xml file.

public class UserDAO {
	public void saveUser(User user) {
		Transaction transaction = null;
		try(Session session = HibernateUtil.getSessionFactory().openSession()){
			transaction = session.beginTransaction();
			session.save(user);
			transaction.commit();
			
			System.out.println("user saved sucessfully");		
			
		}
		catch (Exception e) {
			if(transaction != null) transaction.rollback();
			e.printStackTrace();
		}
		}
	public User getUserByEmail(String email) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()){
			return session.createQuery("FROM User WHERE email = :email", User.class)
						.setParameter("email" , email)
						.uniqueResult();
		}
	}
	public User getUserByEmailAndPassword(String email, String password) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
/*This Java code defines a Data Access Object (DAO) class UserDAO for managing database operations related to the User model,
 *  using Hibernate, which is a popular Object-Relational Mapping (ORM) tool for Java.
 */