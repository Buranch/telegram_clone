package com.example.biruk.androidclientchat.MVCmain;

import android.support.annotation.NonNull;
import android.util.Log;

import com.example.biruk.androidclientchat.ProviderData.RemoteSource.IDataContract;
import com.example.biruk.androidclientchat.ProviderData.RemoteSource.IDataService;
import com.example.biruk.androidclientchat.ProviderData.model.User;

import java.util.List;

/**
 * Created by Biruk on 3/27/2018.
 */

public class Presenter implements SampleInterfaces.Presenter{
    SampleInterfaces.View view;
    IDataService dataService;


    public Presenter(@NonNull IDataService dataService, SampleInterfaces.View view) {
        this.dataService = dataService;
        this.view = view;
    }


    @Override
    public void loadUserName() {
//        view.showText(dataSource.getStringText());
        dataService.getNameList(new IDataContract.LoadDataCallBack<User>() {
            @Override
            public void onDataLoaded(List<User> user) {
                view.showUserList(user);
            }

            @Override
            public void onDataNotAvailable() {

                Log.d("DataNot", "Availiable");
//                view.showNoText();
            }
        });

    }
}
