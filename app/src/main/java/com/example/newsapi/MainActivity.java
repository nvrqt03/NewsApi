package com.example.newsapi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private TextView newsResultView;
    public static final String apiKey = "1b75d30a24b2471e9d520f3a2f429be4";
    public static final String sources = "techcrunch";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        newsResultView = findViewById(R.id.news_view_result);

        //DTO START
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://newsapi.org/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        NewsApi newsApi = retrofit.create(NewsApi.class);

        Call<ArticleList> call = newsApi.getNews(sources, apiKey);

        call.enqueue(new Callback<ArticleList>() {
            @Override
            public void onResponse(Call<ArticleList> call, Response<ArticleList> response) {
                if (!response.isSuccessful()) {
                    newsResultView.setText("Code: " + response.code());
                    return;
                }
                ArticleList posts = response.body();
                List<Article> techCrunchArticles = posts.getArticles();
                //techCrunchArticles.size();

                for (Article techCrunchArticle : techCrunchArticles) {
                    String content = "";
                    content += "Author: " + techCrunchArticle.getAuthor() + "\n";
                    content += "Title: " + techCrunchArticle.getTitle() + "\n";
                    content += "Description: " + techCrunchArticle.getDescription() + "\n";
                    content += "URL: " + techCrunchArticle.getUrl() + "\n\n";

                    newsResultView.append(content);

                }
            }

            @Override
            public void onFailure(Call<ArticleList> call, Throwable t) {
                newsResultView.setText(t.getMessage());
                Log.i("error: ", t.toString());
            }
        });
    }

}
