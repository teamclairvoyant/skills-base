package com.clairvoyant.clarise.graphql.resolver.mutation;

import com.clairvoyant.clarise.dto.DesignationDto;
import com.clairvoyant.clarise.model.Designation;
import com.clairvoyant.clarise.service.DesignationService;
import graphql.kickstart.tools.GraphQLMutationResolver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class DesignationMutationResolver implements GraphQLMutationResolver {
    @Autowired
    private DesignationService service;


    /**
     * addOrUpdateDesignation is used to add or update the designation
     */
    public Designation addOrUpdateDesignation(DesignationDto input){
        log.info("Adding Designation {} ",input);
        Designation designation = new Designation();
        BeanUtils.copyProperties(input,designation);
        return service.save(designation);
    }

    /**
     * deleteDesignation is used to delete the designation
     */
    public String deleteDesignation(String designationId){
        Designation designation = new Designation();
        designation.setId(designationId);
        service.delete(designation);
        return "Record Deleted";
    }
}
