package com.example.biruk.androidclientchat.ProviderData.RemoteSource;

import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.ArrayAdapter;

import com.example.biruk.androidclientchat.ProviderData.fixtures.DialogsFixtures;
import com.example.biruk.androidclientchat.ProviderData.model.Dialog;
import com.example.biruk.androidclientchat.ProviderData.model.Message;
import com.example.biruk.androidclientchat.ProviderData.model.User;

import java.lang.reflect.Array;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.concurrent.atomic.AtomicReferenceArray;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Biruk on 3/27/2018.
 */

public class DataService implements IDataService {
    private static DataService INSTANCE;
    private ApiEndPoint service;

    private DataService(ApiEndPoint endPoint) {
        this.service = endPoint;
    }

    public static DataService getInstance(ApiEndPoint service) {
        if (INSTANCE == null) {
            INSTANCE = new DataService(service);
        }
        return INSTANCE;
    }

    @Override
    public Observable<List<Dialog>> getDialogList() {
//        return service.getDialogList("5aa4f04bd46b9d1d24a1465f");
         return Observable.create(
                 new ObservableOnSubscribe<List<Dialog>>() {
                     @Override
                     public void subscribe(ObservableEmitter<List<Dialog>> e) throws Exception {
                         int i = 4;
                         Calendar calendar = Calendar.getInstance();
                         calendar.add(Calendar.DAY_OF_MONTH, -(i * i));
                         calendar.add(Calendar.MINUTE, -(i * i));

                         ArrayList<User> users =new ArrayList<User>();
                         users.add(new User("sdf", "dsfs", "sdf",false));
                         e.onNext(Arrays.asList(
                                 new Dialog("goo", "biruk", "pic",  users,  new Message("sf",new User("sdf", "dsfs", "sdf",false),
                                         "wow", calendar.getTime()), 3),
                                 new Dialog("goo", "jerry", "pic",  users,  new Message("sf",new User("sdf", "dsfs", "sdf",false),
                                         "143", calendar.getTime()), 2)
                                 ));
                         e.onComplete();
                     }
                 }
         );
    }

    @Override
    public Observable<List<User>> getUsersList() {

        return service.getUsersReactivly();
    }
}

//    Observable<Integer> observable = Observable.create(new ObservableOnSubscribe<Integer>() {
//     @Override
//      public void subscribe(ObservableEmitter<Integer> e) throws Exception {
//                    //Use onNext to emit each item in the stream//
// e.onNext(1);
// e.onNext(2);
// e.onNext(3);
// Once the Observable has emitted all items in the sequence, call onComplete//
//                                                               e.onComplete();
//                                                           }
//                                                       }
//    );
