package com.okosiuta.rabitmqpoc.service.impl;

import com.okosiuta.rabitmqpoc.data.model.ResourceUsage;
import com.okosiuta.rabitmqpoc.repository.ResourceUsageRepository;
import com.okosiuta.rabitmqpoc.service.ResourceUsageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ResourceUsageServiceImpl implements ResourceUsageService {

    private final ResourceUsageRepository resourceUsageRepository;

    @Override
    public List<ResourceUsage> save(List<ResourceUsage> resourceUsages) {
        return resourceUsageRepository.saveAll(resourceUsages);
    }

    @Override
    public List<ResourceUsage> findAll() {
        return resourceUsageRepository.findAll();
    }
}
