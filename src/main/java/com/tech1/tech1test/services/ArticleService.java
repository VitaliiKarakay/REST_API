package com.tech1.tech1test.services;

import com.tech1.tech1test.domain.Article;

import java.util.List;

public interface ArticleService {
    List<Article> getAll();

    Article read(Long id);

    void create(Article article);

    Article update(Article articleFromDb, Article article);

    void delete(Article article);
}
