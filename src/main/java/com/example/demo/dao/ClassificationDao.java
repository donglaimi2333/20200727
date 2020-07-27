package com.example.demo.dao;

import com.example.demo.service.model.ClassificationModel;

import java.util.List;

public interface ClassificationDao {
    List<ClassificationModel> findAll();

    ClassificationModel selectById(Integer classification);
}
