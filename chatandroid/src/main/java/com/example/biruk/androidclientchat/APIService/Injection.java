package com.example.biruk.androidclientchat.APIService;

/**
 * Created by Biruk on 3/27/2018.
 */


public class Injection {

    public static ApiService provideDataService() {
        ApiEndPoint apiEndPoint = RetrofitInit.getClient().create(ApiEndPoint.class);
        return ApiService.getInstance(apiEndPoint);
    }
}
