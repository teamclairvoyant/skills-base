package com.prd.skillbase.service;


import com.prd.skillbase.model.User;
import com.prd.skillbase.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("userService")
public class UserServiceImpl implements UserService {


    private UserRepository userRepository;


    @Autowired
    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public User findByEmail(String email){
        return userRepository.findByEmail(email);
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(String employeeId) {
        Optional<User> result = userRepository.findById(employeeId);

        User tempEmployee = null;

        if(result.isPresent()) {
            tempEmployee =result.get();
        }

        return tempEmployee;
    }


}
