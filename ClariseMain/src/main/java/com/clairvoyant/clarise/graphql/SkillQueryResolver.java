package com.clairvoyant.clarise.graphql;

import com.clairvoyant.clarise.model.Skill;
import com.clairvoyant.clarise.service.SkillService;
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
