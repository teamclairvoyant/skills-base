package com.clairvoyant.services.skillmatrix.graphql.resolver.query;

import com.clairvoyant.services.skillmatrix.dto.SkillDto;
import com.clairvoyant.services.skillmatrix.service.SkillService;
import graphql.kickstart.tools.GraphQLQueryResolver;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
