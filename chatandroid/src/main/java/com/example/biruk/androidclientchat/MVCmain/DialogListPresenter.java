package com.example.biruk.androidclientchat.MVCmain;

import android.support.annotation.NonNull;

import com.example.biruk.androidclientchat.ProviderData.RemoteSource.IDataService;
import com.example.biruk.androidclientchat.ProviderData.model.Dialog;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by Biruk on 3/27/2018.
 */

public class DialogListPresenter implements DialogListContracts.Presenter{
    DialogListContracts.View view;
    IDataService dataService;


    DialogListPresenter(@NonNull IDataService dataService, DialogListContracts.View view) {
        this.dataService = dataService;
        this.view = view;
    }
    @Override
    public void getDialogs() {
        Observable<List<Dialog>> observable = dataService.getDialogList();
        observable.subscribe(new Observer<List<Dialog>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(List<Dialog> dialogs) {
                view.renderDialogList(dialogs);
            }

            @Override
            public void onError(Throwable e) {
            }
            @Override
            public void onComplete() {

            }
        });
    }

}
