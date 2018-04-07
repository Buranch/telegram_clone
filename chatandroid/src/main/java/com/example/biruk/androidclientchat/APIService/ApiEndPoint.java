package com.example.biruk.androidclientchat.APIService;

import com.example.biruk.androidclientchat.model.Dialog;
import com.example.biruk.androidclientchat.model.Message;
import com.example.biruk.androidclientchat.model.StringConstants;
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
    @GET("api/convList")
    Observable<List<Dialog>> getDialogList(
            @Query("id") String id
    );

    @GET("api/privatemsglist")
    Observable<List<Message>> getPrivateMessageList(
            @Query("id") String id
    );

    @GET("api/groupmsglist")
    Observable<List<Message>> getGroupMessageList(
            @Query("id") String id
    );

    @GET("api/channelmsglist")
    Observable<List<Message>> getChannelMessageList(
            @Query("id") String id
    );

    @GET("user")
    Observable<User> getUserInfo();
}
