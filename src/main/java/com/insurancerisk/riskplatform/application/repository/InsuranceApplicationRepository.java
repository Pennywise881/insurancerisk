package com.insurancerisk.riskplatform.application.repository;

import com.insurancerisk.riskplatform.application.entity.InsuranceApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InsuranceApplicationRepository
    extends JpaRepository<InsuranceApplication, Long>
{

}
