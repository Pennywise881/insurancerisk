package com.insurancerisk.riskplatform.assessment.entity;

import com.insurancerisk.riskplatform.application.entity.InsuranceApplication;
import com.insurancerisk.riskplatform.assessment.RiskCategory;
import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "assessments")
public class InsuranceAssessment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "application_id", nullable = false)
    private InsuranceApplication application;

    private Integer riskScore;

    @Enumerated(EnumType.STRING)
    private RiskCategory riskCategory;

    private Instant assessedAt;

    public InsuranceAssessment()
    {

    }

    public InsuranceAssessment(Long id, InsuranceApplication application, Integer riskScore, RiskCategory riskCategory, Instant assessedAt)
    {
        this.id = id;
        this.application = application;
        this.riskScore = riskScore;
        this.riskCategory = riskCategory;
        this.assessedAt = assessedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public InsuranceApplication getApplication() {
        return application;
    }

    public void setApplication(InsuranceApplication application) {
        this.application = application;
    }

    public Integer getRiskScore() {
        return riskScore;
    }

    public void setRiskScore(Integer riskScore) {
        this.riskScore = riskScore;
    }

    public RiskCategory getRiskCategory() {
        return riskCategory;
    }

    public void setRiskCategory(RiskCategory riskCategory) {
        this.riskCategory = riskCategory;
    }

    public Instant getAssessedAt() {
        return assessedAt;
    }

    public void setAssessedAt(Instant assessedAt) {
        this.assessedAt = assessedAt;
    }
}
