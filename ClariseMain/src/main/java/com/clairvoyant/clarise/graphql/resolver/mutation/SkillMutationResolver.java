package com.clairvoyant.clarise.graphql.resolver.mutation;

import com.clairvoyant.clarise.dto.SkillDto;
import com.clairvoyant.clarise.enums.Status;
import com.clairvoyant.clarise.service.SkillService;
import graphql.kickstart.tools.GraphQLMutationResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SkillMutationResolver implements GraphQLMutationResolver {

    @Autowired
    private SkillService skillService;

    public SkillDto addOrUpdateSkill(SkillDto skillDto) {
        return skillService.addOrUpdateSkill(skillDto);
    }

    public Status deleteSkill(String skillId) {
        skillService.deleteSkill(skillId);
        return Status.SUCCESS;
    }
}
