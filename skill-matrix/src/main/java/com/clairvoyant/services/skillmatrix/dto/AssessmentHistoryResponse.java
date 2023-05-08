package com.clairvoyant.services.skillmatrix.dto;

import java.time.ZonedDateTime;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AssessmentHistoryResponse {
    private List<AssessmentHistory> assessmentHistoryList;
}
