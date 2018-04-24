package com.spring.aurora.service;

import com.spring.aurora.model.Container;

import java.sql.Date;
import java.util.List;

public interface ContainerService {
    Container insert(Container container);
    Container update(Container container);
    List<Container> findAllByCustomerId(String customerId);
    List<Container> findAll();
    List<Container> findContainerActivityByDate(Date date);
}
