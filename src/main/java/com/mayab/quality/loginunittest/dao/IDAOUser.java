package com.mayab.quality.loginunittest.dao;

import com.mayab.quality.loginunittest.model.User;

public interface IDAOUser {
    public User findUserByUsername(String username);

    public boolean logIn(String username, String password);

    public boolean registerUser(User user);

    public boolean deleteUser(String username);

    public boolean updateUser(User user);

}
