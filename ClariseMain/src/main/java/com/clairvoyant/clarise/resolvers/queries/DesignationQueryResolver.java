package com.clairvoyant.clarise.resolvers.queries;

import com.clairvoyant.clarise.entities.Designation;
import com.clairvoyant.clarise.service.DesignationService;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DesignationQueryResolver implements GraphQLQueryResolver {

    @Autowired
    private DesignationService service;

    public List<Designation> getAllDesignations(){
       return service.findAll();
    }

    public Designation getDesignationById(String designationId){
        return service.findById(designationId);
    }
}
