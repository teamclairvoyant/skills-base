package com.clairvoyant.clarise.graphql.resolver.query;

import com.clairvoyant.clarise.model.SkillsRating;
import com.clairvoyant.clarise.service.SkillsRatingService;
import graphql.kickstart.tools.GraphQLQueryResolver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;

@Slf4j
@Component
public class SkillRatingQueryResolver implements GraphQLQueryResolver {

    @Autowired
    private SkillsRatingService service;

    public List<SkillsRating> getAllSkillRatings(){
        return service.findAll();
    }

    public SkillsRating getSkillRatingById(String skillRatingId){
        return service.findById(skillRatingId);
    }
}
