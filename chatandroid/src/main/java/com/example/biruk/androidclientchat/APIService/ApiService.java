package com.example.biruk.androidclientchat.APIService;

import android.util.Log;

import com.example.biruk.androidclientchat.model.Dialog;
import com.example.biruk.androidclientchat.model.Message;
import com.example.biruk.androidclientchat.model.User;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

/**
 * Created by Biruk on 3/27/2018.
 */

public class ApiService implements IDataService {

    private static ApiService INSTANCE;
    private ApiEndPoint service;

    private ApiService(ApiEndPoint endPoint) {
        this.service = endPoint;
    }

    public static ApiService getInstance(ApiEndPoint service) {
        if (INSTANCE == null) {
            INSTANCE = new ApiService(service);
        }
        return INSTANCE;
    }

    @Override
    public Observable<List<Dialog>> getDialogList() {
        Log.d("onGetDialog", "woww");
        Observable<List<Dialog>> listObservable = service.getDialogList("5abd3f1eb5caea286c5af5dd");
        return listObservable
               .flatMap(new Function<List<Dialog>, Observable<List<Dialog>>>() {
                   @Override
                   public Observable<List<Dialog>> apply(List<Dialog> dialogs) throws Exception {
                       return Observable.just(dialogs);
                   }
               });
    }

    @Override
    public Observable<List<Message>> getPrivateMessageList() {
        Log.d("onGetDialog", "woww");
        Observable<List<Message>> listObservable = service.getPrivateMessageList("5abd3c40cc5292f4d9d9fab1");
        return listObservable
                .flatMap(new Function<List<Message>, Observable<List<Message>>>() {
                    @Override
                    public Observable<List<Message>> apply(List<Message> messages) throws Exception {
                        return Observable.just(messages);
                    }
                });
    }

    @Override
    public Observable<User> getUserInfo() {
        return service.getUserInfo()
                .flatMap(new Function<User, Observable<User>>() {
                    @Override
                    public Observable<User> apply(User user) throws Exception {
                        Log.d("onFlatMap", ""+user.getAvatar());
                        return Observable.just(user);
                    }
                });
    }

}
