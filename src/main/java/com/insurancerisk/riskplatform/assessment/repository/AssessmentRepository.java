package com.insurancerisk.riskplatform.assessment.repository;

import com.insurancerisk.riskplatform.assessment.entity.InsuranceAssessment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssessmentRepository
    extends JpaRepository<InsuranceAssessment, Long>
{
    List<InsuranceAssessment> findByApplication_Id(Long applicationId);
}
