package com.example.biruk.androidclientchat.model;

public class SearchItem implements ISearchItem{

    String _id;

    String name;

    String avatar;

    String type;


    public SearchItem(String id, String name, String avatar, String type) {
        this._id = id;
        this.name = name;
        this.avatar = avatar;
        this.type = type;
    }

    @Override
    public String getId() {
        return this._id;
    }

    @Override
    public String getConvType() {
        return this.type;
    }

    @Override
    public String getAvatar() {
        return this.avatar;
    }

    @Override
    public String getName() {
        return this.name;
    }
}
