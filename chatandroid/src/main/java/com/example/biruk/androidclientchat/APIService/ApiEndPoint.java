package com.example.biruk.androidclientchat.APIService;

import com.example.biruk.androidclientchat.model.Dialog;
import com.example.biruk.androidclientchat.model.User;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Biruk on 3/27/2018.
 */

public interface ApiEndPoint {

    @GET("dumd")
//    Call<List<User>> getParticipants();
    Observable<List<User>> getUsersReactivly();
    @GET("dum")
    Observable<List<Dialog>> getDialogList();

}
