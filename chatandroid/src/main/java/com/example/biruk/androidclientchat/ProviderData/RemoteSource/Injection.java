package com.example.biruk.androidclientchat.ProviderData.RemoteSource;

/**
 * Created by Biruk on 3/27/2018.
 */


public class Injection {

    public static DataService provideDataService() {
        ApiEndPoint apiService = RetrofitInit.getClient().create(ApiEndPoint.class);
        return DataService.getInstance(apiService);
    }
}
