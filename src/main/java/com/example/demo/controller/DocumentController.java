package com.example.demo.controller;

import com.example.demo.common.Query;
import com.example.demo.dataobject.Document;
import com.example.demo.service.ClassificationService;
import com.example.demo.service.DocumentService;
import com.example.demo.service.FavoritesService;
import com.example.demo.service.model.ClassificationModel;
import com.example.demo.service.model.DocumentModel;
import com.example.demo.service.model.FavoritesModel;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Slf4j
@Controller
@RequestMapping("/document")
public class DocumentController {
    @Autowired
    DocumentService documentService;
    @Autowired
    ClassificationService classificationService;
    @Autowired
    FavoritesService favoritesService;

    /**
     * 查询所有文件
     *
     * @return
     */
    @RequestMapping("/search_all")
    public String searchAll(Model model) {
        List<DocumentModel> documentModelList = documentService.selectDocument();
        System.out.println("搜到啥了" + documentModelList);
        model.addAttribute("documentList", documentModelList);

        //传入所有分类
        List<ClassificationModel> classifications = classificationService.findAll();
        model.addAttribute("classificationList", classifications);
        return "allFile";
    }

    /**
     * 条件查询
     *
     * @return
     */
    @RequestMapping("/search_document")
    public String search_document(Query query, Model model) {
        System.out.println("传来的参数是啥" + query);
        if (query.getTitle() == null && query.getClassification() == null && query.getEndTime() == null && query.getStartTime() == null) {
            System.out.println("query是空");
            List<DocumentModel> documentModelList = documentService.selectDocument();
            model.addAttribute("documentList", documentModelList);
        } else {
            System.out.println("query不是空");
            List<DocumentModel> documentModels = documentService.selectDocumentByCondition(query);
            model.addAttribute("documentList", documentModels);
        }
        //所选分类对应的分类名
        if (query.getClassification() != null) {
            ClassificationModel classification = classificationService.selectById(query.getClassification());
            model.addAttribute("classification", classification);
        }
        //传入所有分类
        List<ClassificationModel> classifications = classificationService.findAll();
        model.addAttribute("classificationList", classifications);
        model.addAttribute("query", query);
        return "allFile";
    }

    @RequestMapping("/selectById")
    public String selectById(Integer id, Model model) {
        System.out.println("传过来的id:" + id);
        DocumentModel documentModel = documentService.selectById(id);
        model.addAttribute("document", documentModel);
        //判断文章是否被收藏
        FavoritesModel favorites = favoritesService.selectById(id);
        if (favorites != null) {
            System.out.println("已被收藏");
            boolean isFavorites = true;
            model.addAttribute("isFavorites", isFavorites);
        } else {
            System.out.println("未被收藏");
            boolean isFavorites = false;
            model.addAttribute("isFavorites", isFavorites);
        }
        return "details";
    }

    //我的收藏页面
    @RequestMapping("/myFavorites")
    public String selectMyFavorites(Model model) {
        List<DocumentModel> documentModelList = documentService.selectMyFavorites();
        model.addAttribute("documents", documentModelList);
        return "myFavorites";
    }

    //进入上传页面
    @RequestMapping("/intoUpload")
    public String intoUpload(Model model) {
        //传入所有分类
        List<ClassificationModel> classifications = classificationService.findAll();
        model.addAttribute("classificationList", classifications);
        return "uploadFile";
    }

    //上传
    @RequestMapping("/create")
    public String create(@RequestParam("classification") Integer classification,
                         @RequestParam("summary") String summary,
                         @RequestParam(value = "label", required = false) String label,
                         @RequestParam("file") MultipartFile file,
                         @RequestParam("supportDownload") Integer supportDownload,
                         @RequestParam("supportReference") Integer supportReference) throws IOException {

        //上传文件
        String filename = file.getOriginalFilename();
        String extension = FilenameUtils.getExtension(filename);
        String baseName = FilenameUtils.getBaseName(filename);
        UUID uuid = UUID.randomUUID();
        String filePath = "/data/deploy-web/tomcat-document-web/file/upload/" + baseName + "-" + uuid + "." + extension;


        Document document = new Document();
        document.setTitle(filename);
        document.setSummary(summary);
        document.setUser(Long.valueOf(1));
        document.setSupportDownload(supportDownload);
        document.setSupportReference(supportReference);
        document.setClassification(classification);
        document.setSourceLink(filePath);

        documentService.create(document);
        return "redirect:/document/myPublish";
    }

    //进入我的发表页面
    @RequestMapping("/myPublish")
    public String myPublish(Model model) {
        List<DocumentModel> documentModelList = documentService.selectMyPublish();
        model.addAttribute("documentList", documentModelList);
        return "myPublish";
    }

    //进入编辑页面
    @RequestMapping("/intoUpdate")
    public String update(@RequestParam("id") Integer id,
                        Model model) {
        //传入所有分类
        List<ClassificationModel> classifications = classificationService.findAll();
        model.addAttribute("classificationList", classifications);
        //查找文章
        DocumentModel documentModel = documentService.selectById(id);
        model.addAttribute("document", documentModel);
        return "edit";
    }

    //编辑发表
    @RequestMapping("/update")
    public String update(@RequestParam("id") Integer id,
                         @RequestParam("classification") Integer classification,
                         @RequestParam("summary") String summary,
                         @RequestParam(value = "label", required = false) String label,
                         @RequestParam("file") MultipartFile file,
                         @RequestParam("supportDownload") Integer supportDownload,
                         @RequestParam("supportReference") Integer supportReference, Model model) {
        Document document = new Document();
        document.setId(id);
        //判断上传文件是否改变
        if (StringUtils.isNotBlank(file.getOriginalFilename())) {
            System.out.println("上传文件改变");
            //上传文件
            String filename = file.getOriginalFilename();
            String extension = FilenameUtils.getExtension(filename);
            String baseName = FilenameUtils.getBaseName(filename);
            UUID uuid = UUID.randomUUID();
            String filePath = "/data/deploy-web/tomcat-document-web/file/upload/" + baseName + "-" + uuid + "." + extension;
            document.setTitle(filename);
            document.setSourceLink(filePath);
        }
        if (summary != null) {
            document.setSummary(summary);
        }
        document.setUser(Long.valueOf(1));
        if (supportDownload != null) {
            document.setSupportDownload(supportDownload);
        }
        if (supportReference != null) {
            document.setSupportReference(supportReference);
        }
        if (classification != null) {
            document.setClassification(classification);
        }
        System.out.println("要更改的信息"+document);
        documentService.update(document);
        return "redirect:/document/myPublish";
    }
}
