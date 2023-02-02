package com.clairvoyant.services.skillmatrix.graphql.resolver.query;

import com.clairvoyant.services.skillmatrix.model.Designation;
import com.clairvoyant.services.skillmatrix.service.DesignationService;
import graphql.kickstart.tools.GraphQLQueryResolver;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DesignationQueryResolver implements GraphQLQueryResolver {
    @Autowired
    private DesignationService service;


    /**
     * getAllDesignations is used to get all the Designations
     */
    public List<Designation> getAllDesignations(Optional<Boolean> isActive) {
        return service.findAll(isActive);
    }

    /**
     * getDesignationById is used to get the Designation based on the passed designationId
     */
    public Designation getDesignationById(String designationId, Optional<Boolean> isActive) {
        return service.findById(designationId, isActive);
    }
}
