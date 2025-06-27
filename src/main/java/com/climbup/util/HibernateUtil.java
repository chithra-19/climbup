// hibernateutil is common for all class which is hibernate setup , for each class we need to import hibernateutil

package com.climbup.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	
	/*This declares and initializes the sessionFactory when the class is first loaded.
	It calls the helper method buildSessionFactory() to create the object.*/

	public static final SessionFactory sessionFactory = buildSessionFactory();
	
	
	/*This is the helper method that contains the actual logic to create the SessionFactory.
	It's separate from the declaration to keep code clean and handle errors (try-catch).*/
	
	private static SessionFactory buildSessionFactory() {
		try {
			return new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
		}
		catch(Throwable ex) {
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
/*new Configuration().configure():

Reads hibernate.cfg.xml (from resources/ folder by default).

Applies all settings (like DB URL, dialect, mappings, etc.).

.buildSessionFactory():

Creates the actual SessionFactory based on those settings.*/
