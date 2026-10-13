package com.insurancerisk.riskplatform.assessment.service;

//import com.insurancerisk.riskplatform.application.dto.ApplicationResponse;
import com.insurancerisk.riskplatform.application.dto.ApplicationResponse;
import com.insurancerisk.riskplatform.application.entity.InsuranceApplication;
import com.insurancerisk.riskplatform.application.repository.InsuranceApplicationRepository;
import com.insurancerisk.riskplatform.assessment.RiskCategory;
import com.insurancerisk.riskplatform.assessment.dto.ApplicationAssessmentHistoryResponse;
import com.insurancerisk.riskplatform.assessment.dto.AssessmentResponse;
import com.insurancerisk.riskplatform.assessment.dto.CreateAssessmentRequest;
import com.insurancerisk.riskplatform.assessment.entity.InsuranceAssessment;
import com.insurancerisk.riskplatform.assessment.repository.AssessmentRepository;
import com.insurancerisk.riskplatform.common.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Service
public class AssessmentService {

    private final AssessmentRepository assessmentRepository;
    private final InsuranceApplicationRepository applicationRepository;

    public AssessmentService(AssessmentRepository assessmentRepository, InsuranceApplicationRepository applicationRepository)
    {
        this.assessmentRepository = assessmentRepository;
        this.applicationRepository = applicationRepository;
    }

    public AssessmentResponse createAssessment(CreateAssessmentRequest request)
    {
        InsuranceAssessment assessment = new InsuranceAssessment();

        Long id = request.getApplicationId();

        InsuranceApplication application = applicationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Insurance application with ID " + id + " was not found"
                ));

        Integer riskScore = 0;
        String insuranceType = application.getInsuranceType();
        BigDecimal coverageAmount = application.getCoverageAmount();
        RiskCategory riskCategory;

        if(insuranceType.equalsIgnoreCase("LIFE"))
            riskScore+=10;
        else if (insuranceType.equalsIgnoreCase("AUTO"))
            riskScore+=20;
        else if (insuranceType.equalsIgnoreCase("HOME"))
            riskScore+=15;
        else if (insuranceType.equalsIgnoreCase("HEALTH"))
            riskScore+=25;
        else
            riskScore+=30;

        if (coverageAmount.compareTo(new BigDecimal("10000")) <= 0)
            riskScore += 5;
        else if (coverageAmount.compareTo(new BigDecimal("50000")) <= 0)
            riskScore += 10;
        else if (coverageAmount.compareTo(new BigDecimal("100000")) <= 0)
            riskScore += 20;
        else
            riskScore += 30;

        if(riskScore <=25)
            riskCategory = RiskCategory.LOW;
        else if (riskScore <= 45)
            riskCategory = RiskCategory.MEDIUM;
        else
            riskCategory = RiskCategory.HIGH;


//        assessment.setId(1L);
        assessment.setApplication(application);
        assessment.setRiskScore(riskScore);
        assessment.setRiskCategory(riskCategory);
        assessment.setAssessedAt(Instant.now());

        assessment = assessmentRepository.save(assessment);
        application.setStatus("ASSESSED");
        application = applicationRepository.save(application);

        return toResponse(assessment);
    }

    public List<AssessmentResponse> getAssessments() {
        return assessmentRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public ApplicationAssessmentHistoryResponse getAssessmentsByApplicationId(Long id)
    {
        InsuranceApplication application = applicationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Insurance application with ID " + id + " was not found"
                ));

        ApplicationResponse applicationResponse = new ApplicationResponse(
                application.getId(),
                application.getApplicantName(),
                application.getInsuranceType(),
                application.getCoverageAmount(),
                application.getStatus());

        return new ApplicationAssessmentHistoryResponse(
                applicationResponse,
                assessmentRepository.findByApplication_Id(id)
                .stream()
                .map(this::toResponse)
                .toList()
        );

//        return assessmentRepository.findByApplication_Id(id)
//                .stream()
//                .map(this::toResponse)
//                .toList();
    }

    private AssessmentResponse toResponse(InsuranceAssessment assessment)
    {
//        System.out.println("In toResponse");
        return new AssessmentResponse(
                assessment.getId(),
                assessment.getApplication().getId(),
                assessment.getRiskScore(),
                assessment.getRiskCategory(),
                assessment.getAssessedAt()
        );
    }
}
