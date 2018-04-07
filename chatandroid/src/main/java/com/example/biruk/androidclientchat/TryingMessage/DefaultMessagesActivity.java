package com.example.biruk.androidclientchat.TryingMessage;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.biruk.androidclientchat.APIService.Injection;
import com.example.biruk.androidclientchat.AppUtils;
import com.example.biruk.androidclientchat.ProviderData.fixtures.MessagesFixtures;
import com.example.biruk.androidclientchat.R;
import com.example.biruk.androidclientchat.model.Dialog;
import com.example.biruk.androidclientchat.model.Message;
import com.stfalcon.chatkit.messages.MessageInput;
import com.stfalcon.chatkit.messages.MessagesList;
import com.stfalcon.chatkit.messages.MessagesListAdapter;

import java.text.MessageFormat;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by User on 4/6/2018.
 */


public class DefaultMessagesActivity extends DemoMessagesActivity
        implements MessageInput.InputListener,
        MessageInput.AttachmentsListener,DefaultMessageContracts.View {
    public DefaultMessagePresenter defaultMessagePresenter;

    public static void open(Context context) {
        context.startActivity(new Intent(context, DefaultMessagesActivity.class));
    }

    private MessagesList messagesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_default_messages);

        this.messagesList = (MessagesList) findViewById(R.id.messagesList);
        initAdapter();
        initPresenter();

        MessageInput input = (MessageInput) findViewById(R.id.input);
        input.setInputListener(this);

        defaultMessagePresenter.getDialogs();

    }

    public void initPresenter(){
        Log.v("initPre","initPresenter");
        this.defaultMessagePresenter = new DefaultMessagePresenter(Injection.provideDataService(),this);
    }

    @Override
    public boolean onSubmit(CharSequence input) {
        Log.v("Number",messagesAdapter.getItemCount()+"");
        //super.messagesAdapter.clear();
        super.messagesAdapter.addToStart(
                MessagesFixtures.getTextMessage(input.toString()), true);
        return true;
    }

    @Override
    public void onAddAttachments() {
        super.messagesAdapter.addToStart(
                MessagesFixtures.getImageMessage(), true);
    }

    //just override loadMessages method so as to prevent displaying default stored messages.
    public void loadMessages(){

    }

    private void initAdapter() {
        super.messagesAdapter = new MessagesListAdapter<>(super.senderId, super.imageLoader);
        super.messagesAdapter.enableSelectionMode(this);
        super.messagesAdapter.setLoadMoreListener(this);
        super.messagesAdapter.registerViewClickListener(R.id.messageUserAvatar,
                new MessagesListAdapter.OnMessageViewClickListener<Message>() {
                    @Override
                    public void onMessageViewClick(View view, Message message) {
                        AppUtils.showToast(DefaultMessagesActivity.this,
                                message.getUser().getName() + " avatar click",
                                false);
                    }
                });
        this.messagesList.setAdapter(super.messagesAdapter);
    }

    @Override
    public void renderDialogList(List<Dialog> dialogs) {
        for(int i=0;i<dialogs.size();i++){
            Log.v("dialogs text",dialogs.get(i).getLastMsg().getTimeStamp().toString());
            super.messagesAdapter.addToStart(MessagesFixtures.getTextMessage(dialogs.get(i).getName()), true);
        }
        Log.v("dialogs",dialogs.get(0).getName().toString());
    }
}
