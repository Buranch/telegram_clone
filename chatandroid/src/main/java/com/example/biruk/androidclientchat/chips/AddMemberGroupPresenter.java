package com.example.biruk.androidclientchat.chips;

import android.content.Context;
import android.support.annotation.NonNull;

import com.example.biruk.androidclientchat.APIService.IDataService;
import com.example.biruk.androidclientchat.model.SearchItem;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class AddMemberGroupPresenter implements IAddMemberGroup.Presenter {
    IAddMemberGroup.View view;
    IDataService dataService;
    Context mContext;

    private Boolean firstTime = true;

    AddMemberGroupPresenter(@NonNull IDataService dataService, IAddMemberGroup.View view, Context context) {
        this.dataService = dataService;
        this.view = view;
        this.mContext = context;

    }

    @Override
    public void searchQuery(String query) {
        Observable<List<SearchItem>> observable = dataService.getUserSearch(query);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<SearchItem>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<SearchItem> searchItems) {
                        view.renderSearchList(searchItems);
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
