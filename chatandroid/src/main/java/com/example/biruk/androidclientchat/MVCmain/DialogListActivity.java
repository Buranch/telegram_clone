package com.example.biruk.androidclientchat.MVCmain;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.biruk.androidclientchat.ProviderData.RemoteSource.Injection;
import com.example.biruk.androidclientchat.ProviderData.model.Dialog;
import com.example.biruk.androidclientchat.R;
import com.squareup.picasso.Picasso;
import com.stfalcon.chatkit.commons.ImageLoader;

import com.stfalcon.chatkit.dialogs.DialogsList;
import com.stfalcon.chatkit.dialogs.DialogsListAdapter;

import java.util.List;

public class DialogListActivity extends AppCompatActivity implements DialogListContracts.View, DialogsListAdapter.OnDialogClickListener<Dialog>,
        DialogsListAdapter.OnDialogLongClickListener<Dialog> {
    DialogListPresenter dialogListPresenter;
    DialogsList dialogsList;
    DialogsListAdapter<Dialog> dialogsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_default_dialogs);
        dialogsList = (DialogsList) findViewById(R.id.dialogsList);
        initPresenter();
        dialogAdapterInit();
        dialogListPresenter.getDialogs();
    }
    public void dialogAdapterInit(){
        ImageLoader imageLoader = new ImageLoader() {
            @Override
            public void loadImage(ImageView imageView, String url) {
                String imageUri = "http://10.0.2.22:9666/images/red.jpg";
                Picasso.with(DialogListActivity.this).load(imageUri)
                        .error(R.drawable.w)
                        .into(imageView);
            }
        };
        dialogsAdapter = new DialogsListAdapter<>(imageLoader);
        dialogsAdapter.setOnDialogClickListener(this);
        dialogsAdapter.setOnDialogLongClickListener(this);
        dialogsList.setAdapter(dialogsAdapter);
    }
    // Create the dialogListPresenter
    private void initPresenter() {
        dialogListPresenter = new DialogListPresenter(
                Injection.provideDataService(),this);
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
