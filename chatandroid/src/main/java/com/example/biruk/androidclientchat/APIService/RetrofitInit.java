package com.example.biruk.androidclientchat.ProviderData.RemoteSource;

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

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://10.0.2.22:9666/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();

            return retrofit;
        } else {
            return sRetrofit;
        }
    }
}