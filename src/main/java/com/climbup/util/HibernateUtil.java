// hibernateutil is common for all class which is hibernate setup , for each class we need to import hibernateutil

package com.climbup.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

	public static final SessionFactory sessionFactory = buildSessionFactory();
	
	private static SessionFactory buildSessionFactory() {
		try {
			return new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
		}catch(Throwable ex) {
			System.err.println("Initial sessionfactory creation failed" +ex);
			throw new ExceptionInInitializerError(ex);
		}
		
	}
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
		
	}
	public static void shutdown() {
		getSessionFactory().close();
	}
		

	

}
