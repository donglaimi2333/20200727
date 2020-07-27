package com.example.demo.controller;

import com.example.demo.service.DocumentService;
import com.example.demo.service.FavoritesService;
import com.example.demo.service.model.DocumentModel;
import com.example.demo.service.model.FavoritesModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Slf4j
@Controller
@RequestMapping("/favorites")
public class FavoritesController {
    @Autowired
    DocumentService documentService;
    @Autowired
    FavoritesService favoritesService;

    //收藏文件
    @RequestMapping("/create")
    public ModelAndView create(Model model, Integer document, RedirectAttributes attributes) {
        System.out.println("收藏" + document);
        DocumentModel documentModel = documentService.selectById(document);
        model.addAttribute("document", documentModel);
        FavoritesModel favoritesModel = new FavoritesModel();
        favoritesModel.setDocument(document);
        favoritesModel.setUser((long) 1);
        favoritesService.create(favoritesModel);

        //重定向传递参数
        ModelAndView modelAndView = new ModelAndView("redirect:/document/selectById");
        modelAndView.addObject("id", document);

        return modelAndView;
    }

    //取消收藏
    @RequestMapping("/cancel")
    public ModelAndView cancel(Model model, Integer document, RedirectAttributes attributes) throws Exception {
        System.out.println("取消收藏");
        DocumentModel documentModel = documentService.selectById(document);
        model.addAttribute("documentList", documentModel);
        favoritesService.cancel(document);

        //重定向传递参数
        ModelAndView modelAndView = new ModelAndView("redirect:/document/selectById");
        modelAndView.addObject("id", document);

        return modelAndView;
    }


}
