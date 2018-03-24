package com.example.biruk.androidclientchat.Chat;

import android.app.Application;
import android.util.Log;

import java.net.URISyntaxException;
import io.socket.client.IO;
import io.socket.client.Socket;

/**
 * Created by Biruk on 3/2/2018.
 */

public class ChatSocket extends Application {
    private Socket mSocket;
    public static final String CHAT_SERVER_URL = "http://10.0.2.22:9666";
    {

        try {
            mSocket = IO.socket(CHAT_SERVER_URL);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    public Socket getSocket()

    {
        Log.v("ChatSocket", "getSocket");
        return mSocket;
    }
    }
