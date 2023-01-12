package com.clairvoyant.clarise.resolvers;

import com.clairvoyant.clarise.model.Designation;
import com.clairvoyant.clarise.repository.DesignationRepository;
import graphql.kickstart.tools.GraphQLResolver;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.util.Optional;

@Component
public class DesignationFieldResolvers implements GraphQLResolver<Designation> {

    private DesignationRepository designationRepository;
    private Optional<Designation> byId;
    private Optional<Designation> optionalDesignation;

    public DesignationFieldResolvers(DesignationRepository designationRepository) {
        this.designationRepository = designationRepository;
    }



    public String createdBy(Designation designation)
    {
        Optional<Designation> optionalDesignation = designationRepository.findById(designation.getId());
        String createdBy=null;
        if(optionalDesignation.isPresent())
        {
            createdBy = optionalDesignation.get().getCreatedBy();
        }
        return createdBy;
    }


    public ZonedDateTime createdOn(Designation designation) {
        Optional<Designation> optionalDesignation = designationRepository.findById(designation.getId());

        ZonedDateTime zdt = null;
        if (optionalDesignation.isPresent()) {
            zdt = optionalDesignation.get().getCreatedDate();
        }

        return zdt;
    }

    public String updatedBy(Designation designation)
    {
        Optional<Designation> optionalDesignation = designationRepository.findById(designation.getId());
        String updatedBy=null;
        if(optionalDesignation.isPresent())
        {
            updatedBy = optionalDesignation.get().getModifiedBy();
        }
        return updatedBy;
    }

    public ZonedDateTime updatedOn(Designation designation)
    {
        Optional<Designation> optionalDesignation = designationRepository.findById(designation.getId());

        ZonedDateTime zdt = null;
        if (optionalDesignation.isPresent()) {
            zdt = optionalDesignation.get().getModifiedDate();
        }

        return zdt;
    }
}
