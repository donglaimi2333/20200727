package com.example.demo.dao;

import com.example.demo.common.Query;
import com.example.demo.dataobject.Document;

import java.util.List;

public interface DocumentDao {
    List<Document> selectDocument();

    List<Document> selectDocumentByCondition(Query query);

    Document selectByTitle(String title);

    Document selectById(Integer id);

    List<Document> selectMyFavorites();

    void create(Document document);

    List<Document> selectMyPublish();

    void update(Document document);
}
