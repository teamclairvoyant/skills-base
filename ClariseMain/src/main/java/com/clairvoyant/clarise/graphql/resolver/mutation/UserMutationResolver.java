package com.clairvoyant.clarise.graphql.resolver.mutation;

import com.clairvoyant.clarise.dto.UserDto;
import com.clairvoyant.clarise.dto.UserResponseDto;
import com.clairvoyant.clarise.enums.Status;
import com.clairvoyant.clarise.service.UserService;
import graphql.kickstart.tools.GraphQLMutationResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserMutationResolver implements GraphQLMutationResolver {
    @Autowired
    private UserService userService;

    public UserResponseDto addOrUpdateUser(UserDto userDto) {
        return userService.addOrUpdateUser(userDto);
    }

    public Status deleteUser(String userId) {
        return userService.delete(userId);
    }

}
