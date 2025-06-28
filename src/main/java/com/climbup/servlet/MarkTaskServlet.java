package com.climbup.servlet;


import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.climbup.dao.TaskDAO;
import com.climbup.model.Task.TaskStatus;

@WebServlet("/task/updateStatus")
public class MarkTaskServlet extends HttpServlet {
	    private TaskDAO taskDAO = new TaskDAO();

	    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
	            throws IOException {
	        int taskId = Integer.parseInt(req.getParameter("id"));
	        String action = req.getParameter("action");

	        TaskStatus status = "complete".equals(action)
	                            ? TaskStatus.COMPLETED
	                            : TaskStatus.MISSED;

	        taskDAO.updateStatus(taskId, status);
	        resp.sendRedirect("dashboard");
	    }
}


