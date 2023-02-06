package com.clairvoyant.services.skillmatrix.graphql.resolver.mutation;

import com.clairvoyant.services.skillmatrix.dto.QualificationDto;
import com.clairvoyant.services.skillmatrix.enums.Status;
import com.clairvoyant.services.skillmatrix.service.QualificationService;
import graphql.kickstart.tools.GraphQLMutationResolver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class QualificationMutationResolver implements GraphQLMutationResolver {

    @Autowired
    private QualificationService qualificationService;

    public QualificationDto addOrUpdateQualification(QualificationDto qualificationDto) {
        return qualificationService.addOrUpdateQualification(qualificationDto);
    }

    public Status deleteQualification(String qualificationId) {
        qualificationService.deleteQualification(qualificationId);
        return Status.SUCCESS;
    }
}
