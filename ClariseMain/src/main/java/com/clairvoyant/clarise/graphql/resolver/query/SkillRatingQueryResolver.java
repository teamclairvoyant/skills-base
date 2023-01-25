package com.clairvoyant.clarise.graphql.resolver.query;

import com.clairvoyant.clarise.model.SkillsRating;
import com.clairvoyant.clarise.service.SkillsRatingService;
import graphql.kickstart.tools.GraphQLQueryResolver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Slf4j
@Component
public class SkillRatingQueryResolver implements GraphQLQueryResolver {

    @Autowired
    private SkillsRatingService service;

    /**
     * getAllSkillRatings is used to get all the SkillRatings
     */
    public List<SkillsRating> getAllSkillRatings(Optional<Boolean> isActive){
        return service.findAll(isActive);
    }

    /**
     * getSkillRatingById is used to get the SkillRating based on the passed skillRatingId
     */
    public SkillsRating getSkillRatingById(String skillRatingId,Optional<Boolean> isActive){
        return service.findById(skillRatingId,isActive);
    }
}
