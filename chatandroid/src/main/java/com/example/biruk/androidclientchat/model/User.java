package com.example.biruk.androidclientchat.model;


import com.stfalcon.chatkit.commons.modeels.IUser;

import java.util.Date;

/*
 * Created by troy379 on 04.04.17.
 */
public class User implements IUser {

    private String id;
    private String name;
    private String avatar;
    private boolean online;

    public Date getTimeStamp() {
        return new Date(timeStamp);

    }

    private long timeStamp;
    public User(String id, String name, String avatar, boolean online, long timeStamp) {
        this.id = id;
        this.name = name;
        this.avatar = avatar;
        this.online = online;
        this.timeStamp = timeStamp;
    }

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

    public boolean isOnline() {
        return online;
    }
}
