package com.insurancerisk.riskplatform.assessment.dto;

import com.insurancerisk.riskplatform.application.dto.ApplicationResponse;
import java.util.List;


public class ApplicationAssessmentHistoryResponse {
    private ApplicationResponse application;
    private List<AssessmentResponse> assessments;


    public ApplicationAssessmentHistoryResponse(
            ApplicationResponse application,
            List<AssessmentResponse> assessments) {
        this.application = application;
        this.assessments = assessments;
    }

    public ApplicationResponse getApplication()
    {
        return this.application;
    }

    public List<AssessmentResponse> getAssessments()
    {
        return this.assessments;
    }
}
