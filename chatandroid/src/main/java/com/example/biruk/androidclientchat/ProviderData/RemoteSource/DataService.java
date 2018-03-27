package com.example.biruk.androidclientchat.ProviderData.RemoteSource;

import android.support.annotation.NonNull;

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
//
//            @Override
//            public void onResponse(Call<User> call, Response<User> response) {
//                    callback.onDataLoaded();
//                 //verify the list
//                    Log.d("onResponse", "wowww");
////                    callback.onDataLoaded(Arrays.asList(new User("w3", "hello there", "aav", false)
////                                    , new User("w33", "hellre", "aadv", false)
////                                    , new User("3fds", "New User", "staize", false)
////                            , new User("3fds", "New User 1", "staize", false)
////                            , new User("3fds", "New User 3", "staize", false)
//
//                    ));
//            }
//            @Override
//            public void onFailure(Call<User> call, Throwable t) {
//                callback.onDataNotAvailable();
//                Log.d("onF", "wowww");
//
//            }
        });
    }
}
