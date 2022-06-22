package com.tech1.tech1test.controllers;

import com.tech1.tech1test.domain.Article;
import com.tech1.tech1test.domain.Color;
import com.tech1.tech1test.repository.ArticleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("articles")
public class ArticleController {

    private final ArticleRepo articleRepo;

    @Autowired
    public ArticleController(ArticleRepo articleRepo) {
        this.articleRepo = articleRepo;
    }

    @GetMapping
    public List<Article> getAll(){
        return articleRepo.findAll();
    }

    @GetMapping("{id}")
    public Article getById(@PathVariable Article article) {
        return article;
    }

    @GetMapping("color/{color}")
    public List<Article> getByColor(@PathVariable Color color) {
        return null;
    }
}
