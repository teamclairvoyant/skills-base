package com.clairvoyant.clarise.graphql.resolver.mutation;

import com.clairvoyant.clarise.dto.UserDto;
import com.clairvoyant.clarise.enums.Status;
import com.clairvoyant.clarise.service.UserService;
import graphql.kickstart.tools.GraphQLMutationResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

@Component
@Validated
public class UserMutationResolver implements GraphQLMutationResolver {
    @Autowired
    private UserService userService;


    /**
     * addOrUpdateUser is used to save or update the user
     */
    public Status addOrUpdateUser(@Valid UserDto userDto) {
        return userService.addOrUpdateUser(userDto);
    }


    /**
     * deleteUser is used to delete the user
     */
    public Status deleteUser(String userId) {
        return userService.delete(userId);
    }

}
