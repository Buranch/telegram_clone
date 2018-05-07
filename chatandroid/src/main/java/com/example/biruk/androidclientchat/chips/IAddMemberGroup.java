package com.example.biruk.androidclientchat.chips;

import com.example.biruk.androidclientchat.model.SearchItem;

import java.util.List;

public interface IAddMemberGroup {

    interface View {
        void renderSearchList(List<SearchItem> searchItems);
    }

    interface Presenter {
        void searchQuery(String query);
    }
}
