package com.example.biruk.androidclientchat.ProviderData.RemoteSource;

import android.support.annotation.NonNull;

import com.example.biruk.androidclientchat.ProviderData.model.Dialog;
import com.example.biruk.androidclientchat.ProviderData.model.User;

import java.util.List;


/**
 * Created by Biruk on 3/27/2018.
 */

public interface IDataService extends IDataContract {

    void getNameList(@NonNull LoadDataCallBack<User> callback);

    void getDialogList(@NonNull LoadDataCallBack<List<Dialog>> callBack);

}
