package com.example.biruk.androidclientchat.app.CreateGroup;

import android.content.Context;
import android.support.annotation.NonNull;

import com.example.biruk.androidclientchat.APIService.IDataService;
import com.example.biruk.androidclientchat.app.CreateGroup.CreateGroupInterface.Presenter;
import com.example.biruk.androidclientchat.model.NewGroup;

public class CreateGroupPresenter implements Presenter {


    CreateGroupInterface.View view;
    IDataService dataService;
    Context mContext;
    CreateGroupPresenter(@NonNull IDataService dataService, CreateGroupInterface.View view, Context context) {
        this.dataService = dataService;
        this.view = view;
        this.mContext = context;
    }
    @Override
    public void createGroup(NewGroup group) {

//        this.dataService.createGroup(group);
        this.dataService.createGroupb();
    }
}
