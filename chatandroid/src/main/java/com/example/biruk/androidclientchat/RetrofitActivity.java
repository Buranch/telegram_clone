package com.example.biruk.androidclientchat;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.biruk.androidclientchat.Chat.SocketSingleton;
import com.example.biruk.androidclientchat.Retrofit.ApiServices;
import com.example.biruk.androidclientchat.model.TokenO;
import com.example.biruk.androidclientchat.model.UserNew;
import com.google.gson.Gson;


import org.json.JSONException;
import org.json.JSONObject;

import io.socket.client.Socket;
import io.socket.emitter.Emitter;


/**
 * Created by Biruk on 3/10/2018.
 */

public class RetrofitActivity extends AppCompatActivity{
    public static final String PREFS_NAME = "ChatApp";
    Button btn;
    Button btn2;
    Socket mSocket;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);
        SocketSingleton app = SocketSingleton.getInstance();
        mSocket = app.getSocket();

        btn = (Button) findViewById(R.id.button);
        btn2 = (Button) findViewById(R.id.button2);
        mSocket.on("authenticated", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                Log.d("authenticated", "yes");
            }
        });


        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSocket.connect();
                SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
                String token = settings.getString("token", "notMan");
                TextView textView = (TextView) findViewById(R.id.textView);
                textView.setText(token);
                Gson gson = new Gson();
//                mSocket.emit("withoutauth", gson.toJson(new TokenO("tokennnn")));
                try {
                    JSONObject obj = new JSONObject(gson.toJson(new TokenO(token)));
                    mSocket.emit("authenticate", obj);

                    mSocket.emit("wow", "this is authenticated");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserNew user = new UserNew("gark@gmail.com", "pass");
                ApiServices apiServices = new ApiServices();
//                apiServices.loginUser(user, RetrofitActivity.this);
//                SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
//                SharedPreferences.Editor editor = settings.edit();
//                editor.putString("token", token);
//                // Commit the edits!
//                editor.apply();
            }
        });
    }


}
