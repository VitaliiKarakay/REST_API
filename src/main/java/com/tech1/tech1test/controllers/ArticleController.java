package com.tech1.tech1test.controllers;

import com.tech1.tech1test.domain.Article;
import com.tech1.tech1test.domain.Color;
import com.tech1.tech1test.repository.ArticleRepo;
import com.tech1.tech1test.services.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("articles")
public class ArticleController {

    private final ArticleService articleService;

    @Autowired
    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping
    public List<Article> getAll() {
        return articleService.getAll();
    }

    @GetMapping("{id}")
    public Article getById(@PathVariable("id") Long id) {
        return articleService.read(id);
    }

    @PostMapping
    public Article create(@RequestBody Article article) {
        articleService.create(article);
        return article;
    }

    @PutMapping("{id}")
    public Article update(@PathVariable("id") Article articleFromDb,
                          @RequestBody Article article
    ) {
        article = articleService.update(articleFromDb, article);
        return article;
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Article article) {
        articleService.delete(article);
    }
}
