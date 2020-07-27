package com.example.demo.service.impl;

import com.example.demo.dao.FavoritesDao;
import com.example.demo.service.FavoritesService;
import com.example.demo.service.model.FavoritesModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Slf4j
@Service
public class FavoritesServiceImpl implements FavoritesService {
    @Autowired
    FavoritesDao favoritesDao;

    //添加收藏
    @Override
    public void create(FavoritesModel favoritesModel) {
        favoritesDao.create(favoritesModel);
    }

    @Override
    public void cancel(Integer document) {
        favoritesDao.cancel(document);
    }

    @Override
    public FavoritesModel selectById(Integer document) {
        return favoritesDao.selectById(document);
    }
}
