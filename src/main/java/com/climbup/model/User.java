package com.climbup.model;

import javax.persistence.*;
//This gives your class access to JPA annotations — the standard way to tell Hibernate how to treat your class and fields.
//import javax.persistence.*; is essential to use @Entity, @Id, @Column, etc. — which Hibernate uses to map Java to SQL.

@Entity  //Required to tell Hibernate this is a mapped class
@Table(name="users") //(Optional) rename DB table
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private int id;
	
	private String username;
	
	private String email;
	
	private String password;
	
	public User() {
		
	}
	public User(String username , String password) {
		this.username = username;
		this.password = password;
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	

}

/*

What Happens If You Don't Add It?
You’ll get an error like:

org.hibernate.MappingException: Unknown entity: com.climbup.model.User
Because Hibernate will have no idea your User class exists as a mapped entity.

🧱 Multiple Entity Classes?
Yup, you add one <mapping class="..."/> per entity, like this:

<mapping class="com.climbup.model.User"/>
<mapping class="com.climbup.model.Task"/>
<mapping class="com.climbup.model.Whatever"/>

 
 
 
<mapping class="com.climbup.model.User"/>
inside hibernate.cfg.xml?

✅ Simple Answer:
It tells Hibernate:

“This class User should be mapped to a database table.”

Hibernate doesn’t scan your project automatically (unless you're using advanced frameworks like Spring Boot with annotations + auto-scan).

So unless you're using those, you must explicitly tell Hibernate which classes are entities — and you do that with <mapping>.
*/
