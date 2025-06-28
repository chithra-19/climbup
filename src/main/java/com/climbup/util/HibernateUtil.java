// hibernateutil is common for all class which is hibernate setup , for each class we need to import hibernateutil

package com.climbup.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	
	/*When HibernateUtil is first loaded, Java calls buildSessionFactory().
	That method runs only once, and returns the SessionFactory.
	The result is saved in the final variable sessionFactory.
	
	//Declares a static, final field — so it's created once and shared across your entire application.*/
	public static final SessionFactory sessionFactory = buildSessionFactory();
	
	
	/*This is the helper method that contains the actual logic to create the SessionFactory.
	It's separate from the declaration to keep code clean and handle errors (try-catch).*/
	
	/*This actually builds the SessionFactory.
	This line is only called once, during class loading.
	After that, the result is reused.*/
	
	private static SessionFactory buildSessionFactory() {
		try {
			return new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
		}
		catch(Throwable ex) {
			System.err.println("Initial sessionfactory creation failed" +ex);
			throw new ExceptionInInitializerError(ex);
		}
		
	}
	
	/*we use Encapsulation to:
	Prevent direct access to the sessionFactory field
	Provide controlled, safe access through a public method*/
	
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
		//We only create it once, and then return the same one every time through:
		}
	public static void shutdown() {
		getSessionFactory().close();//)	Closes the entire Hibernate system (SessionFactory)
		//Just returns the already-built object

	}

}
/*
 


 
| What happens            | How often      | Purpose                               |
| ----------------------- | -------------- | ------------------------------------- |
| `buildSessionFactory()` | **Once**       | Actually builds the `SessionFactory`  |
| `getSessionFactory()`   | **Many times** | Just returns the already-built object |
*/
