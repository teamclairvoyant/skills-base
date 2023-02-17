package com.clairvoyant.services.skillmatrix.graphql.resolver.mutation;

import com.clairvoyant.services.skillmatrix.dto.SkillDto;
import com.clairvoyant.services.skillmatrix.enums.Status;
import com.clairvoyant.services.skillmatrix.model.Skill;
import com.clairvoyant.services.skillmatrix.service.SkillService;
import graphql.kickstart.tools.GraphQLMutationResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SkillMutationResolver implements GraphQLMutationResolver {
    @Autowired
    private SkillService skillService;

    public Skill addOrUpdateSkill(String skillName, String description, Boolean isActive) {
        var skillDto = SkillDto.builder()
                .skillName(skillName)
                .description(description)
                .isActive(isActive)
                .build();

        return skillService.addOrUpdateSkill(skillDto);
    }
    public Status deleteSkill(String skillId) {
        skillService.deleteSkill(skillId);
        return Status.SUCCESS;
    }
}
