package com.example.biruk.androidclientchat.MVCmain;

import com.example.biruk.androidclientchat.ProviderData.model.Dialog;
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

        void renderDialogList(List<Dialog> dialogs);
    }

    interface Presenter {

        void getDialogs();
        void loadUserName();

    }

    interface Data {

        String getStringText();
    }
}
