package com.example.demo.service.impl;

import com.example.demo.common.Query;
import com.example.demo.common.TimeUtil;
import com.example.demo.dao.DocumentDao;
import com.example.demo.dataobject.Document;
import com.example.demo.service.ClassificationService;
import com.example.demo.service.DocumentService;
import com.example.demo.service.model.ClassificationModel;
import com.example.demo.service.model.DocumentModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.beans.BeanUtils.copyProperties;

@Slf4j
@Service
public class DocumentServiceImpl implements DocumentService {
    @Autowired
    DocumentDao documentDao;
    @Autowired
    ClassificationService classificationService;


    @Override
    public List<DocumentModel> selectDocument() {
        //查询所有文件
        List<Document> documentsList = documentDao.selectDocument();

        return documentsList.stream().map(document -> convertFromDataObject(document)).collect(Collectors.toList());
    }

    @Override
    public List<DocumentModel> selectDocumentByCondition(Query query) {
        /*if (query.getTitle() != "") {
            query.setTitle(String.valueOf(new StringBuffer("%"+query.getTitle()+"%")));
        }*/
        log.debug("查找document");
        if (query.getTitle() != "") {
            Document document1 = documentDao.selectByTitle(query.getTitle());
        }

        List<Document> documents = documentDao.selectDocumentByCondition(query);
        System.out.println("搜到shawanyi了" + documents);
        //将分类名称存入documentModel
        return documents.stream().map(document -> convertFromDataObject(document)).collect(Collectors.toList());

    }

    @Override
    public DocumentModel selectById(Integer id) {
        Document document = documentDao.selectById(id);
        return convertFromDataObject(document);
    }

    @Override
    public List<DocumentModel> selectMyFavorites() {
        List<Document> documents = documentDao.selectMyFavorites();
        return documents.stream().map(document -> convertFromDataObject(document)).collect(Collectors.toList());
    }

    @Override
    public void create(Document document) {
        document.setCreateTime(TimeUtil.getNowTime());
        document.setModifyTime(TimeUtil.getNowTime());
        documentDao.create(document);
    }

    @Override
    public List<DocumentModel> selectMyPublish() {
        List<Document> documents=documentDao.selectMyPublish();
        return documents.stream().map(document -> convertFromDataObject(document)).collect(Collectors.toList());
    }

    @Override
    public void update(Document document) {
        documentDao.update(document);
    }


    /**
     * 将Document转成DocumentModel,调用将分类名存入document的方法
     * * @param document
     *
     * @return
     */
    private DocumentModel convertFromDataObject(Document document) {
        DocumentModel documentModel = new DocumentModel();
        //从Document类型传到DocumentModel
        copyProperties(document, documentModel);
        //查询所有的分类放进map
        Map<Integer, ClassificationModel> classificationModelMap = classificationService.findAll()
                .stream().collect(Collectors.toMap(ClassificationModel::getId, classificationModel -> classificationModel));
        setClassificationIntoDocument(documentModel, classificationModelMap.get(documentModel.getClassification()));

        return documentModel;
    }

    //将分类存入document中
    private void setClassificationIntoDocument(DocumentModel documentModel, ClassificationModel classificationModel) {
        if (classificationModel.getTitle() != null) {
            //将分类title存入documentModel
            documentModel.setClassificationTitle(classificationModel.getTitle());
        }
    }


}
