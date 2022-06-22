package com.tech1.tech1test.services;

import com.tech1.tech1test.domain.Article;
import com.tech1.tech1test.domain.User;
import com.tech1.tech1test.repository.ArticleRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArticleService {

    private final ArticleRepo articleRepo;

    public ArticleService(ArticleRepo articleRepo) {
        this.articleRepo = articleRepo;
    }

    public List<Article> getAll() {
        return articleRepo.findAll();
    }

    public Article read(Long id) {
        Optional<Article> optionalArticle = articleRepo.findById(id);
        Article article = new Article(0L, "Article not exist", null);
        if (optionalArticle.isPresent()) {
            article = optionalArticle.get();
        }
        return article;
    }

    public void create(Article article) {
        articleRepo.save(article);
    }

    public Article update(Article articleFromDb, Article article) {
        articleFromDb.setText(article.getText());
        articleFromDb.setColor(article.getColor());
        return articleRepo.save(articleFromDb);
    }

    public void delete(Article article) {
        articleRepo.delete(article);
    }
}
