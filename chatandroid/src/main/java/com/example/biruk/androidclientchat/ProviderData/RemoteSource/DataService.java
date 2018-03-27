package com.example.biruk.androidclientchat.ProviderData.RemoteSource;

import android.support.annotation.NonNull;
import android.util.Log;

import com.example.biruk.androidclientchat.ProviderData.fixtures.DialogsFixtures;
import com.example.biruk.androidclientchat.ProviderData.model.Dialog;
import com.example.biruk.androidclientchat.ProviderData.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Biruk on 3/27/2018.
 */

public class DataService implements IDataService {
    private static DataService INSTANCE;
    private ApiEndPoint service;

    private DataService(ApiEndPoint endPoint) {
        this.service = endPoint;
    }

    public static DataService getInstance(ApiEndPoint service) {
        if (INSTANCE == null) {
            INSTANCE = new DataService(service);
        }
        return INSTANCE;
    }
    @Override
    public void getNameList(@NonNull final LoadDataCallBack<User> callback) {

        Call<List<User>> usersList = service.getUsers();
        usersList.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                    callback.onDataLoaded(response.body());
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                callback.onDataNotAvailable();
            }
        });
    }

    @Override
    public void getDialogList(@NonNull final LoadDataCallBack<List<Dialog>> callBack) {

        Call<List<Dialog>> dialogList = service.getDialogList("5aa4f04bd46b9d1d24a1465f");

        dialogList.enqueue(new Callback<List<Dialog>>() {
            @Override
            public void onResponse(Call<List<Dialog>> call, Response<List<Dialog>> response) {
                callBack.onDialogLoaded(DialogsFixtures.getDialogs());
            }
            @Override
            public void onFailure(Call<List<Dialog>> call, Throwable t) {
                Log.d("Dialog", "NOTAVAIL");
                callBack.onDialogNotAvailable();
            }
        });
    }
}
