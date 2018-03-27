package com.example.biruk.androidclientchat.MVCmain;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.TextView;

import com.example.biruk.androidclientchat.ProviderData.RemoteSource.Injection;
import com.example.biruk.androidclientchat.ProviderData.model.User;
import com.example.biruk.androidclientchat.R;

import java.util.ArrayList;
import java.util.List;

public class Main2Activity extends AppCompatActivity implements SampleInterfaces.View {

    Presenter presenter;
    Button button3;
    TextView textView;
    UserListAdapter userListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        initPresenter();
        initRecyler();
        presenter.loadUserName();
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
}
