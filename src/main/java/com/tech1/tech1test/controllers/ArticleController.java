package com.tech1.tech1test.controllers;

import com.tech1.tech1test.domain.Article;
import com.tech1.tech1test.services.ArticleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("articles")
public class ArticleController {

    private final ArticleServiceImpl articleService;

    @Autowired
    public ArticleController(ArticleServiceImpl articleServiceImpl) {
        this.articleService = articleServiceImpl;
    }

    @GetMapping
    public ResponseEntity<List<Article>> getAll() {
        return new ResponseEntity<>(articleService.getAll(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Article> getById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(articleService.read(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Article> create(@RequestBody Article article) {
        articleService.create(article);
        return new ResponseEntity<>(article, HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<Article> update(@PathVariable("id") Article articleFromDb,
                                          @RequestBody Article article
    ) {
        article = articleService.update(articleFromDb, article);
        return new ResponseEntity<>(article, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Article article) {
        articleService.delete(article);
    }
}
