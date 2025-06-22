package com.climbup.servlet;

import com.climbup.dao.UserDAO;
import com.climbup.model.User;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

public class RegisterServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        User user = new User(name, email, password);

        UserDAO userDAO = new UserDAO();
        userDAO.saveUser(user);

        response.sendRedirect("login.jsp"); // After register, go to login
    }

}
