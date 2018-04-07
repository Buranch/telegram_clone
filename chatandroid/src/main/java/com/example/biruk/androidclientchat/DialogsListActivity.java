package com.example.biruk.androidclientchat;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import com.example.biruk.androidclientchat.Chat.SocketSingleton;
//import com.example.biruk.androidclientchat.ProviderData.fixtures.DialogsFixtures;
import com.example.biruk.androidclientchat.ProviderData.fixtures.DialogsFixtures;
import com.example.biruk.androidclientchat.model.Dialog;
import com.example.biruk.androidclientchat.model.Message;
import com.example.biruk.androidclientchat.Retrofit.ApiServices;
import com.example.biruk.androidclientchat.Retrofit.BookmarkCallback;
import com.stfalcon.chatkit.dialogs.DialogsList;
import com.stfalcon.chatkit.dialogs.DialogsListAdapter;

import java.util.ArrayList;

import io.socket.client.Socket;
import io.socket.emitter.Emitter;

import static com.example.biruk.androidclientchat.Retrofit.ApiServices.PREFS_NAME;
import static com.example.biruk.androidclientchat.Retrofit.ApiServices.PREFS_USERDATA;


public class DialogsListActivity extends DemoDialogsActivity {

    private ArrayList<Dialog> dialogs;

    public static void open(Context context) {
        context.startActivity(new Intent(context, DialogsListActivity.class));
    }
    private DialogsList dialogsList;
    private Socket mSocket;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("chat Kit", "you are in default");
        setContentView(R.layout.activity_default_dialogs);
//        ChatSocket app = new ChatSocket();
        SocketSingleton app = SocketSingleton.getInstance();
        mSocket = app.getSocket();
        mSocket.connect();
        mSocket.emit("android", "yes! that's right!");
        Log.d("SocketConnected", ""+mSocket.connected());
        mSocket.on("node", onNodeGretting);
        dialogsList = (DialogsList) findViewById(R.id.dialogsList);
        initAdapter();
    }

    @Override
    public void onDialogClick(Dialog dialog) {
                MessagesActivity.open(this);
    }
    private void initAdapter() {
        super.dialogsAdapter = new DialogsListAdapter<>(super.imageLoader);
        //why dont you write the retrofit here
        super.dialogsAdapter.setItems(DialogsFixtures.getDialogs());
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        ApiServices apiServices = new ApiServices();
               apiServices.getConvList(settings.getString(PREFS_USERDATA, "sss"), new BookmarkCallback() {
                    @Override
                    public ArrayList<Dialog> onSuccess(ArrayList<Dialog> a) {
                        Log.d("onSuccess", ""+a.size());
                        return a;
                    }

                    @Override
                    public void onError() {
                    }
                }) ;
        super.dialogsAdapter.setOnDialogClickListener(this);
        super.dialogsAdapter.setOnDialogLongClickListener(this);
        dialogsList.setAdapter(super.dialogsAdapter);
    }

    //for example
    private void onNewMessage(String dialogId, Message message) {
        boolean isUpdated = dialogsAdapter.updateDialogWithMessage(dialogId, message);
        if (!isUpdated) {
            //Dialog with this ID doesn't exist, so you can create new Dialog or update all dialogs list
        }
    }
    private Emitter.Listener onNodeGretting = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            Log.v("Node", "Node gretting");

            Log.v("GrettingL", ""+args.length);
            Log.v("GrettingL", ""+args[0]);

        }
    };
    //for example
    private void onNewDialog(Dialog dialog) {
        dialogsAdapter.addItem(dialog);
    }
}
