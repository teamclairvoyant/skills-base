package com.clairvoyant.services.skillmatrix.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyBoolean;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.clairvoyant.services.skillmatrix.dto.AssessmentDto;
import com.clairvoyant.services.skillmatrix.dto.CategoryList;
import com.clairvoyant.services.skillmatrix.dto.SkillCategoryList;
import com.clairvoyant.services.skillmatrix.dto.SkillCategoryResponse;
import com.clairvoyant.services.skillmatrix.dto.SkillCategorySelected;
import com.clairvoyant.services.skillmatrix.model.Assessment;
import com.clairvoyant.services.skillmatrix.model.AssessmentStatus;
import com.clairvoyant.services.skillmatrix.model.AssessmentType;
import com.clairvoyant.services.skillmatrix.model.Category;
import com.clairvoyant.services.skillmatrix.model.Skill;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {SkillAssessmentServiceImpl.class})
@ExtendWith(SpringExtension.class)
class SkillAssessmentServiceImplTest {
    @MockBean
    private AssessmentRepository assessmentRepository;

    @MockBean
    private AssessmentStatusRepository assessmentStatusRepository;

    @MockBean
    private AssessmentTypeRepository assessmentTypeRepository;

    @MockBean
    private CategoryRepository categoryRepository;

    @MockBean
    private SkillAssessmentRepository skillAssessmentRepository;

    @Autowired
    private SkillAssessmentServiceImpl skillAssessmentServiceImpl;

    @MockBean
    private SkillCategoryRepository skillCategoryRepository;

    @MockBean
    private UserRepository userRepository;

    /**
     * Method under test: {@link SkillAssessmentServiceImpl#getSkillAssessmentDetails(List)}
     */
    @Test
    void testGetSkillAssessmentDetailsWithEmptyInputs() {
        ArrayList<String> stringList = new ArrayList<>();
        assertEquals(stringList, skillAssessmentServiceImpl.getSkillAssessmentDetails(stringList).getCategoryList());
    }

    /**
     * Method under test: {@link SkillAssessmentServiceImpl#getSavedSkillAssessmentDetails(String)}
     */
    @Test
    void testGetSkillAssessmentDetailsThrowsIndexOutOfBoundsException() {
        when(skillAssessmentRepository.findByAssessmentId((String) any())).thenReturn(new ArrayList<>());

        List<String> categoryIds = new ArrayList<>();
        categoryIds.add(UUID.randomUUID().toString());
        assertThrows(IndexOutOfBoundsException.class, () -> {
            skillAssessmentServiceImpl.getSkillAssessmentDetails(categoryIds);
        });
    }

    /**
     * Method under test: {@link SkillAssessmentServiceImpl#getSkillAssessmentDetails(List)}
     */
    @Test
    void testGetSkillAssessmentDetailsWithValidInputs() {
        List<String> categoryIds = new ArrayList<>();
        categoryIds.add(UUID.randomUUID().toString());

        SkillCategory skillCategory = new SkillCategory();
        skillCategory.setId("skillCategory_01");
        Category category = new Category();
        category.setId("category_01");
        category.setCatName("Cat Name");
        skillCategory.setCategory(category);

        Skill skill = new Skill();
        skill.setId("skill_01");
        skill.setSkillName("Skill Name");
        skillCategory.setSkill(skill);
        List<SkillCategory> skillCategoryList = new ArrayList<>();
        skillCategoryList.add(skillCategory);

        when(skillCategoryRepository.findByCategoryIdAndIsActive((String) any(), anyBoolean()))
            .thenReturn(skillCategoryList);

        SkillCategoryResponse skillCategoryResponse = skillAssessmentServiceImpl.getSkillAssessmentDetails(categoryIds);

        //Assertion checks
        assertNotNull(skillCategoryResponse);
        List<CategoryList> categoryLists = skillCategoryResponse.getCategoryList();
        assertEquals(1, categoryLists.size());
        CategoryList categoryList = categoryLists.get(0);
        assertEquals("category_01", categoryList.getCategoryId());
        assertEquals("Cat Name", categoryList.getCategoryName());
        SkillCategoryList result = categoryList.getSkillCategoryList().get(0);
        assertEquals("skillCategory_01", result.getSkillCategoryId());
        assertEquals("Skill Name", result.getSkillName());
    }

