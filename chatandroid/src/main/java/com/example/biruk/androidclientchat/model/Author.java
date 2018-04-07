package com.example.biruk.androidclientchat.model;

import com.stfalcon.chatkit.commons.modeels.IUser;

/**
 * Created by User on 4/6/2018.
 */

public class Author implements IUser {

    String id;
    String name;
    String avatar;

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getAvatar() {
        return avatar;
    }
}
