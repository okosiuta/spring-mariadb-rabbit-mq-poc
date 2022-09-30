package com.okosiuta.rabitmqpoc.repository;

import com.okosiuta.rabitmqpoc.data.model.ResourceUsage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResourceUsageRepository extends JpaRepository<ResourceUsage, Long> {
}
