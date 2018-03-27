package com.example.biruk.androidclientchat.MVCmain;

import com.example.biruk.androidclientchat.ProviderData.model.User;

import java.util.List;

/**
 * Created by Biruk on 3/27/2018.
 */

public interface SampleInterfaces {

    interface View {
        void showText(String a);
        void showNoText();
        void showUserList(List<User> userList);
    }

    interface Presenter {

        void loadUserName();

    }

    interface Data {

        String getStringText();
    }
}
