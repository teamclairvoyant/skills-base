package com.clairvoyant.clarise.service.impl;

import com.clairvoyant.clarise.model.Designation;
import com.clairvoyant.clarise.model.Skill;
import com.clairvoyant.clarise.model.User;
import com.clairvoyant.clarise.repository.UserRepository;
import com.clairvoyant.clarise.service.UserService;
import com.clairvoyant.clarise.util.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {


    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User addOrUpdateUser(User user) {

        if(StringUtils.hasText(user.getId())) {
            Optional<User> result = userRepository.findById(user.getId());
            if (result.isPresent()) {
                if (StringUtils.hasLength(user.getName()))
                    result.get().setName(user.getName());

                if (StringUtils.hasLength(user.getEmailAddress()))
                    result.get().setEmailAddress(user.getEmailAddress());

                if(StringUtils.hasLength(user.getPassword()))
                    result.get().setPassword(PasswordUtil.encode(user.getPassword()));

                if (StringUtils.hasLength(user.getGrade()))
                    result.get().setGrade(user.getGrade());

                if (StringUtils.hasLength(user.getReportingManager()))
                    result.get().setReportingManager(user.getReportingManager());


                user = result.get();
            }

        } else {
            user.setCreatedBy("");
            user.setPassword(PasswordUtil.encode(user.getPassword()));
            user.setActive(true);
        }
        return userRepository.save(user);
    }

    @Override
    public User findById(String id) {
        return userRepository.findById(id).get();
    }

    @Override
    public void delete(String userId) {

        Optional<User> optUser = userRepository.findById(userId);
        if (optUser.isPresent()) {
            User user = optUser.get();
            user.setActive(false);
            userRepository.save(user);
        }
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }
}
