package com.example.biruk.androidclientchat.APIService;

import android.util.Log;

import com.example.biruk.androidclientchat.model.Dialog;
import com.example.biruk.androidclientchat.model.NewGroup;
import com.example.biruk.androidclientchat.model.SearchItem;
import com.example.biruk.androidclientchat.model.User;

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
    @Override
    public Observable<List<SearchItem>> getSearchQuery(String query) {
        return service.searchQuery(query)
                .flatMap(new Function<List<SearchItem>, Observable<List<SearchItem>>>() {
                    @Override
                    public Observable<List<SearchItem>> apply(List<SearchItem> searchItems) throws Exception {
                        Log.d("getSerachQuery", ""+searchItems.size());
                        return Observable.just(searchItems);

                    }
                });
        }

    @Override
    public Observable<String> createGroup(NewGroup newGroup) {
        Log.d("CREATEGROUP", "CALLED");
//        this.getSearchQuery("SE");
//        this.getDialogList();
         return service.createGroup(newGroup)
                 .flatMap(new Function<String, Observable<String>>() {
                     @Override
                     public Observable<String> apply(String s) throws Exception {
                         return Observable.just(s);
                     }
                 });
    }

    @Override
    public Observable<String> createGroupb() {
        return service.createGroupb()
                .flatMap(new Function<String, Observable<String>>() {
                    @Override
                    public Observable<String> apply(String s) throws Exception {
                        return Observable.just(s);
                    }
                });
    }

    @Override
    public Observable<List<SearchItem>> getUserSearch(String query) {
        return service.searchUser(query)
                .flatMap(new Function<List<SearchItem>, Observable<List<SearchItem>>>() {
                    @Override
                    public Observable<List<SearchItem>> apply(List<SearchItem> searchItems) throws Exception {
                        Log.d("getSerachQuery", ""+searchItems.size());
                        return Observable.just(searchItems);

                    }
                });
    }
}
