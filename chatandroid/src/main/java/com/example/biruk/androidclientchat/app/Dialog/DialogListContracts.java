package com.example.biruk.androidclientchat.app.app.Dialog;

import com.example.biruk.androidclientchat.ProviderData.model.Dialog;

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
