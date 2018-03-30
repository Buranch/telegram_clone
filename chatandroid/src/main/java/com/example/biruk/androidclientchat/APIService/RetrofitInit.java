package com.example.biruk.androidclientchat.APIService;

import com.example.biruk.androidclientchat.model.StringConstants;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Biruk on 3/27/2018.
 */

public class RetrofitInit {
    private static Retrofit sRetrofit = null;

    public RetrofitInit() {
    }

    public static Retrofit getClient() {

        if (sRetrofit == null) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(StringConstants.URL_BASIC)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(client)
                    .build();

            return retrofit;
        } else {
            return sRetrofit;
        }
    }
}