    /**
     * Method under test: {@link SkillAssessmentServiceImpl#saveSkillAssessmentDetails(AssessmentDto)}
     */
    @Test
    void testSaveSkillAssessmentDetails() {
        AssessmentStatus assessmentStatus = mock(AssessmentStatus.class);
        AssessmentType assessmentType = mock(AssessmentType.class);
        User user = mock(User.class);

        Assessment assessment = new Assessment();
        assessment.setId("assessmentId_01");
        assessment.setComments("Comments");
        assessment.setUser(user);
        assessment.setAssessmentStatus(assessmentStatus);
        assessment.setAssessmentType(assessmentType);

        when(assessmentRepository.save((Assessment) any())).thenReturn(assessment);
        when(assessmentStatusRepository.findByName((String) any())).thenReturn(assessmentStatus);
        when(assessmentTypeRepository.findByName((String) any())).thenReturn(assessmentType);
        when(userRepository.findById((String) any())).thenReturn(Optional.ofNullable(user));

        SkillAssessment skillAssessment = new SkillAssessment();
        skillAssessment.setId("skillAssessmentId_01");
        skillAssessment.setSkillsRating(mock(SkillsRating.class));
        skillAssessment.setAssessment(mock(Assessment.class));
        skillAssessment.setSkillCategory(mock(SkillCategory.class));
        List<SkillAssessment> skillAssessmentList = new ArrayList<>();
        skillAssessmentList.add(skillAssessment);
        when(skillAssessmentRepository.saveAll(any())).thenReturn(skillAssessmentList);
        when(skillAssessmentRepository.findAllById((Iterable<String>) any())).thenReturn(skillAssessmentList);

        AssessmentDto assessmentDto = new AssessmentDto();
        assessmentDto.setCategoryList(new ArrayList<>());
        CategoryList categoryList = new CategoryList();
        categoryList.setSkillCategorySelected(new ArrayList<>());
        SkillCategorySelected skillCategorySelected = new SkillCategorySelected();
        skillCategorySelected.setSkillName("Skill Name");
        skillCategorySelected.setSkillCategoryId("skillCategoryId_01");
        categoryList.getSkillCategorySelected().add(skillCategorySelected);
        assessmentDto.getCategoryList().add(categoryList);

        List<SkillAssessment> actualSkillAssessmentList = skillAssessmentServiceImpl.saveSkillAssessmentDetails(assessmentDto);

        //Assertion checks
        assertSame(skillAssessmentList, actualSkillAssessmentList);
        assertEquals("skillAssessmentId_01", skillAssessmentList.get(0).getId());
        verify(assessmentRepository).save((Assessment) any());
        verify(assessmentStatusRepository).findByName((String) any());
        verify(assessmentTypeRepository).findByName((String) any());
        verify(skillAssessmentRepository).findAllById((Iterable<String>) any());
        verify(skillAssessmentRepository).saveAll((Iterable<SkillAssessment>) any());
        verify(userRepository).findById((String) any());
    }

    /**
     * Method under test: {@link SkillAssessmentServiceImpl#getSavedSkillAssessmentDetails(String)}
     */
    @Test
    void testGetSavedSkillAssessmentDetailsThrowsIndexOutOfBoundsException() {
        when(skillAssessmentRepository.findByAssessmentId((String) any())).thenReturn(new ArrayList<>());
        assertThrows(IndexOutOfBoundsException.class, () -> {
            skillAssessmentServiceImpl.getSavedSkillAssessmentDetails("assessmentId_01");
        });
    }

    /**
     * Method under test: {@link SkillAssessmentServiceImpl#getSavedSkillAssessmentDetails(String)}
     */
    @Test
    void testGetSavedSkillAssessmentDetailsWithSingleCategory() {
        Assessment assessment = new Assessment();
        assessment.setAssessmentStatus(new AssessmentStatus());
        assessment.setAssessmentType(new AssessmentType());
        assessment.setComments("Comments");
        assessment.setId("assessmentId_01");
        assessment.setUser(new User());

        Category category = new Category();
        category.setActive(true);
        category.setCatName("Cat Name");
        category.setId("categoryId_01");

        Skill skill = new Skill();
        skill.setActive(true);
        skill.setId("skillId_01");
        skill.setSkillName("Skill Name");

        SkillCategory skillCategory = new SkillCategory();
        skillCategory.setActive(true);
        skillCategory.setCategory(category);
        skillCategory.setId("skillCategoryId_01");
        skillCategory.setSkill(skill);

        SkillsRating skillsRating = new SkillsRating();
        skillsRating.setId("skillsRatingId_01");
        skillsRating.setName("Name");

        SkillAssessment skillAssessment = new SkillAssessment();
        skillAssessment.setAssessment(assessment);
        skillAssessment.setId("skillAssessmentId_01");
        skillAssessment.setSkillCategory(skillCategory);
        skillAssessment.setSkillsRating(skillsRating);

        ArrayList<SkillAssessment> skillAssessmentList = new ArrayList<>();
        skillAssessmentList.add(skillAssessment);
        when(skillAssessmentRepository.findByAssessmentId((String) any())).thenReturn(skillAssessmentList);

        AssessmentDto actualSavedSkillAssessmentDetails = skillAssessmentServiceImpl.getSavedSkillAssessmentDetails("assessmentId_01");

        //Assertion checks
        List<CategoryList> categoryLists = actualSavedSkillAssessmentDetails.getCategoryList();
        assertEquals(1, categoryLists.size());
        assertEquals("Comments", actualSavedSkillAssessmentDetails.getComments());
        CategoryList categoryList = categoryLists.get(0);
        assertEquals("categoryId_01", categoryList.getCategoryId());
        List<SkillCategorySelected> skillCategorySelected = categoryList.getSkillCategorySelected();
        assertEquals(1, skillCategorySelected.size());
        assertEquals("Cat Name", categoryList.getCategoryName());
        SkillCategorySelected result = skillCategorySelected.get(0);
        assertEquals("skillsRatingId_01", result.getSelectedSkillRatingId());
        assertEquals("Skill Name", result.getSkillName());
        assertEquals("skillCategoryId_01", result.getSkillCategoryId());
        verify(skillAssessmentRepository).findByAssessmentId((String) any());
    }

