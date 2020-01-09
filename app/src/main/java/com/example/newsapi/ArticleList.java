package com.example.newsapi;

import com.google.gson.annotations.SerializedName;

import java.util.List;

//this class was important because I think this is where the articles themselves were stored. I think
//this is what you need if your json info is stored in an object

public class ArticleList {
    @SerializedName("articles")
    private List<Article> articles;

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }
}
