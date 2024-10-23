package com.mayab.quality.loginunittest.dao;

import java.util.List;

import com.mayab.quality.loginunittest.model.User;

public interface IDAOUser {
    public User findUserByUsername(String username);

    public User findUserByEmail(String email);

    public boolean logIn(String username, String password);

    public boolean registerUser(User user);

    public boolean deleteUser(String username);

    public User updateUser(User user);

    int save(User user);

    List<User> findAll();

    User findById(int id);

    boolean deleteById(int id);

}
