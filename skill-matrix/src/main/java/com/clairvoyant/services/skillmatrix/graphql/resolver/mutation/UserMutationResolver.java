package com.clairvoyant.services.skillmatrix.graphql.resolver.mutation;

import com.clairvoyant.services.skillmatrix.dto.UserDto;
import com.clairvoyant.services.skillmatrix.enums.Status;
import com.clairvoyant.services.skillmatrix.service.UserService;
import graphql.kickstart.tools.GraphQLMutationResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserMutationResolver implements GraphQLMutationResolver {
    @Autowired
    private UserService userService;


    /**
     * addOrUpdateUser is used to save or update the user
     */
    public Status addOrUpdateUser(UserDto userDto) {
        return userService.addOrUpdateUser(userDto);
    }


    /**
     * deleteUser is used to delete the user
     */
    public Status deleteUser(String userId) {
        return userService.delete(userId);
    }

}
