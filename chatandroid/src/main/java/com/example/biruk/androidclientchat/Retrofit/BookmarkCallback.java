package com.example.biruk.androidclientchat.Retrofit;

import com.example.biruk.androidclientchat.model.Dialog;

import java.util.ArrayList;

/**
 * Created by Biruk on 3/14/2018.
 */

public interface BookmarkCallback {

    ArrayList<Dialog> onSuccess(ArrayList<Dialog> a);
    void onError();
}
