package com.clairvoyant.services.skillmatrix.graphql.resolver.query;

import com.clairvoyant.services.skillmatrix.model.Skill;
import com.clairvoyant.services.skillmatrix.service.SkillService;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SkillQueryResolver implements GraphQLQueryResolver {
    @Autowired
    private SkillService skillService;

    public List<Skill> getSkills() {
        return skillService.findAll();
    }

    public Skill findSkillById( String skillId) {
        return skillService.findSkill(skillId);
    }
}
