package com.clairvoyant.services.skillmatrix.graphql.resolver.query;

import com.clairvoyant.services.skillmatrix.dto.UserResponseDto;
import com.clairvoyant.services.skillmatrix.repository.UserRepository;
import com.clairvoyant.services.skillmatrix.service.UserService;
import graphql.kickstart.tools.GraphQLQueryResolver;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserQueryResolver implements GraphQLQueryResolver {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;


    /**
     * getUserById is used to get the User based on the passed userId
     */
    public UserResponseDto getUserById(String userId, Optional<Boolean> isActive) {
        return userService.findById(userId, isActive);
    }

    /**
     * getAllUsers is used to get all the Users
     */
    public List<UserResponseDto> getAllUsers(Optional<Boolean> isActive) {
        return userService.findAll(isActive);
    }
}
