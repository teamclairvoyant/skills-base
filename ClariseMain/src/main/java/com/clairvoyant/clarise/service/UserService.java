package com.clairvoyant.clarise.service;

import com.clairvoyant.clarise.model.SkillsRating;
import com.clairvoyant.clarise.model.User;

import java.util.List;

public interface UserService {
    public User addOrUpdateUser(User user);

    public User findById(String id);

    public void delete(String userId);

    public List<User> findAll();
}
