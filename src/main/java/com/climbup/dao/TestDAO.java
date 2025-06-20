package com.climbup.dao;


import java.time.LocalDate;
import java.util.List;

import com.climbup.model.*;

public class TestDAO {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		UserDAO userDAO = new UserDAO();
        TaskDAO taskDAO = new TaskDAO();

        // 1. Save user
        User user = new User();
        user.setUsername("Test User");
        user.setEmail("test@example.com");
        user.setPassword("123456");
        userDAO.saveUser(user);

        // 2. Get user by email
        User fetchedUser = userDAO.getUserByEmail("test@example.com");
        System.out.println("Fetched User: " + fetchedUser.getUsername());

        // 3. Save task
        Task task = new Task();
        task.setTitle("Test Task");
        task.setDueDate(LocalDate.now().plusDays(1));
        task.setUser(fetchedUser);
        taskDAO.saveTask(task);

        // 4. Get tasks for user
        List<Task> tasks = taskDAO.getTaskByUserId(fetchedUser.getId());
        System.out.println("Tasks for User: " + tasks.size());

	}

}
