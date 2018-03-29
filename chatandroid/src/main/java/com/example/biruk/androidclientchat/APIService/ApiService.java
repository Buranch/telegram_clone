package com.example.biruk.androidclientchat.ProviderData.RemoteSource;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.biruk.androidclientchat.HomeActivity;
import com.example.biruk.androidclientchat.ProviderData.RemoteSource.IDataService;
import com.example.biruk.androidclientchat.ProviderData.model.Dialog;
import com.example.biruk.androidclientchat.ProviderData.model.Message;
import com.example.biruk.androidclientchat.ProviderData.model.Token;
import com.example.biruk.androidclientchat.ProviderData.model.User;
import com.example.biruk.androidclientchat.ProviderData.model.UserNew;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

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

//
//    public void loginUser(final UserNew user, final Context context) {
//
//        Call<Token> call = service.loginUser(user);
//        call.enqueue(new Callback<Token>() {
//            @Override
//            public void onResponse(Call<Token> call, Response<Token> response) {
//                try {
//                    Log.d("RETROFIT", "reponse");
//                    Log.d("reeponse", ""+response.body().getToken());
//                    Log.d("mongodID", ""+response.body().getId());
//                    //save the token in shared preference
//                    String token = response.body().getToken();
//                    String userId = response.body().getId();
//                    SharedPreferences settings = context.getSharedPreferences(PREFS_NAME, 0);
//                    SharedPreferences.Editor editor = settings.edit();
//                    editor.putString(PREFS_TOKEN, token);
//                    editor.putString(PREFS_USERID, userId);
//                    // Commit the edits!
//                    editor.apply();
//                    Intent i = new Intent(context, HomeActivity.class);
//                    context.startActivity(i);
//                } catch (Exception e) {
//                    Log.d("onResponse", "There is an error");
//                    e.printStackTrace();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<Token> call, Throwable t) {
//                Log.d("onFailure", t.toString());
//
//            }
//
//        });
//    }