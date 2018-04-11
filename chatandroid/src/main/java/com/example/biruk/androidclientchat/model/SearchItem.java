package com.example.biruk.androidclientchat.model;

public class SearchItem implements ISearchItem{

    String _id;

    String name;

    String profPic;

    String type;


    public SearchItem(String id, String name, String profPic, String type) {
        this._id = id;
        this.name = name;
        this.profPic = profPic;
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
        return this.profPic;
    }

    @Override
    public String getName() {
        return this.name;
    }
}
