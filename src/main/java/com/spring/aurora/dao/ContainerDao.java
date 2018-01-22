package com.spring.aurora.dao;

import com.spring.aurora.model.Container;

import java.util.List;

public interface ContainerDao {
    Container insert(Container container);
    List<Container> findAllByCustomerId(String customerId);
}
