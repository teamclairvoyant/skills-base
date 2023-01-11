package com.clairvoyant.clarise.resolvers.mutation;

import com.clairvoyant.clarise.input.UserInput;
import com.clairvoyant.clarise.service.UserService;
import graphql.kickstart.tools.GraphQLMutationResolver;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.clairvoyant.clarise.model.User;

@Component
public class UserMutationResolver implements GraphQLMutationResolver {

    @Autowired
    private UserService userService;

    public User addOrUpdateUser(UserInput userInput){
        User user = new User();
        BeanUtils.copyProperties(userInput,user);
        return userService.save(user);
    }


}
