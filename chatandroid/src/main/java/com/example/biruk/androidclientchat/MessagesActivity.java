package com.example.biruk.androidclientchat;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.biruk.androidclientchat.Chat.SocketSingleton;
import com.example.biruk.androidclientchat.ProviderData.fixtures.MessagesFixtures;
import com.example.biruk.androidclientchat.model.Message;
import com.stfalcon.chatkit.messages.MessageInput;
import com.stfalcon.chatkit.messages.MessagesList;
import com.stfalcon.chatkit.messages.MessagesListAdapter;

import io.socket.client.Socket;
import io.socket.emitter.Emitter;


/**
 * Created by Biruk on 3/2/2018.
 */

public class MessagesActivity extends DemoMessageActivity
        implements MessageInput.InputListener,
        MessageInput.AttachmentsListener {

    public static void open(Context context) {
        context.startActivity(new Intent(context, MessagesActivity.class));
    }

    private MessagesList messagesList;
    private Socket mSocket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_default_messages);

        this.messagesList = (MessagesList) findViewById(R.id.messagesList);
        initAdapter();

        MessageInput input = (MessageInput) findViewById(R.id.input);
        input.setInputListener(this);
        //loading Socket

        SocketSingleton app = SocketSingleton.getInstance();
        mSocket = app.getSocket();
        mSocket.connect();

        mSocket.on("toandroid", onMessageReceive);
        mSocket.emit("android", "from MessageActivity");
        Log.d("MessagesSocket", "geta hoy");
    }

    @Override
    public boolean onSubmit(CharSequence input) {
        //adding new message and updating the list
        mSocket.emit("toweb", input.toString());
        super.messagesAdapter.addToStart(
                MessagesFixtures.getTextMessage(input.toString()), true);

//        return onIncomingMessage(input.toString());
        return true;
    }
    public boolean onIncomingMessage(String input) {
        Log.d("onIncomingM", "msg"+input);
        //adding new message and updating the list
        super.messagesAdapter.addToStart(
                MessagesFixtures.getTextMessage(input), true);

        return true;
    }

    @Override
    public void onAddAttachments() {
        //work good here
        super.messagesAdapter.addToStart(
                MessagesFixtures.getImageMessage(), true);
    }

    public void grabAttachedPic(String picName) {
        super.messagesAdapter.addToStart(MessagesFixtures.getUploadedImageResource(picName), true);
    }

    private void initAdapter() {
        super.messagesAdapter = new MessagesListAdapter<>(super.senderId, super.imageLoader);
        super.messagesAdapter.enableSelectionMode(this);
        super.messagesAdapter.setLoadMoreListener(this);
        super.messagesAdapter.registerViewClickListener(R.id.messageUserAvatar,
                new MessagesListAdapter.OnMessageViewClickListener<Message>() {
                    @Override
                    public void onMessageViewClick(View view, Message message) {
                        AppUtils.showToast(MessagesActivity.this,
                                message.getUser().getName() + " avatar click",
                                false);
                    }
                });
        this.messagesList.setAdapter(super.messagesAdapter);
    }
    private Emitter.Listener onMessageReceive = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {

            runOnUiThread(new Runnable() {
                @Override

                public void run() {
                    onIncomingMessage(args[0].toString());
//                    onAddAttachments();
//                    grabAttachedPic(args[0].toString());
                }
            });
        }
    };

}
