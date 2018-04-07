package com.example.biruk.androidclientchat.TryingMessage;

import android.util.Log;

import com.example.biruk.androidclientchat.APIService.IDataService;
import com.example.biruk.androidclientchat.model.Dialog;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by User on 4/7/2018.
 */

public class DefaultMessagePresenter implements DefaultMessageContracts.Presenter {
    DefaultMessageContracts.View view;
    IDataService dataService;

    DefaultMessagePresenter(@NonNull IDataService dataService,DefaultMessageContracts.View view){
        this.dataService = dataService;
        this.view = view;
    }

    @Override
    public void getDialogs() {
        Log.v("dialog","getDialogs is called");
//        Observable<List<Dialog>> observable = dataService.getDialogList();
        Observable<List<Dialog>> observable = dataService.getDialogList();
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        new Observer<List<Dialog>>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(List<Dialog> dialogs) {
                                Log.d("onNext", ""+dialogs.size());
                                view.renderDialogList(dialogs);
                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onComplete() {

                            }
                        }
                );
    }

    @Override
    public void getUserInfo() {

    }
}
