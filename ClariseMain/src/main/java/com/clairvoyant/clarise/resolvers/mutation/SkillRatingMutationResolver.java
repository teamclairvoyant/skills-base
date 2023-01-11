package com.clairvoyant.clarise.resolvers.mutation;

import com.clairvoyant.clarise.input.SkillRatingInput;
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

    public SkillsRating addOrUpdateSkillRating(SkillRatingInput skillRatingInput){
        SkillsRating skillsRating = new SkillsRating();
        BeanUtils.copyProperties(skillRatingInput,skillsRating);
        return service.save(skillsRating);
    }

    public String deleteSkillRating(String skillRatingId){
        SkillsRating rating = new SkillsRating();
        rating.setId(skillRatingId);
        service.delete(rating);
        return "Record Deleted";
    }
}
