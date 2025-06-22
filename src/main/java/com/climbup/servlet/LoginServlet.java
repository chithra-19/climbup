package com.climbup.servlet;

import com.climbup.dao.UserDAO;
import com.climbup.model.User;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet {
	 protected void doPost(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	        
	        String email = request.getParameter("email");
	        String password = request.getParameter("password");

	        UserDAO userDAO = new UserDAO();
	        User user = userDAO.getUserByEmailAndPassword(email, password);

	        if (user != null) {
	            HttpSession session = request.getSession();
	            session.setAttribute("user", user);
	            response.sendRedirect("dashboard.jsp");
	        } else {
	            response.sendRedirect("login.jsp?error=1");
	        }
	    }

}