    /**
     * Method under test: {@link SkillAssessmentServiceImpl#getSavedSkillAssessmentDetails(String)}
     */
    @Test
    void testGetSavedSkillAssessmentDetailsWithMultipleCategories() {
        Assessment assessment = new Assessment();
        assessment.setAssessmentStatus(new AssessmentStatus());
        assessment.setAssessmentType(new AssessmentType());
        assessment.setComments("Comments");
        assessment.setId("42");
        assessment.setUser(new User());

        Category category = new Category();
        category.setActive(true);
        category.setCatName("Cat Name");
        category.setId("42");

        Skill skill = new Skill();
        skill.setActive(true);
        skill.setId("42");
        skill.setSkillName("Skill Name");

        SkillCategory skillCategory = new SkillCategory();
        skillCategory.setCategory(category);
        skillCategory.setId("42");
        skillCategory.setSkill(skill);

        SkillsRating skillsRating = new SkillsRating();
        skillsRating.setId("42");
        skillsRating.setName("Name");

        SkillAssessment skillAssessment = new SkillAssessment();
        skillAssessment.setAssessment(assessment);
        skillAssessment.setId("42");
        skillAssessment.setSkillCategory(skillCategory);
        skillAssessment.setSkillsRating(skillsRating);

        Assessment assessment1 = new Assessment();
        assessment1.setAssessmentStatus(new AssessmentStatus());
        assessment1.setAssessmentType(new AssessmentType());
        assessment1.setComments("Comments");
        assessment1.setId("42");
        assessment1.setUser(new User());

        Category category1 = new Category();
        category1.setActive(true);
        category1.setCatName("Cat Name");
        category1.setId("42");

        Skill skill1 = new Skill();
        skill1.setActive(true);
        skill1.setId("42");
        skill1.setSkillName("Skill Name");

        SkillCategory skillCategory1 = new SkillCategory();
        skillCategory1.setActive(true);
        skillCategory1.setCategory(category1);
        skillCategory1.setId("42");
        skillCategory1.setSkill(skill1);

        SkillsRating skillsRating1 = new SkillsRating();
        skillsRating1.setActive(true);
        skillsRating1.setId("42");
        skillsRating1.setName("Name");

        SkillAssessment skillAssessment1 = new SkillAssessment();
        skillAssessment1.setAssessment(assessment1);
        skillAssessment1.setId("42");
        skillAssessment1.setSkillCategory(skillCategory1);
        skillAssessment1.setSkillsRating(skillsRating1);

        ArrayList<SkillAssessment> skillAssessmentList = new ArrayList<>();
        skillAssessmentList.add(skillAssessment1);
        skillAssessmentList.add(skillAssessment);
        when(skillAssessmentRepository.findByAssessmentId((String) any())).thenReturn(skillAssessmentList);
        AssessmentDto actualSavedSkillAssessmentDetails = skillAssessmentServiceImpl.getSavedSkillAssessmentDetails("42");
        List<CategoryList> categoryList = actualSavedSkillAssessmentDetails.getCategoryList();
        assertEquals(1, categoryList.size());
        assertEquals("Comments", actualSavedSkillAssessmentDetails.getComments());
        CategoryList getResult = categoryList.get(0);
        assertEquals("42", getResult.getCategoryId());
        List<SkillCategorySelected> skillCategorySelected = getResult.getSkillCategorySelected();
        assertEquals(2, skillCategorySelected.size());
        assertEquals("Cat Name", getResult.getCategoryName());
        SkillCategorySelected getResult1 = skillCategorySelected.get(0);
        assertEquals("Skill Name", getResult1.getSkillName());
        SkillCategorySelected getResult2 = skillCategorySelected.get(1);
        assertEquals("Skill Name", getResult2.getSkillName());
        assertEquals("42", getResult2.getSkillCategoryId());
        assertEquals("42", getResult2.getSelectedSkillRatingId());
        assertEquals("42", getResult1.getSkillCategoryId());
        assertEquals("42", getResult1.getSelectedSkillRatingId());
        verify(skillAssessmentRepository).findByAssessmentId((String) any());
    }
}
