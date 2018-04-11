package com.example.biruk.androidclientchat.app.Dialog;

import com.example.biruk.androidclientchat.model.Dialog;
import com.example.biruk.androidclientchat.model.SearchItem;

import java.util.List;

/**
 * Created by Biruk on 3/27/2018.
 */

public interface DialogListContracts {

    interface View {
        void renderDialogList(List<Dialog> dialogs);
        void renderSearchList(List<SearchItem> searchItems);
    }

    interface Presenter {
        void getDialogs();
        void getUserInfo();
        void searchQuery(String query);
    }

}
