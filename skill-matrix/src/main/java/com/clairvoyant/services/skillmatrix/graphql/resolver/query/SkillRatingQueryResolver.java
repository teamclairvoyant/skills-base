package com.clairvoyant.services.skillmatrix.graphql.resolver.query;

import com.clairvoyant.services.skillmatrix.model.SkillsRating;
import com.clairvoyant.services.skillmatrix.service.SkillsRatingService;
import graphql.kickstart.tools.GraphQLQueryResolver;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SkillRatingQueryResolver implements GraphQLQueryResolver {

    @Autowired
    private SkillsRatingService service;

    /**
     * getAllSkillRatings is used to get all the SkillRatings
     */
    public List<SkillsRating> getAllSkillRatings(Optional<Boolean> isActive) {
        return service.findAll(isActive);
    }

    /**
     * getSkillRatingById is used to get the SkillRating based on the passed skillRatingId
     */
    public SkillsRating getSkillRatingById(String skillRatingId, Optional<Boolean> isActive) {
        return service.findById(skillRatingId, isActive);
    }
}
