package com.climbup.dao;

import com.climbup.model.Task;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.climbup.util.HibernateUtil;

import java.util.List;

public class TaskDAO {
	
	public void saveTask(Task task) {
		Transaction transaction = null;
		try(Session session = HibernateUtil.getSessionFactory().openSession()){
			transaction = session.beginTransaction();
			session.save(task);
			transaction.commit();
		}catch(Exception e) {
			if(transaction != null) transaction.rollback();
			e.printStackTrace();
		}
		
	}
	
	public List<Task> getTaskByUserId(int userId){
		try (Session session = HibernateUtil.getSessionFactory().openSession()){
			return session.createQuery("FROM Task WHERE User.id = :userId", Task.class)
					.setParameter("userId", userId)
					.list();
		}
	}

}
