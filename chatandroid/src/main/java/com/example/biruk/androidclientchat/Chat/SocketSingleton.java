package com.example.biruk.androidclientchat.Chat;

import android.util.Log;

import java.net.URISyntaxException;

import io.socket.client.IO;
import io.socket.client.Socket;

/**
 * Created by Biruk on 3/2/2018.
 */

public class SocketSingleton {

    private static volatile SocketSingleton socketSingleton = null;
    private static Socket mSocket;
    public static final String CHAT_SERVER_URL = "http://10.0.2.22:9666";
//    public static final String CHAT_SERVER_URL = "http://192.168.137.1:9666";
    private SocketSingleton(){
        try {
            mSocket = IO.socket(CHAT_SERVER_URL);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }


    public static SocketSingleton getInstance() {
        if(socketSingleton == null){
            socketSingleton = new SocketSingleton();
        }
        return socketSingleton;
    }
    public Socket getSocket()
    {
        return mSocket;
    }
}
