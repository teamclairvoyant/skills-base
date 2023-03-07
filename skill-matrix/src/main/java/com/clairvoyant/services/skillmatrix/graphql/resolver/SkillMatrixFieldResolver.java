package com.clairvoyant.services.skillmatrix.graphql.resolver;

import com.clairvoyant.services.skillmatrix.dto.SkillMatrixList;
import com.clairvoyant.services.skillmatrix.dto.SkillMatrixResponse;
import graphql.kickstart.tools.GraphQLResolver;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class SkillMatrixFieldResolver implements GraphQLResolver<SkillMatrixResponse> {

    public List<SkillMatrixList> skillMatrixLists(SkillMatrixResponse skillMatrixResponse) {
        return skillMatrixResponse.getSkillMatrixLists();
    }
}
