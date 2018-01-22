package com.spring.aurora.service;

import com.spring.aurora.model.Container;

import java.util.List;

public interface ContainerService {
    Container insert(Container container);
    List<Container> findAllByCustomerId(String customerId);
}
