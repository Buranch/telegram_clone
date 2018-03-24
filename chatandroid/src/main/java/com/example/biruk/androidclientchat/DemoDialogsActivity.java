package com.example.biruk.androidclientchat;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import android.widget.ImageView;

import com.example.biruk.androidclientchat.ProviderData.model.Dialog;
import com.squareup.picasso.Picasso;
import com.stfalcon.chatkit.commons.ImageLoader;
import com.stfalcon.chatkit.dialogs.DialogsListAdapter;


/*
 * Created by troy379 on 05.04.17.
 */
public abstract class DemoDialogsActivity extends Activity
        implements DialogsListAdapter.OnDialogClickListener<Dialog>,
        DialogsListAdapter.OnDialogLongClickListener<Dialog> {

    protected ImageLoader imageLoader;
    protected DialogsListAdapter<Dialog> dialogsAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        imageLoader = new ImageLoader() {
            @Override
            public void loadImage(ImageView imageView, String url) {
//                String imageUri = "http://10.0.2.22:9666/images/red.jpg";
                Picasso.with(DemoDialogsActivity.this).load(url)
                        .error(R.drawable.w)
                        .into(imageView);
            }
        };
    }

    @Override
    public void onDialogLongClick(Dialog dialog) {
        AppUtils.showToast(
                this,
                dialog.getDialogName(),
                false);
    }
}
