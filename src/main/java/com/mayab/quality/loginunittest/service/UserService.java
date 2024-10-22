package com.mayab.quality.loginunittest.service;

import com.mayab.quality.loginunittest.dao.IDAOUser;
import com.mayab.quality.loginunittest.model.User;

public class UserService {
    private IDAOUser dao;

    public UserService(IDAOUser dao) {
        this.dao = dao;
    }

    /*
     * public User createUser(String name, String email, String password){
     * User user = null;
     * if (password.length() >= 8 || password.length() <= 16){
     * user = dao.findUserByUsername(email);
     * if (user != null){
     * return user;
     * } else {
     * user = new User(name, email, password);
     * int id = dao.save(user);
     * user.setId(id);
     * }
     * }
     * else{
     * return user;
     * }
     * }
     */

    public User createUser(String name, String email, String password) {
        if (password.length() < 8 || password.length() > 16) {
            return null;
        }

        User existingUser = dao.findUserByEmail(email);
        if (existingUser != null) {
            return existingUser;
        }

        User newUser = new User(name, email, password);
        int id = dao.save(newUser);
        newUser.setId(id);

        return newUser;
    }

}