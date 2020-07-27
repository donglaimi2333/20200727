package com.example.demo.service;

import com.example.demo.service.model.FavoritesModel;

public interface FavoritesService {
    void create(FavoritesModel favoritesModel);

    void cancel(Integer document);

    FavoritesModel selectById(Integer id);
}
