package com.example.demo.service.impl;

import com.example.demo.dao.ClassificationDao;
import com.example.demo.service.ClassificationService;
import com.example.demo.service.model.ClassificationModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassficationServiceImpl implements ClassificationService {
    @Autowired
    ClassificationDao classificationDao;
    @Override
    public List<ClassificationModel> findAll() {
        return classificationDao.findAll();
    }

    @Override
    public ClassificationModel selectById(Integer classification) {
        return classificationDao.selectById(classification);
    }
}
