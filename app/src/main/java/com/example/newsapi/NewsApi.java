package com.example.newsapi;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NewsApi {
    @GET("top-headlines")
    Call<ArticleList> getNews(
            @Query("sources") String Sources,
            @Query("apiKey") String apiKey);
}
