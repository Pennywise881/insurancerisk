package com.insurancerisk.riskplatform.application.service;

import com.insurancerisk.riskplatform.application.dto.CreateApplicationRequest;

import com.insurancerisk.riskplatform.application.dto.ApplicationResponse;
import com.insurancerisk.riskplatform.application.dto.UpdateApplicationRequest;
import com.insurancerisk.riskplatform.application.entity.InsuranceApplication;
import com.insurancerisk.riskplatform.application.repository.InsuranceApplicationRepository;
import com.insurancerisk.riskplatform.common.exception.ResourceNotFoundException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class ApplicationService {

    private final InsuranceApplicationRepository applicationRepository;

    public ApplicationService(InsuranceApplicationRepository applicationRepository)
    {
        this.applicationRepository = applicationRepository;
    }

//    public List<InsuranceApplication> getApplications()
//    {
//        return applicationRepository.findAll();
//    }

    public List<ApplicationResponse> getApplications() {
        return applicationRepository.findAll(Sort.by(Sort.Direction.ASC, "id"))
                .stream()
                .map(this::toResponse)
                .toList();
    }

//    public InsuranceApplication createApplication(InsuranceApplication application)
//    {
//        return applicationRepository.save(application);
//    }

//    public InsuranceApplication createApplication(
//            CreateApplicationRequest request) {
//
//        InsuranceApplication application = new InsuranceApplication();
//
//        application.setApplicantName(request.getApplicantName());
//        application.setInsuranceType(request.getInsuranceType());
//        application.setCoverageAmount(request.getCoverageAmount());
//        application.setStatus("PENDING");
//
//        return applicationRepository.save(application);
//    }

    public ApplicationResponse createApplication(CreateApplicationRequest request) {
        InsuranceApplication application = new InsuranceApplication();

        application.setApplicantName(request.getApplicantName());
        application.setInsuranceType(request.getInsuranceType());
        application.setCoverageAmount(request.getCoverageAmount());
        application.setStatus("PENDING");

        InsuranceApplication savedApplication = applicationRepository.save(application);

        return toResponse(savedApplication);
    }

//    public Optional<InsuranceApplication> getApplicationById(Long id)
//    {
//        return applicationRepository.findById(id);
//    }

    public ApplicationResponse getApplicationById(Long id) {
//        return applicationRepository.findById(id)
//                .map(this::toResponse);

        InsuranceApplication application = applicationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException((
                        "Insurance application with ID "+ id + " was not found"
                )));

        return toResponse(application);
    }
//
//    public Optional<ApplicationResponse> updateApplication(
//            Long id, InsuranceApplication updatedApplication) {
//
//        return applicationRepository.findById(id).map(existingApplication -> {
//            existingApplication.setApplicantName(updatedApplication.getApplicantName());
//            existingApplication.setInsuranceType(updatedApplication.getInsuranceType());
//            existingApplication.setCoverageAmount(updatedApplication.getCoverageAmount());
//            existingApplication.setStatus(updatedApplication.getStatus());
//
//            InsuranceApplication savedApplication =
//                    applicationRepository.save(existingApplication);
//
//            return toResponse(savedApplication);
//        });
//    }

//    public Optional<InsuranceApplication> updateApplication(
//            Long id, InsuranceApplication updatedApplication) {
//
//        return applicationRepository.findById(id)
//                .map(existingApplication -> {
//                    existingApplication.setApplicantName(
//                            updatedApplication.getApplicantName());
//                    existingApplication.setInsuranceType(
//                            updatedApplication.getInsuranceType());
//                    existingApplication.setCoverageAmount(
//                            updatedApplication.getCoverageAmount());
//                    existingApplication.setStatus(
//                            updatedApplication.getStatus());
//
//                    return applicationRepository.save(existingApplication);
//                });
//    }

    public void deleteApplication(Long id) {
//        if (!applicationRepository.existsById(id)) {
//            return false;
//        }
//
//        applicationRepository.deleteById(id);
//        return true;
        if(!applicationRepository.existsById(id))
        {
            throw new ResourceNotFoundException(
                    "Insurance application with ID " + id + " was not found"
            );
        }

        applicationRepository.deleteById(id);
    }

    private ApplicationResponse toResponse(InsuranceApplication application) {
        return new ApplicationResponse(
                application.getId(),
                application.getApplicantName(),
                application.getInsuranceType(),
                application.getCoverageAmount(),
                application.getStatus()
        );
    }

    public ApplicationResponse updateApplication(
            Long id, UpdateApplicationRequest request) {

//        return applicationRepository.findById(id).map(existingApplication -> {
//            existingApplication.setApplicantName(request.getApplicantName());
//            existingApplication.setInsuranceType(request.getInsuranceType());
//            existingApplication.setCoverageAmount(request.getCoverageAmount());
//            existingApplication.setStatus(request.getStatus());
//
//            InsuranceApplication savedApplication =
//                    applicationRepository.save(existingApplication);
//
//            return toResponse(savedApplication);
//        });

        InsuranceApplication existingApplication = applicationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Insurance application with ID " + id + " was not found"
                ));

        existingApplication.setApplicantName(request.getApplicantName());
        existingApplication.setInsuranceType(request.getInsuranceType());
        existingApplication.setCoverageAmount(request.getCoverageAmount());
        existingApplication.setStatus(request.getStatus());

        InsuranceApplication savedApplication =
                applicationRepository.save(existingApplication);

        return toResponse(savedApplication);
    }

}
