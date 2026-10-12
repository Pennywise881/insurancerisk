package com.insurancerisk.riskplatform.application.controller;

import com.insurancerisk.riskplatform.application.dto.ApplicationResponse;
import com.insurancerisk.riskplatform.application.dto.CreateApplicationRequest;
import com.insurancerisk.riskplatform.application.dto.UpdateApplicationRequest;
//import com.insurancerisk.riskplatform.application.entity.InsuranceApplication;
import com.insurancerisk.riskplatform.application.service.ApplicationService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/applications")
public class ApplicationController {

    private final ApplicationService applicationService;

    public ApplicationController(ApplicationService applicationService)
    {
        this.applicationService = applicationService;
    }

    @GetMapping
    public List<ApplicationResponse> getApplication()
    {
        return applicationService.getApplications();
    }


    @PostMapping
    public ApplicationResponse createApplication(
            @Valid @RequestBody CreateApplicationRequest request) {

        return applicationService.createApplication(request);
    }


//    @PostMapping
//    public InsuranceApplication createApplication(@Valid @RequestBody InsuranceApplication application)
//    {
//        return applicationService.createApplication(application);
//    }

    @GetMapping("/{id}")
    public ResponseEntity<ApplicationResponse> getApplicationById(
            @PathVariable Long id) {

//        return applicationService.getApplicationById(id)
//                .map(ResponseEntity::ok)
//                .orElse(ResponseEntity.notFound().build());
        return ResponseEntity.ok(applicationService.getApplicationById(id));
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<ApplicationResponse> updateApplication(
//            @PathVariable Long id,
//            @Valid @RequestBody InsuranceApplication application) {
//
//        return applicationService.updateApplication(id, application)
//                .map(ResponseEntity::ok)
//                .orElse(ResponseEntity.notFound().build());
//    }

    @PutMapping("/{id}")
    public ResponseEntity<ApplicationResponse> updateApplication(
            @PathVariable Long id,
            @Valid @RequestBody UpdateApplicationRequest request) {

//        return applicationService.updateApplication(id, request)
//                .map(ResponseEntity::ok)
///                .orElse(ResponseEntity.notFound().build());
        return ResponseEntity.ok(
                applicationService.updateApplication(id, request)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteApplication(@PathVariable Long id) {
//        if (!applicationService.deleteApplication(id)) {
//            return ResponseEntity.notFound().build();
//        }
//
//        return ResponseEntity.noContent().build();
        applicationService.deleteApplication(id);
        return ResponseEntity.noContent().build();
    }
}
