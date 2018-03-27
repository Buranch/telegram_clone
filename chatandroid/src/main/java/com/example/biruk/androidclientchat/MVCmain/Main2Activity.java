package com.example.biruk.androidclientchat.MVCmain;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.biruk.androidclientchat.ProviderData.RemoteSource.Injection;
import com.example.biruk.androidclientchat.ProviderData.model.Dialog;
import com.example.biruk.androidclientchat.ProviderData.model.User;
import com.example.biruk.androidclientchat.R;
import com.squareup.picasso.Picasso;
import com.stfalcon.chatkit.commons.ImageLoader;
import com.stfalcon.chatkit.dialogs.DialogsList;
import com.stfalcon.chatkit.dialogs.DialogsListAdapter;

import java.util.ArrayList;
import java.util.List;

public class Main2Activity extends AppCompatActivity implements SampleInterfaces.View, DialogsListAdapter.OnDialogClickListener<Dialog>,
        DialogsListAdapter.OnDialogLongClickListener<Dialog> {

    Presenter presenter;
    Button button3;
    TextView textView;
    UserListAdapter userListAdapter;
    DialogsList dialogsList;
    DialogsListAdapter<Dialog> dialogsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main2);
        setContentView(R.layout.activity_default_dialogs);
        dialogsList = (DialogsList) findViewById(R.id.dialogsList);
        initPresenter();
//        initRecyler();
        dialogAdapterInit();
//        presenter.loadUserName();

        presenter.getDialogs();
    }
    public void initRecyler(){
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(itemAnimator);
        userListAdapter = new UserListAdapter(new UserListAdapter.ItemClickListner() {
            @Override
            public void onItemClick(User user) {
//                showNewsInWebBrowser(article.getUrl());
            }

        }, new ArrayList<User>());
        recyclerView.setAdapter(userListAdapter);
    }

    public void dialogAdapterInit(){
        ImageLoader imageLoader = new ImageLoader() {
            @Override
            public void loadImage(ImageView imageView, String url) {
//                String imageUri = "http://10.0.2.22:9666/images/red.jpg";
                Picasso.with(Main2Activity.this).load(url)
                        .error(R.drawable.w)
                        .into(imageView);
            }
        };
        dialogsAdapter = new DialogsListAdapter<>(imageLoader);
        dialogsAdapter.setOnDialogClickListener(this);
        dialogsAdapter.setOnDialogLongClickListener(this);
        dialogsList.setAdapter(dialogsAdapter);
    }
    // Create the presenter
    private void initPresenter() {
        presenter = new Presenter(
                Injection.provideDataService(),this);
    }

    @Override
    public void showText(String a) {

        textView.setText(a);

    }
    @Override
    public void showNoText() {

    }
    @Override
    public void showUserList(List<User> userList) {
        if (userListAdapter!=null){
        userListAdapter.addUsers(userList);}
    }

    @Override
    public void renderDialogList(List<Dialog> dialogs) {
        dialogsAdapter.setItems(dialogs);
    }

    @Override
    public void onDialogClick(Dialog dialog) {

    }

    @Override
    public void onDialogLongClick(Dialog dialog) {

    }
}
