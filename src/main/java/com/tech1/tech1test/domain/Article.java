package com.tech1.tech1test.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "article", schema = "testdb")
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String text;

    @Enumerated(EnumType.STRING)
    private Color color;

    public Article() {
    }

    public Article(Long id, String text, Color color) {
        this.id = id;
        this.text = text;
        this.color = color;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Article article = (Article) o;
        return id.equals(article.id) && Objects.equals(text, article.text) && color == article.color;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, text, color);
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", color=" + color +
                '}';
    }





}
