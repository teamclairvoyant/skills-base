package com.clairvoyant.clarise.resolvers.queries;

import com.clairvoyant.clarise.service.UserService;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.clairvoyant.clarise.model.User;

import java.util.List;

@Component
public class UserQueryResolver implements GraphQLQueryResolver {

    @Autowired
    private UserService userService;

    public List<User> getAllUsers(){
       return userService.findAll();
    }
}
