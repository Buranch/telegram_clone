package com.example.biruk.androidclientchat.APIService;

import com.example.biruk.androidclientchat.model.Dialog;
import com.example.biruk.androidclientchat.model.NewGroup;
import com.example.biruk.androidclientchat.model.SearchItem;
import com.example.biruk.androidclientchat.model.User;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by Biruk on 3/27/2018.
 */

public interface ApiEndPoint {

    @GET("dumd")
//    Call<List<User>> getParticipants();
    Observable<List<User>> getUsersReactivly();
    @GET("api/convList")
    Observable<List<Dialog>> getDialogList(
            @Query("id") String id
    );
    @GET("user")
    Observable<User> getUserInfo(
            );

    @GET("/api/searchPGC")
    Observable<List<SearchItem>> searchQuery(
            @Query("query") String query
    );

    @GET("/crud/group/create")
    Observable<String> createGroupb();

    @POST("/crud/group/create")
    Observable<String> createGroup(
            @Query("newGroup") NewGroup newGroup
            );

    @GET("/api/searchUser")
    Observable<List<SearchItem>> searchUser(
            @Query("query") String query
    );
}
