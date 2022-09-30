package com.okosiuta.rabitmqpoc.controller;

import com.okosiuta.rabitmqpoc.data.model.ResourceUsage;
import com.okosiuta.rabitmqpoc.service.ResourceUsageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/resource-usage")
public class ResourceUsageController {

    private final ResourceUsageService resourceUsageService;

    @GetMapping
    public List<ResourceUsage> getAll() {
        return resourceUsageService.findAll();
    }
}
