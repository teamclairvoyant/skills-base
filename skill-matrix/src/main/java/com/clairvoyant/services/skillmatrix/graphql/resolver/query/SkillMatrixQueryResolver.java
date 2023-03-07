package com.clairvoyant.services.skillmatrix.graphql.resolver.query;

import com.clairvoyant.services.skillmatrix.dto.SkillMatrixResponse;
import com.clairvoyant.services.skillmatrix.service.SkillMatrixService;
import graphql.kickstart.tools.GraphQLQueryResolver;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SkillMatrixQueryResolver implements GraphQLQueryResolver {

    @Autowired
    private SkillMatrixService skillMatrixService;

    public SkillMatrixResponse getSkillMatrix(String userId, List<String>categoryIds) {

        return skillMatrixService.getSkills(userId, categoryIds);
    }
}
