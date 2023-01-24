package com.clairvoyant.clarise.graphql.resolver.mutation;

import com.clairvoyant.clarise.enums.Status;
import com.clairvoyant.clarise.model.Skill;
import com.clairvoyant.clarise.service.SkillService;
import graphql.kickstart.tools.GraphQLMutationResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SkillMutationResolver implements GraphQLMutationResolver {
    @Autowired
    private SkillService skillService;

    public Skill addOrUpdateSkill(String skillName, String description, Boolean isActive) {
        Skill skill = Skill.builder()
                .skillName(skillName)
                .description(description)
                .isActive(isActive)
                .build();

        return skillService.addOrUpdateSkill(skill);
    }
    public Status deleteSkill(String skillId) {
        skillService.deleteSkill(skillId);
        return Status.SUCCESS;
    }
}
