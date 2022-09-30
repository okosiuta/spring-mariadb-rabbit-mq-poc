package com.okosiuta.rabitmqpoc.service;

import com.okosiuta.rabitmqpoc.data.model.ResourceUsage;

import java.util.List;

public interface ResourceUsageService {

    List<ResourceUsage> save(List<ResourceUsage> resourceUsages);

    List<ResourceUsage> findAll();
}
