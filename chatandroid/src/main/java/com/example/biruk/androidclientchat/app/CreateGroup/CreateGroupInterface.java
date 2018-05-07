package com.example.biruk.androidclientchat.app.CreateGroup;

import com.example.biruk.androidclientchat.model.NewGroup;

public interface CreateGroupInterface {

    interface View{

    }
    interface Presenter{
        void createGroup(NewGroup group);
    }

}
