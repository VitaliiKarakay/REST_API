package com.tech1.tech1test.repository;

import com.tech1.tech1test.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepo extends JpaRepository<Article, Long> {


}
