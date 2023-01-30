package com.clairvoyant.clarise.graphql.resolver.mutation;

import com.clairvoyant.clarise.dto.SkillRatingDto;
import com.clairvoyant.clarise.model.SkillsRating;
import com.clairvoyant.clarise.service.SkillsRatingService;
import graphql.kickstart.tools.GraphQLMutationResolver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SkillRatingMutationResolver implements GraphQLMutationResolver {

    @Autowired
    private SkillsRatingService service;


    /**
     * addOrUpdateSkillRating is used to add or update the SkillRating
     */
    public SkillsRating addOrUpdateSkillRating(SkillRatingDto skillRatingDto){
        SkillsRating skillsRating = new SkillsRating();
        BeanUtils.copyProperties(skillRatingDto,skillsRating);
        return service.save(skillsRating);
    }


    /**
     * deleteSkillRating is used to delete the SkillRating
     */
    public String deleteSkillRating(String skillRatingId){
        SkillsRating rating = new SkillsRating();
        rating.setId(skillRatingId);
        service.delete(rating);
        return "Record Deleted";
    }
}
