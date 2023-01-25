package com.clairvoyant.clarise.graphql.resolver.query;

import com.clairvoyant.clarise.model.Designation;
import com.clairvoyant.clarise.service.DesignationService;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class DesignationQueryResolver implements GraphQLQueryResolver {
    @Autowired
    private DesignationService service;


    /**
     * getAllDesignations is used to get all the Designations
     */
    public List<Designation> getAllDesignations(Optional<Boolean> isActive){
        return service.findAll(isActive);
    }

    /**
     * getDesignationById is used to get the Designation based on the passed designationId
     */
    public Designation getDesignationById(String designationId, Optional<Boolean> isActive){
        return service.findById(designationId,isActive);
    }
}
