package com.example.biruk.androidclientchat.app.Dialog;

import android.support.annotation.NonNull;
import android.util.Log;

import com.example.biruk.androidclientchat.APIService.IDataService;
import com.example.biruk.androidclientchat.model.Dialog;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

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
//        Observable<List<Dialog>> observable = dataService.getDialogList();
        Observable<Dialog> observable = dataService.getDialogList();
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Dialog>() {


            @Override
            public void onSubscribe(Disposable d) {
                Log.d("subscired", "sub");
            }

            @Override
            public void onNext(Dialog dialog) {
                Log.d("subscired", "" + dialog.getName());

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
