package com.example.biruk.androidclientchat.chips;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.biruk.androidclientchat.R;
import com.example.biruk.androidclientchat.chips.IAddMemberGroup.View;
import com.example.biruk.androidclientchat.model.SearchItem;

import java.util.List;

public class AddMemberGroupActivity extends AppCompatActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chips);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragmentContainer, ItemsFragment.newInstance())
                    .commit();
        }
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainer, ItemsFragment.newInstance())
                .commit();
    }

    @Override
    protected void onResumeFragments() {
        super.onResumeFragments();
    }


}
