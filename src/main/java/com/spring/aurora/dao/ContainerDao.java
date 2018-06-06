package com.spring.aurora.dao;

import com.spring.aurora.model.Container;

import java.sql.Date;
import java.util.List;

public interface ContainerDao {
    
	Container insert(Container container);
	Container update(Container container);
	Container delete(Container container);
    List<Container> findAllByCustomerId(String customerId);
    List<Container> findAll();
    List<Container> findContainerActivityByDate(Date date);
    
}
