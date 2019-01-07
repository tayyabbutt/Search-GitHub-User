package com.inowave.test.networking;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private ApiService apiService;
    private static RetrofitClient retrofitClient = null;

    public static RetrofitClient getInstance() {
        if (retrofitClient == null) {
            return retrofitClient = new RetrofitClient();
        } else
            return retrofitClient;

    }

    private RetrofitClient() {


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/users/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(ApiService.class);
    }

    public ApiService getApiService() {
        return apiService;
    }
}
