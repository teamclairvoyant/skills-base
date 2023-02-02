package com.clairvoyant.clarise.graphql.resolver.query;

import com.clairvoyant.clarise.dto.SkillDto;
import com.clairvoyant.clarise.service.SkillService;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SkillQueryResolver implements GraphQLQueryResolver {

    @Autowired
    private SkillService skillService;

    public List<SkillDto> getAllSkills() {
        return skillService.findAll();
    }

    public SkillDto findSkillById( String skillId) {
        return skillService.findSkill(skillId);
    }
}
