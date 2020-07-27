package com.example.demo.service;

import com.example.demo.common.Query;
import com.example.demo.dataobject.Document;
import com.example.demo.service.model.DocumentModel;

import java.util.List;

public interface DocumentService {
    List<DocumentModel> selectDocument();

    List<DocumentModel> selectDocumentByCondition(Query query);

    DocumentModel selectById(Integer id);

    List<DocumentModel> selectMyFavorites();

    void create(Document document);

    List<DocumentModel> selectMyPublish();

    void update(Document document);
}
