package com.clairvoyant.clarise.graphql.resolver.query;

import com.clairvoyant.clarise.dto.UserDto;
import com.clairvoyant.clarise.dto.UserResponseDto;
import com.clairvoyant.clarise.repository.UserRepository;
import com.clairvoyant.clarise.service.UserService;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Component
public class UserQueryResolver implements GraphQLQueryResolver {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;


    /**
     * getUserById is used to get the User based on the passed userId
     */
    public UserResponseDto getUserById(String userId, Optional<Boolean> isActive){
        return userService.findById(userId,isActive);
    }

    /**
     * getAllUsers is used to get all the Users
     */
    public List<UserResponseDto> getAllUsers(Optional<Boolean> isActive){
        return userService.findAll(isActive);
    }
}
