package com.spring.aurora.service;

import com.spring.aurora.model.Container;
import com.spring.aurora.model.Customer;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("containerService")
public class ContainerServiceImpl implements ContainerService {

    @Override
    public Container insert(Container container) {
        return container;
    }

    @Override
    public List<Container> findAllByCustomerId(int customerId) {
        return null;
    }
}
