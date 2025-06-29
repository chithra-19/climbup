package com.climbup.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.climbup.dao.TaskDAO;
import com.climbup.model.Task;
import com.climbup.model.User;
import com.climbup.model.Task.TaskStatus;

@WebServlet("/AddTaskServlet")
public class AddTaskServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 1. Get input values from form
        String title = request.getParameter("title");
        String dueDateStr = request.getParameter("dueDate");

        // 2. Get current user from session
        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("user");

        // 3. Convert date string to java.sql.Date
       
        LocalDate dueDate= LocalDate.parse(dueDateStr);
		

        // 4. Create Task object
        Task task = new Task();
        task.setTitle(title);
        task.setDueDate(dueDate);
        task.setStatus(TaskStatus.PENDING);
        task.setUser(user); // FK relationship

        // 5. Save using DAO
        TaskDAO dao = new TaskDAO();
        dao.addTask(task);

        // 6. Redirect to dashboard
        response.sendRedirect("dashboard.jsp");
    }
	

}
