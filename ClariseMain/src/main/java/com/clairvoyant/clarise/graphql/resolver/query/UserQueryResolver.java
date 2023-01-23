package com.clairvoyant.clarise.graphql.resolver.query;

import com.clairvoyant.clarise.dto.UserDto;
import com.clairvoyant.clarise.dto.UserResponseDto;
import com.clairvoyant.clarise.repository.UserRepository;
import com.clairvoyant.clarise.service.UserService;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserQueryResolver implements GraphQLQueryResolver {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    public UserResponseDto getUserById(String userId){
        return userService.findById(userId);
    }

    public List<UserResponseDto> getAllUsers(){
        return userService.findAll();
    }
}
