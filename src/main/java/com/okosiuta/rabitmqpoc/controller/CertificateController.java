package com.okosiuta.rabitmqpoc.controller;

import com.okosiuta.rabitmqpoc.data.model.Certificate;
import com.okosiuta.rabitmqpoc.service.CertificateService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/certificate")
public class CertificateController {

    private final CertificateService certificateService;

    @PostMapping
    public Certificate create() {
        return certificateService.create();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        certificateService.delete(id);
    }
}
