package com.clairvoyant.clarise.service;

import com.clairvoyant.clarise.model.User;

import java.util.List;

public interface UserService {

    public User findByEmail(String email);

    public void saveUser(User user);

    public List<User> findAll();

    public User findById(String employeeId);
}
