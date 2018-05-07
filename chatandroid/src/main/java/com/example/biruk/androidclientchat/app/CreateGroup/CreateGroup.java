package com.example.biruk.androidclientchat.app.CreateGroup;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.biruk.androidclientchat.APIService.Injection;
import com.example.biruk.androidclientchat.R;

public class CreateGroup extends AppCompatActivity implements CreateGroupInterface.View{
    CreateGroupPresenter createGroupPresenter;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_group);
        button = (Button) findViewById(R.id.create_group);
        initPresenter();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                createGroupPresenter.createGroup(new NewGroup(
//                        "Name", "profile","observalbel ",null, null, null
//                ));
//                createGroupPresen


            }
        });
    }
    private void initPresenter() {
        createGroupPresenter = new CreateGroupPresenter(
                Injection.provideDataService(),this, this);
    }
}
