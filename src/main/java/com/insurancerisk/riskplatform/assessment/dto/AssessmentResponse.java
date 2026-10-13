package com.insurancerisk.riskplatform.assessment.dto;

import com.insurancerisk.riskplatform.assessment.RiskCategory;

import java.time.Instant;
import java.time.LocalDateTime;

public class AssessmentResponse {
    private Long id;
    private Long applicationId;
    private Integer riskScore;
    private Enum<RiskCategory> riskCategory;
    private Instant assessedAt;

    public AssessmentResponse(
            Long id,
            Long applicationId,
            Integer riskScore,
            Enum<RiskCategory> riskCategory,
            Instant assessedAt) {
        this.id = id;
        this.applicationId = applicationId;
        this.riskScore = riskScore;
        this.riskCategory = riskCategory;
        this.assessedAt = assessedAt;
    }

    public Long getId() {
        return id;
    }

    public Long getApplicationId() {
        return applicationId;
    }

    public Integer getRiskScore() {
        return riskScore;
    }

    public Enum<RiskCategory> getRiskCategory() {
        return riskCategory;
    }

    public Instant getAssessedAt() {
        return assessedAt;
    }
}
