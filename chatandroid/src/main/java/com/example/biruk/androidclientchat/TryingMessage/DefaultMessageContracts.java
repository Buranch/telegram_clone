package com.example.biruk.androidclientchat.TryingMessage;

import com.example.biruk.androidclientchat.model.Dialog;

import java.util.List;

/**
 * Created by Biruk on 3/27/2018.
 */

public interface DefaultMessageContracts {

    interface View {
        void renderDialogList(List<Dialog> dialogs);
    }

    interface Presenter {
        void getDialogs();
        void getUserInfo();
    }

}
