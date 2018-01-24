package com.spring.aurora.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.aurora.dao.ContainerDao;
import com.spring.aurora.model.Container;

@Service("containerService")
public class ContainerServiceImpl implements ContainerService {

	@Autowired
	private ContainerDao containerDao;

	public void setContainerDao(ContainerDao containerDao) {
		this.containerDao = containerDao;
	}
	    
    @Override
    public Container insert(Container container) {
        return containerDao.insert(container);
    }

    @Override
    public List<Container> findAllByCustomerId(String customerId) {
        return containerDao.findAllByCustomerId(customerId);
    }
}
