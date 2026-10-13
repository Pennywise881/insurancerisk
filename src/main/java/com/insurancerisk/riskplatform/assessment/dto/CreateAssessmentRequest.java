
package com.insurancerisk.riskplatform.assessment.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class CreateAssessmentRequest {

    @NotNull(message = "Application ID is required")
    @Positive(message = "Application ID must be positive")
    private Long applicationId;

    public Long getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(Long applicationId) {
        this.applicationId = applicationId;
    }
}
