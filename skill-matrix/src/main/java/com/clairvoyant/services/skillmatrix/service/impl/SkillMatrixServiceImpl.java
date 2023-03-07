package com.clairvoyant.services.skillmatrix.service.impl;

import com.clairvoyant.services.skillmatrix.dto.*;
import com.clairvoyant.services.skillmatrix.model.SkillAssessment;
import com.clairvoyant.services.skillmatrix.model.SkillCategory;
import com.clairvoyant.services.skillmatrix.repository.SkillAssessmentRepository;
import com.clairvoyant.services.skillmatrix.repository.SkillCategoryRepository;
import com.clairvoyant.services.skillmatrix.repository.UserRepository;
import com.clairvoyant.services.skillmatrix.service.SkillMatrixService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class SkillMatrixServiceImpl implements SkillMatrixService {

    public static final String VERY_LOW = "Very Low";
    public static final String BASIC = "Basic";
    public static final String COMPETENT = "Competent";
    public static final String DEVELOPED_SKILLS = "Developed Skills";
    public static final String HIGHLY_SKILLED = "Highly Skilled";

    @Autowired
    private SkillCategoryRepository skillCategoryRepository;

    @Autowired
    private SkillAssessmentRepository skillAssessmentRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public SkillMatrixResponse getSkills(String userId, List<String> categoryIds) {
        SkillMatrixResponse skillMatrixResponse = new SkillMatrixResponse();
        List<SkillMatrixList> skillMatrixLists = new ArrayList<>();
        for (String categoryId : categoryIds) {
            List<SkillCategory> skillCategoryList = skillCategoryRepository.findByCategoryIdAndIsActive(categoryId, true);
            List<SkillCategoryMatrixList> skillCategoryMatrixLists = new ArrayList<>();

            SkillMatrixList skillMatrixList = new SkillMatrixList();
            skillMatrixList.setCategoryId(skillCategoryList.get(0).getCategory().getId());
            skillMatrixList.setCategoryName(skillCategoryList.get(0).getCategory().getCatName());


            for (SkillCategory skillCategory : skillCategoryList) {
                SkillCategoryMatrixList skillCategoryMatrixList = new SkillCategoryMatrixList();

                skillCategoryMatrixList.setSkillCategoryId(skillCategory.getId());
                skillCategoryMatrixList.setSkillName(skillCategory.getSkill().getSkillName());

                List<SkillAssessment> assessmentList = skillAssessmentRepository.findBySkillCategoryId(skillCategory.getId());
                float averageRating = 0;
                for (SkillAssessment skillAssessment : assessmentList) {
                    if (userId.equals(skillAssessment.getAssessment().getUser().getId())) {
                        SelfAssessment selfAssessment = SelfAssessment.builder()
                            .skillLevel(getSkillsRating(skillAssessment.getSkillsRating().getName()))
                            .build();
                        skillCategoryMatrixList.setSelfAssessment(selfAssessment);

                        averageRating += getSkillsRating(skillAssessment.getSkillsRating().getName());
                    } else {
                        ManagerAssessment managerAssessment = ManagerAssessment.builder()
                            .skillLevel(getSkillsRating(skillAssessment.getSkillsRating().getName()))
                            .managerName(skillAssessment.getAssessment().getUser().getName())
                            .build();
                        skillCategoryMatrixList.setManagerAssessment(managerAssessment);

                        averageRating += getSkillsRating(skillAssessment.getSkillsRating().getName());

                    }
                }
                AveragedRatings averagedRatings = AveragedRatings.builder()
                    .skillLevel(averageRating / 2)
                    .build();
                skillCategoryMatrixList.setAveragedRatings(averagedRatings);
                skillCategoryMatrixLists.add(skillCategoryMatrixList);
            }
            skillMatrixList.setSkillCategoryMatrixList(skillCategoryMatrixLists);
            skillMatrixLists.add(skillMatrixList);

        }
        skillMatrixResponse.setSkillMatrixLists(skillMatrixLists);

        return skillMatrixResponse;
    }


    public int getSkillsRating(String skillRatingName) {
        int skillRating = 0;
        switch (skillRatingName) {
            case VERY_LOW:
                skillRating = 1;
                break;
            case BASIC:
                skillRating = 2;
                break;
            case COMPETENT:
                skillRating = 3;
                break;
            case DEVELOPED_SKILLS:
                skillRating = 4;
                break;
            case HIGHLY_SKILLED:
                skillRating = 5;
                break;
        }

        return skillRating;
    }
}
