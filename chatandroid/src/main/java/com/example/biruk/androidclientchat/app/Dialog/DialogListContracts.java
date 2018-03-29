package com.example.biruk.androidclientchat.app.Dialog;

import com.example.biruk.androidclientchat.model.Dialog;

import java.util.List;

/**
 * Created by Biruk on 3/27/2018.
 */

public interface DialogListContracts {

    interface View {
        void renderDialogList(List<Dialog> dialogs);
    }

    interface Presenter {
        void getDialogs();
        void getUserInfo();
    }

}
