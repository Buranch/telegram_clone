package com.example.biruk.androidclientchat.app.Dialog;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.biruk.androidclientchat.APIService.IDataService;
import com.example.biruk.androidclientchat.Notification.NotificationHandler;
import com.example.biruk.androidclientchat.model.Dialog;
import com.example.biruk.androidclientchat.model.SearchItem;
import com.example.biruk.androidclientchat.model.User;

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
    Context mContext;

    private Boolean firstTime = true;

    DialogListPresenter(@NonNull IDataService dataService, DialogListContracts.View view, Context context) {
        this.dataService = dataService;
        this.view = view;
        this.mContext = context;

    }


    @Override
    public void getDialogs() {
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
                                //Iterate through each dialog and send notification
                                //with the last msg


                                view.renderDialogList(dialogs);
                                if(firstTime){
                                    int count = 0;
                                    for(Dialog d: dialogs) {
                                        count+=d.getUnreadCount();
                                        Log.d("Dialog ", ""+d.getUnreadCount());
                                    }
                                    NotificationHandler notificationHandler = NotificationHandler.getInstance(mContext);
                                    notificationHandler.createSimpleNotification(mContext,
                                            "You have new messages",
                                            "There are "+count+" messages from "+dialogs.size()+ " chats");
                                    firstTime = false;
                                }


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
        Observable<User> observable = dataService.getUserInfo();
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                 .subscribe(new Observer<User>() {
                     @Override
                     public void onSubscribe(Disposable d) {

                     }

                     @Override
                     public void onNext(User user) {
                         Log.d("onNext", ""+user.getTimeStamp());
                     }

                     @Override
                     public void onError(Throwable e) {

                     }

                     @Override
                     public void onComplete() {

                     }
                 });


                }

    @Override
    public void searchQuery(String query) {
        Observable<List<SearchItem>> observable = dataService.getSearchQuery(query);
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
