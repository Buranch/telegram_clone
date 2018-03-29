package com.example.biruk.androidclientchat.APIService;

import com.example.biruk.androidclientchat.model.Dialog;

import java.util.List;

import io.reactivex.Observable;


/**
 * Created by Biruk on 3/27/2018.
 */

public interface IDataService{
    Observable<Dialog> getDialogList();

}
