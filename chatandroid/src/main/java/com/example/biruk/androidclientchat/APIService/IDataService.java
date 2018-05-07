package com.example.biruk.androidclientchat.APIService;

import com.example.biruk.androidclientchat.model.Dialog;
import com.example.biruk.androidclientchat.model.NewGroup;
import com.example.biruk.androidclientchat.model.SearchItem;
import com.example.biruk.androidclientchat.model.User;

import java.util.List;

import io.reactivex.Observable;


/**
 * Created by Biruk on 3/27/2018.
 */

public interface IDataService{
    Observable<List<Dialog>> getDialogList();

    Observable<User> getUserInfo();
    Observable<List<SearchItem>> getSearchQuery(String query);
    Observable<String> createGroup(NewGroup newGroup);
    Observable<String> createGroupb();
    Observable<List<SearchItem>> getUserSearch(String query);
}
