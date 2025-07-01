package com.climbup.servlet;

import com.climbup.dao.TaskDAO;
import com.climbup.model.Task;
import com.climbup.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/dashboard")
public class DashboardServlet extends HttpServlet {
    private TaskDAO taskDAO;

    @Override
    public void init() throws ServletException {
        taskDAO = new TaskDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Check if user is logged in
        HttpSession session = request.getSession(false);
        User loggedInUser = (User) session.getAttribute("user");

        if (loggedInUser == null) {
            response.sendRedirect("login.jsp");
            return;
        }


        String filter = request.getParameter("filter");
        List<Task> taskList;

        if (filter != null && !filter.isEmpty()) {
            taskList = taskDAO.getTasksByDate(filter, loggedInUser.getId());
        } else {
            taskList = taskDAO.getTaskByUserId(loggedInUser.getId());
        }

        request.setAttribute("taskList", taskList);
        request.setAttribute("filter", filter); // optional, for highlighting active filter
        request.getRequestDispatcher("dashboard.jsp").forward(request, response);
        
        int userId = loggedInUser.getId();  
        List<Task> recentTasks = taskDAO.getCompletedTasksLast7Days(userId);
        int score = (recentTasks.size() * 100) / 7;
        request.setAttribute("score", score);
        
        int userId1 = (Integer) session.getAttribute("userId");
        List<Task> recentTasks1 = taskDAO.getCompletedTasksByUserInLastNDays(userId1, 30);
        int streak = calculateStreak(recentTasks1);
        request.setAttribute("streak", streak);


    }

	private int calculateStreak(List<Task> recentTasks1) {
		// TODO Auto-generated method stub
		return 0;
	}

}
