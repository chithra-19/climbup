package com.climbup.test;

import com.climbup.dao.UserDAO;
import com.climbup.model.User;

public class TestUserSave {
    public static void main(String[] args) {
        User user = new User("chithra", "password123");
        UserDAO dao = new UserDAO();
        dao.saveUser(user);
    }
}