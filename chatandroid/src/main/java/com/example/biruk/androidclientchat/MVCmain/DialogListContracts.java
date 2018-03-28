package com.example.biruk.androidclientchat.MVCmain;

import com.example.biruk.androidclientchat.ProviderData.model.Dialog;
import com.example.biruk.androidclientchat.ProviderData.model.User;

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
    }

}
