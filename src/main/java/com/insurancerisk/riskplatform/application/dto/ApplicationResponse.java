
package com.insurancerisk.riskplatform.application.dto;

import java.math.BigDecimal;

public class ApplicationResponse {

    private Long id;
    private String applicantName;
    private String insuranceType;
    private BigDecimal coverageAmount;
    private String status;

    public ApplicationResponse(
            Long id,
            String applicantName,
            String insuranceType,
            BigDecimal coverageAmount,
            String status) {
        this.id = id;
        this.applicantName = applicantName;
        this.insuranceType = insuranceType;
        this.coverageAmount = coverageAmount;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public String getApplicantName() {
        return applicantName;
    }

    public String getInsuranceType() {
        return insuranceType;
    }

    public BigDecimal getCoverageAmount() {
        return coverageAmount;
    }

    public String getStatus() {
        return status;
    }
}
