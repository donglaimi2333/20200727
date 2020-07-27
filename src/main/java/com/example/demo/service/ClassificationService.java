package com.example.demo.service;

import com.example.demo.service.model.ClassificationModel;

import java.util.List;

public interface ClassificationService {
    List<ClassificationModel> findAll();

    ClassificationModel selectById(Integer classification);
}

