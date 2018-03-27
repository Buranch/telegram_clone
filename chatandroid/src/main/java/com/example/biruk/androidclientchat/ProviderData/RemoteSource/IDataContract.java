package com.example.biruk.androidclientchat.ProviderData.RemoteSource;

import com.example.biruk.androidclientchat.ProviderData.model.User;

import java.util.List;

/**
 * Created by Biruk on 3/27/2018.
 */

public interface IDataContract {

    interface LoadDataCallBack<T>{

        void onDataLoaded(List<User> user);
        void onDataNotAvailable();
    }
}
