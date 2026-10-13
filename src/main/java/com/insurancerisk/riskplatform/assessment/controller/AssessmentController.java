package com.insurancerisk.riskplatform.assessment.controller;

import com.insurancerisk.riskplatform.assessment.dto.ApplicationAssessmentHistoryResponse;
import com.insurancerisk.riskplatform.assessment.dto.AssessmentResponse;
import com.insurancerisk.riskplatform.assessment.dto.CreateAssessmentRequest;
import com.insurancerisk.riskplatform.assessment.service.AssessmentService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/assessments")
public class AssessmentController {

    private final AssessmentService assessmentService;

    public AssessmentController(AssessmentService assessmentService) {
        this.assessmentService = assessmentService;
    }

    @GetMapping
    public List<AssessmentResponse> getAssessments()
    {
        return assessmentService.getAssessments();
    }

    @GetMapping("/{id}")
    public ApplicationAssessmentHistoryResponse getAssessmentsById(
            @PathVariable Long id
    )
    {
        return assessmentService.getAssessmentsByApplicationId(id);
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
