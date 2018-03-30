package com.example.biruk.androidclientchat.APIService;

import android.util.Log;

import com.example.biruk.androidclientchat.model.Dialog;
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

        /*
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
                                 new Dialog("goo", "biruk", "pic", Arrays.asList("sdlkf", "sdfsdf"),
                                         new Message("sldf", new User("sdf", "name", "avater", false),
                                                 "last Msg",calendar.getTime()), 3, StringConstants.PRI_CONVTYPE),
                                 new Dialog("goo", "jerry", "pic", Arrays.asList("sdlkf", "sdfsdf"),
                                         new Message("sldf", new User("sdf", "name", "avater", false),
                                                 "tnx :)",calendar.getTime()), 3, StringConstants.PRI_CONVTYPE)
                                 ));
                         e.onComplete();
                     }
                 }
         );*/
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