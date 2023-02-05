package com.clairvoyant.services.skillmatrix.service.impl;

import com.clairvoyant.services.skillmatrix.dto.AssessmentCategoryList;
import com.clairvoyant.services.skillmatrix.dto.AssessmentDto;
import com.clairvoyant.services.skillmatrix.dto.CategoryList;
import com.clairvoyant.services.skillmatrix.dto.SkillCategoryList;
import com.clairvoyant.services.skillmatrix.dto.SkillCategoryResponse;
import com.clairvoyant.services.skillmatrix.dto.SkillCategorySelected;
import com.clairvoyant.services.skillmatrix.model.Assessment;
import com.clairvoyant.services.skillmatrix.model.AssessmentStatus;
import com.clairvoyant.services.skillmatrix.model.AssessmentType;
import com.clairvoyant.services.skillmatrix.model.SkillAssessment;
import com.clairvoyant.services.skillmatrix.model.SkillCategory;
import com.clairvoyant.services.skillmatrix.model.SkillsRating;
import com.clairvoyant.services.skillmatrix.model.User;
import com.clairvoyant.services.skillmatrix.repository.AssessmentRepository;
import com.clairvoyant.services.skillmatrix.repository.AssessmentStatusRepository;
import com.clairvoyant.services.skillmatrix.repository.AssessmentTypeRepository;
import com.clairvoyant.services.skillmatrix.repository.CategoryRepository;
import com.clairvoyant.services.skillmatrix.repository.SkillAssessmentRepository;
import com.clairvoyant.services.skillmatrix.repository.SkillCategoryRepository;
import com.clairvoyant.services.skillmatrix.repository.UserRepository;
import com.clairvoyant.services.skillmatrix.service.SkillAssessmentService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SkillAssessmentServiceImpl implements SkillAssessmentService {

    public static final String ASSESSMENT_TYPE_COMPLETED = "completed";

    public static final String ASSESSMENT_STATUS_SELFASSESSMENT = "Self Assessment";

    @Autowired
    private SkillAssessmentRepository skillAssessmentRepository;

    @Autowired
    private SkillCategoryRepository skillCategoryRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private AssessmentTypeRepository assessmentTypeRepository;

    @Autowired
    private AssessmentStatusRepository assessmentStatusRepository;

    @Autowired
    private AssessmentRepository assessmentRepository;

    @Autowired
    private UserRepository userRepository;

    /**
     * Get SkillAssessment details based on list of categoryIds.
     *
     * @param categoryIds list of categoryIds.
     * @return SkillCategoryResponse.
     */
    @Override
    public SkillCategoryResponse getSkillAssessmentDetails(List<String> categoryIds) {
        SkillCategoryResponse skillCategoryResponse = new SkillCategoryResponse();
        skillCategoryResponse.setCategoryList(new ArrayList<>());
        for (String categoryId : categoryIds) {
            List<SkillCategory> skillCategoryList = skillCategoryRepository.findByCategoryIdAndIsActive(categoryId, true);
            if (skillCategoryList == null) {
                return null;
            }

            CategoryList categoryList = new CategoryList();
            categoryList.setCategoryId(skillCategoryList.get(0).getCategory().getId());
            categoryList.setCategoryName(skillCategoryList.get(0).getCategory().getCatName());
            categoryList.setSkillCategoryList(new ArrayList<>());

            for (SkillCategory skillCategoryObject : skillCategoryList) {
                SkillCategoryList skillCategory = new SkillCategoryList();
                skillCategory.setSkillCategoryId(skillCategoryObject.getId());
                skillCategory.setSkillName(skillCategoryObject.getSkill().getSkillName());
                categoryList.getSkillCategoryList().add(skillCategory);

            }
            skillCategoryResponse.getCategoryList().add(categoryList);
        }

        return skillCategoryResponse;
    }

    /**
     * Save or Update the Assessment details in Assessment & SkillAssessment Tables.     *
     *
     * @param assessmentDto input assessmentDto object from UI.
     */
    @Override
    public List<SkillAssessment> saveSkillAssessmentDetails(AssessmentDto assessmentDto) {

        User user = userRepository.findById(assessmentDto.getUserId()).get();

        // If user not exists.
        if (Objects.isNull(user)) {
            user = new User();
            user.setId(assessmentDto.getUserId());
        }

        Assessment assessment = new Assessment();

        //For now creating AssessmentStatus with SelfAssessment
        AssessmentStatus newAssessmentStatus = new AssessmentStatus();
        newAssessmentStatus.setName(ASSESSMENT_STATUS_SELFASSESSMENT);
        AssessmentStatus assessmentStatus = assessmentStatusRepository.save(newAssessmentStatus);
        assessment.setAssessmentStatus(assessmentStatus);

        //For now creating AssessmentType with Completed
        AssessmentType newAssessmentType = new AssessmentType();
        newAssessmentType.setName(ASSESSMENT_TYPE_COMPLETED);
        AssessmentType assessmentType = assessmentTypeRepository.save(newAssessmentType);
        assessment.setAssessmentType(assessmentType);

        assessment.setUser(user);
        assessment.setComments(assessmentDto.getComments());

        Assessment assessmentResponse = assessmentRepository.save(assessment);
        List<SkillAssessment> skillAssessmentList = new ArrayList<>();
        List<SkillAssessment> savedSkillAssessment = new ArrayList<>();

        for (AssessmentCategoryList assessmentCategoryList : assessmentDto.getAssessmentCategoryList()) {
            for (SkillCategorySelected skllCategorySelected : assessmentCategoryList.getSkillCategorySelected()) {

                SkillAssessment skillAssessment = new SkillAssessment();
                skillAssessment.setId(UUID.randomUUID().toString());
                skillAssessment.setAssessment(assessmentResponse);

                SkillsRating skillsRating = new SkillsRating();
                skillsRating.setId(skllCategorySelected.getSelectedSkillRatingId());
                skillAssessment.setSkillsRating(skillsRating);

                SkillCategory skillCategory = new SkillCategory();
                skillCategory.setId(skllCategorySelected.getSkillCategoryId());
                skillAssessment.setSkillCategory(skillCategory);

                skillAssessmentList.add(skillAssessment);
            }
        }
        if (Objects.nonNull(skillAssessmentList) && skillAssessmentList.size() > 0) {
            savedSkillAssessment.addAll(skillAssessmentRepository.saveAll(skillAssessmentList));
        }

        Set<String> skillAssessmentIds = savedSkillAssessment.stream().map(skillAssessment -> skillAssessment.getId()).collect(Collectors.toSet());

        return skillAssessmentRepository.findAllById(skillAssessmentIds);
    }


    /**
     * Get saved skill assessment details based on assessmentId.
     *
     * @param assessmentId assessmentId for which we are trying to get SkillAssessmentdetails.
     * @return AssessmentDto with skill,category,skill assessment details.
     */
    @Override
    public AssessmentDto getSavedSkillAssessmentDetails(String assessmentId) {
        List<SkillAssessment> skillssessmentList = skillAssessmentRepository.findByAssessmentId(assessmentId);

        AssessmentDto assessmentDto = new AssessmentDto();
        assessmentDto.setUserId(skillssessmentList.get(0).getAssessment().getUser().getId());
        assessmentDto.setComments(skillssessmentList.get(0).getAssessment().getComments());

        //Storing categoryId with List of SkillCategorySelected in a map
        Map<String, List<SkillCategorySelected>> skillCategorySelectedMap = new HashMap<>();
        for (SkillAssessment skillAssessmentObject : skillssessmentList) {
            SkillCategorySelected skillCategorySelected = new SkillCategorySelected();
            skillCategorySelected.setSkillCategoryId(skillAssessmentObject.getSkillCategory().getId());
            skillCategorySelected.setSelectedSkillRatingId(skillAssessmentObject.getSkillsRating().getId());
            skillCategorySelected.setSkillName(skillAssessmentObject.getSkillCategory().getSkill().getSkillName());

            String categoryId = skillAssessmentObject.getSkillCategory().getCategory().getId();

            if (skillCategorySelectedMap.containsKey(categoryId)) {
                List<SkillCategorySelected> skillCategorySelectedList = skillCategorySelectedMap.get(categoryId);
                skillCategorySelectedList.add(skillCategorySelected);
                skillCategorySelectedMap.put(categoryId, skillCategorySelectedList);
            } else {
                List<SkillCategorySelected> skillCategorySelectedList = new ArrayList<>();
                skillCategorySelectedList.add(skillCategorySelected);
                skillCategorySelectedMap.put(categoryId, skillCategorySelectedList);
            }
        }

        // Trying to fetch category details from SkillAssessment and storing in Set inorder to avoid duplicate categories
        Set<AssessmentCategoryList> categoryListHashSet = new HashSet<>();
        for (SkillAssessment skillAssessment : skillssessmentList) {

            AssessmentCategoryList assessmentCategoryListObject = new AssessmentCategoryList();
            String categoryId = skillAssessment.getSkillCategory().getCategory().getId();
            assessmentCategoryListObject.setCategoryId(categoryId);
            assessmentCategoryListObject.setCategoryName(skillAssessment.getSkillCategory().getCategory().getCatName());

            categoryListHashSet.add(assessmentCategoryListObject);
        }

        // Trying to fetch List of skillCategorySelected from map based on categoryId and setting to AssessmentCategoryList.
        for (AssessmentCategoryList assessmentCategoryListObject : categoryListHashSet) {
            if (skillCategorySelectedMap.containsKey(assessmentCategoryListObject.getCategoryId())) {
                assessmentCategoryListObject.setSkillCategorySelected(skillCategorySelectedMap
                        .get(assessmentCategoryListObject.getCategoryId()));
            }
        }

        assessmentDto.setAssessmentCategoryList(new ArrayList<>(categoryListHashSet));

        return assessmentDto;
    }
}



































