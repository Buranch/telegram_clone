package com.example.biruk.androidclientchat.APIService;

/**
 * Created by Biruk on 3/27/2018.
 */


public class Injection {

    public static ApiService provideDataService() {
        ApiEndPoint apiService = RetrofitInit.getClient().create(ApiEndPoint.class);
        return ApiService.getInstance(apiService);
    }
}
