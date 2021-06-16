package com.spring.aurora.model;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArSummaryRepository extends CrudRepository<ArSummary, Long> {

    @Override
    List<ArSummary> findAll();
}
