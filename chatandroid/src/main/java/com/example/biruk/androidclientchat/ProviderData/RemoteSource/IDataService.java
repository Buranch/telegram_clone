package com.example.biruk.androidclientchat.ProviderData.RemoteSource;

import android.support.annotation.NonNull;

import com.example.biruk.androidclientchat.ProviderData.model.User;


/**
 * Created by Biruk on 3/27/2018.
 */

public interface IDataService extends IDataContract {

    void getNameList(@NonNull LoadDataCallBack<User> callback);

}
