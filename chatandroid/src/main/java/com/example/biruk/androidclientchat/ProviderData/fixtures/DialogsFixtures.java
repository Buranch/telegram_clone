package com.example.biruk.androidclientchat.ProviderData.fixtures;

import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.biruk.androidclientchat.HomeActivity;
import com.example.biruk.androidclientchat.ProviderData.model.Dialog;
import com.example.biruk.androidclientchat.ProviderData.model.User;
import com.example.biruk.androidclientchat.ProviderData.model.Message;
import com.example.biruk.androidclientchat.R;
import com.example.biruk.androidclientchat.Retrofit.IAPIService;
import com.google.gson.Gson;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/*
 * Created by Anton Bevza on 07.09.16.
 */
public final class DialogsFixtures extends FixturesData {
    private DialogsFixtures() {
        throw new AssertionError();
    }

    public static ArrayList<Dialog> getDialogs() {

        ArrayList<Dialog> chats = new ArrayList<>();

        for (int i = 0; i < 20; i++) {
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DAY_OF_MONTH, -(i * i));
            calendar.add(Calendar.MINUTE, -(i * i));

            chats.add(getDialog(i, calendar.getTime()));
        }

        return chats;
    }


    public static void getConvDetail(String convID){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.22:9666/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        IAPIService service = retrofit.create(IAPIService.class);
        Call<Object> call = service.getConvDetail(convID);
        call.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                Log.d("getConv", ""+response);
                Gson gson = new Gson();
                try {
                    JSONObject jsonObject = new JSONObject(gson.toJson(response));
                    Log.d("Gson" , ""+jsonObject.getJSONObject("body").getJSONArray("participants"));
                    for (int i = 0; i < jsonObject.getJSONObject("body").getJSONArray("participants").length(); i++) {
                        Log.d("indexJSON", jsonObject.getJSONObject("body").getJSONArray("participants").getString(i));
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(Call<Object> call, Throwable t) {

            }
        });



    }

    public static ArrayList<Dialog> getConversations(String userDataID) {
        //accepts UserDateId
        ArrayList<Dialog> convs = new ArrayList<>();

        return convs;


    }

    private static Dialog getDialog(int i, Date lastMessageCreatedAt) {
        ArrayList<User> users = getUsers();
        return new Dialog(
                getRandomId(),
                users.size() > 1 ? groupChatTitles.get(users.size() - 2) : users.get(0).getName(),
//                new String(2){"skdljs", "skdljsfd"},
                users.size() > 1 ? avatars.get(users.size() - 2) : getRandomAvatar()
                ,null,
                "hello there",
//                getMessage(lastMessageCreatedAt),
                i < 3 ? 3 - i : 0);
    }

    private static ArrayList<User> getUsers() {
        ArrayList<User> users = new ArrayList<>();
        int usersCount = 1 + rnd.nextInt(4);

        for (int i = 0; i < usersCount; i++) {
            users.add(getUser());
        }

        return users;
    }

    private static User getUser() {
        return new User(
                getRandomId(),
                getRandomName(),
                getRandomAvatar(),
                getRandomBoolean());
    }

    private static Message getMessage(final Date date) {
        return new Message(
                getRandomId(),
                getUser(),
                getRandomMessage(),
                date);
    }
}
