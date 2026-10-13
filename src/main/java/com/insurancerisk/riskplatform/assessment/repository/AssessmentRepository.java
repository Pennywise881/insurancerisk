package com.insurancerisk.riskplatform.assessment.repository;

import com.insurancerisk.riskplatform.assessment.entity.InsuranceAssessment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssessmentRepository
    extends JpaRepository<InsuranceAssessment, Long>
{

}
