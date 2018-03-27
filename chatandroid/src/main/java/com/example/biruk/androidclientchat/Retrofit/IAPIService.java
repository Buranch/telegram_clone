package com.example.biruk.androidclientchat.Retrofit;

import com.example.biruk.androidclientchat.ProviderData.model.Token;
import com.example.biruk.androidclientchat.ProviderData.model.TokenO;
import com.example.biruk.androidclientchat.ProviderData.model.UserData;
import com.example.biruk.androidclientchat.ProviderData.model.UserNew;

import org.json.JSONObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;


/**
 * Created by Biruk on 3/10/2018.
 */

public interface IAPIService {
    @POST("login/token")
    Call<Token> loginUser(@Body UserNew user);

    @POST("sample")
    Call<Token> testJson(@Body TokenO a);

    @POST("login/userinfo")
    Call<UserData> getUserData(@Body JSONObject a);

    //accepts convID as a paramter
    @GET("api/convId")
    Call<Object> getConvDetail(
            @Query("id") String id
            );
    //accepts userDataID as a param return list of conv
    @GET("api/convlist")
    Call<List> getConvList(
            @Query("id") String id
    );

}
