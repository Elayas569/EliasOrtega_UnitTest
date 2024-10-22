package com.mayab.quality.loginunittest.service;

import com.mayab.quality.loginunittest.dao.IDAOUser;
import com.mayab.quality.loginunittest.model.*;

public class LoginService {
    private IDAOUser dao;

    public LoginService(IDAOUser dao) {
        this.dao = dao;

    }

    public boolean login(String username, String pass) {
        User u = dao.findUserByUsername(username);
        if (u != null) {
            if (u.getPassword() == pass) {
                return true;
            } else {
                return false;
            }

        } else {
            return false;
        }
    }

}
