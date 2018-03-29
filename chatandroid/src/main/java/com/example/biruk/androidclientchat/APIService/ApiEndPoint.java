package com.example.biruk.androidclientchat.ProviderData.RemoteSource;

import com.example.biruk.androidclientchat.ProviderData.model.Dialog;
import com.example.biruk.androidclientchat.ProviderData.model.User;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Biruk on 3/27/2018.
 */

public interface ApiEndPoint {

    @GET("dum")
//    Call<List<User>> getUsers();
    Observable<List<User>> getUsersReactivly();
    @GET("/api/convlist")
    Observable<List<Dialog>> getDialogList(@Query("id") String id);

}
