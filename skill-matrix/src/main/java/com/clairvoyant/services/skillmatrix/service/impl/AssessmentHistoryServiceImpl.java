package com.clairvoyant.services.skillmatrix.service.impl;

import com.clairvoyant.services.skillmatrix.dto.AssessmentHistory;
import com.clairvoyant.services.skillmatrix.dto.AssessmentHistoryResponse;
import com.clairvoyant.services.skillmatrix.model.SkillAssessment;
import com.clairvoyant.services.skillmatrix.repository.SkillAssessmentRepository;
import com.clairvoyant.services.skillmatrix.service.AssessmentHistoryService;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AssessmentHistoryServiceImpl implements AssessmentHistoryService {

    public static final String ASSESSMENT_TYPE_COMPLETED = "Completed";

    public static final String ASSESSMENT_STATUS_SELFASSESSMENT = "Self assessment";

    public static final String ASSESSMENT_STATUS_SUPERVISORASSESSMENT = "Supervisor assessment";

    @Autowired
    private SkillAssessmentRepository skillAssessmentRepository;

    @Override
    public AssessmentHistoryResponse getAssessmentHistory(String userId, String assessmentId) {
        List<SkillAssessment> skillAssessmentList = skillAssessmentRepository.findByAssessmentId(assessmentId);

        Set<String> categoryIds = new HashSet<>();
        Set<String> skillIds = new HashSet<>();

        for (SkillAssessment skillAssessment : skillAssessmentList) {
            if (!categoryIds.contains(skillAssessment.getSkillCategory().getCategory().getId())) {
                categoryIds.add(skillAssessment.getSkillCategory().getCategory().getId());
            }
            if (!skillIds.contains(skillAssessment.getSkillCategory().getSkill().getId())) {
                skillIds.add(skillAssessment.getSkillCategory().getSkill().getId());
            }
        }

        AssessmentHistoryResponse assessmentHistoryResponse = new AssessmentHistoryResponse();
        assessmentHistoryResponse.setAssessmentHistoryList(new ArrayList<>());

        boolean selfAssessmentFlag = false;
        boolean supervisorAssessmentFlag = false;
        for (SkillAssessment skillAssessment : skillAssessmentList) {
            if (selfAssessmentFlag && supervisorAssessmentFlag) {
                break;
            }

            AssessmentHistory assessmentHistory = new AssessmentHistory();
            if (!selfAssessmentFlag && userId.equals(skillAssessment.getAssessment().getUser().getId())) {
                assessmentHistory.setType(ASSESSMENT_STATUS_SELFASSESSMENT);
                assessmentHistory.setComments(skillAssessment.getAssessment().getComments());
                assessmentHistory.setCompletedBy(skillAssessment.getAssessment().getUser().getId());
                assessmentHistory.setCompletionDate(skillAssessment.getAssessment().getModifiedDate());
                assessmentHistory.setCategories(categoryIds.size());
                assessmentHistory.setSkills(skillIds.size());

                selfAssessmentFlag = true;
            } else if (!supervisorAssessmentFlag) {
                assessmentHistory.setType(ASSESSMENT_STATUS_SUPERVISORASSESSMENT);
                assessmentHistory.setComments(skillAssessment.getAssessment().getComments());
                assessmentHistory.setCompletedBy(skillAssessment.getAssessment().getUser().getId());
                assessmentHistory.setCompletionDate(skillAssessment.getAssessment().getModifiedDate());
                assessmentHistory.setCategories(categoryIds.size());
                assessmentHistory.setSkills(skillIds.size());

                supervisorAssessmentFlag = true;
            }
            assessmentHistoryResponse.getAssessmentHistoryList().add(assessmentHistory);
        }
        return assessmentHistoryResponse;
    }
}
