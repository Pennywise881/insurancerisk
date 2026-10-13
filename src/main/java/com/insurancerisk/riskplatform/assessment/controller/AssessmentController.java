package com.insurancerisk.riskplatform.assessment.controller;

import com.insurancerisk.riskplatform.assessment.dto.AssessmentResponse;
import com.insurancerisk.riskplatform.assessment.dto.CreateAssessmentRequest;
import com.insurancerisk.riskplatform.assessment.service.AssessmentService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/assessment")
public class AssessmentController {

    private final AssessmentService assessmentService;

    public AssessmentController(AssessmentService assessmentService) {
        this.assessmentService = assessmentService;
    }


    @PostMapping
    public AssessmentResponse createAssessement(
            @Valid @RequestBody CreateAssessmentRequest createAssessmentRequest
    )
    {
//        System.out.println("in controller");
        return this.assessmentService.createAssessment(createAssessmentRequest);
    }
}
