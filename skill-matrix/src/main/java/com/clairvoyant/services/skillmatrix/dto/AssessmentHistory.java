package com.clairvoyant.services.skillmatrix.dto;

import java.time.ZonedDateTime;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AssessmentHistory {
    private String type;
    private String skillSet;
    private int categories;
    private int skills;
    private String comments;
    private String completedBy;
    private ZonedDateTime completionDate;
}
