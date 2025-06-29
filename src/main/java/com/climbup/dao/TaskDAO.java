package com.climbup.dao;

import com.climbup.model.Task;
import com.climbup.model.Task.TaskStatus;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.climbup.util.HibernateUtil;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
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
					.getResultList();
			
		}
	}
	
	public void addTask(Task task) {
	    Session session = HibernateUtil.getSessionFactory().openSession();
	    Transaction tx = session.beginTransaction();
	    session.save(task);
	    tx.commit();
	    session.close();
	}
	
	public List<Task> getTasksByDate(String filter, int userId) {
	    Session session = HibernateUtil.sessionFactory.openSession();
	    String hql = "";
	    
	    switch (filter) {
	        case "today":
	            hql = "FROM Task WHERE userId = :uid AND DATE(taskDate) = CURDATE()";
	            break;
	        case "upcoming":
	            hql = "FROM Task WHERE userId = :uid AND DATE(taskDate) > CURDATE()";
	            break;
	        case "missed":
	            hql = "FROM Task WHERE userId = :uid AND DATE(taskDate) < CURDATE() AND status = 'pending'";
	            break;
	        default:
	            hql = "FROM Task WHERE userId = :uid";
	    }

	    Query query = session.createQuery(hql);
	    query.setParameter("uid", userId);
	    List<Task> list = query.list();
	    session.close();
	    return list;
	}
	
	
	public void updateStatus(int taskId, TaskStatus status) {
	    Session session = HibernateUtil.sessionFactory.openSession();
	    Transaction tx = session.beginTransaction();
	    Task task = session.get(Task.class, taskId);
	    if (task != null) {
	        task.setStatus(status);
	        if (status == TaskStatus.COMPLETED) {
	            task.setCompletedAt(LocalDateTime.now());
	        }
	        session.update(task);
	    }
	    tx.commit();
	    session.close();
	}
	
	
	public List<Task> getCompletedTasksLast7Days(int userId) {
	    Session session = HibernateUtil.sessionFactory.openSession();
	    Date sevenDaysAgo = Date.from(LocalDate.now().minusDays(6)
	        .atStartOfDay(ZoneId.systemDefault()).toInstant());
	    String hql = "FROM Task WHERE userId = :uid AND completed = true AND completionDate >= :date";
	    Query query = session.createQuery(hql);
	    query.setParameter("uid", userId);
	    query.setParameter("date", sevenDaysAgo);
	    return query.list();
	}


	
	



}
