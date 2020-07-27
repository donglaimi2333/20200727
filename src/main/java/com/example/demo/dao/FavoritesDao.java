package com.example.demo.dao;

import com.example.demo.service.model.FavoritesModel;

public interface FavoritesDao {
    void create(FavoritesModel favoritesModel);

    void cancel(Integer document);

    FavoritesModel selectById(Integer document);
}
