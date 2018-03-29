package com.example.biruk.androidclientchat.ProviderData.RemoteSource;

import android.support.annotation.NonNull;

import com.example.biruk.androidclientchat.ProviderData.model.Dialog;
import com.example.biruk.androidclientchat.ProviderData.model.User;

import java.util.List;

import io.reactivex.Observable;


/**
 * Created by Biruk on 3/27/2018.
 */

public interface IDataService{
    Observable<List<Dialog>> getDialogList();

}
