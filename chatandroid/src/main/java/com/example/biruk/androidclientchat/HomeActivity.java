package com.example.biruk.androidclientchat;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.biruk.androidclientchat.Chat.SocketSingleton;
import com.example.biruk.androidclientchat.ProviderData.fixtures.DialogsFixtures;
import com.example.biruk.androidclientchat.ProviderData.model.Dialog;
import com.example.biruk.androidclientchat.ProviderData.model.TokenO;
import com.example.biruk.androidclientchat.ProviderData.model.UserData;
import com.example.biruk.androidclientchat.Retrofit.ApiServices;
import com.example.biruk.androidclientchat.Retrofit.BookmarkCallback;
import com.example.biruk.androidclientchat.Retrofit.IAPIService;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import io.socket.client.Socket;
import io.socket.emitter.Emitter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.biruk.androidclientchat.Retrofit.ApiServices.PREFS_NAME;
import static com.example.biruk.androidclientchat.Retrofit.ApiServices.PREFS_USERDATA;
import static com.example.biruk.androidclientchat.Retrofit.ApiServices.PREFS_USERID;

public class HomeActivity extends AppCompatActivity {

    public static UserData userData;
    EditText editText;
    Button btn;
    TextView prevText;

    public static Socket mSocket;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //init socket
        SocketSingleton app = SocketSingleton.getInstance();
        mSocket = app.getSocket();

        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        String token = settings.getString(PREFS_USERDATA, "notMan");
        Log.d("USERDATA ", token);
        initSocket();
//        renderConv("5aa650b2f934e514447a9e40");


        //delbo
        btn = (Button) findViewById(R.id.Send);
        editText = (EditText) findViewById(R.id.editText);
        prevText = (TextView) findViewById(R.id.prevView);
        getUserData();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
                mSocket.emit("message", settings.getString(PREFS_USERDATA, "err"), "5aa650b2f934e514447a9e40", editText.getText().toString());

            }
        });
        mSocket.on("message", onMessageReceive);
    }

    public void initSocket(){
        mSocket.connect();
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        String token = settings.getString("token", "notMan");
//        TextView textView = (TextView) findViewById(R.id.textView);
//        textView.setText(token);
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

    public void getUserData() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.22:9666/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        IAPIService service = retrofit.create(IAPIService.class);
        JSONObject obj = new JSONObject();
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
       final Call<UserData> call = service.getUserData(obj);

        try {
            obj.put("id", settings.getString(PREFS_USERID, "null"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        call.enqueue(new Callback<UserData>() {
            @Override
            public void onResponse(Call<UserData> call, Response<UserData> response) {
                userData = response.body();
                SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
                SharedPreferences.Editor editor = settings.edit();
                editor.putString(PREFS_USERDATA, userData.get_id());
                editor.commit();
                editor.apply();
                Log.d("USERDATA_PREf", settings.getString(PREFS_USERDATA, "nooo"));
//                ApiServices apiServices = new ApiServices();
//
//               apiServices.getConvList(settings.getString(PREFS_USERDATA, "sss"), new BookmarkCallback() {
//                    @Override
//                    public ArrayList<Dialog> onSuccess(ArrayList<Dialog> a) {
//                        Log.d("onSuccess", ""+a.size());
//                        return a;
//                    }
//
//                    @Override
//                    public void onError() {
//
//                    }
//                });

            }

            @Override
            public void onFailure(Call<UserData> call, Throwable t) {

            }
        });



    }
    public void renderConv(String id) {

//        DialogsFixtures.getConversations(id);

    }
    private Emitter.Listener onMessageReceive = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {

            runOnUiThread(new Runnable() {
                @Override

                public void run() {

                    Log.d("onMessageRecived", ""+args.length);
                    Log.d("First", ""+args[0]);
                    Log.d("msg", ""+args[1]);
                    prevText.setText((String) args[1]);
                    //do whatever you want
                }
            });
        }
    };
}
