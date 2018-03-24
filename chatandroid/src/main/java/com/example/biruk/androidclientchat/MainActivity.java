package com.example.biruk.androidclientchat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        DialogsListActivity.open(this);

        LoginActivity.open(this);
        /*
        String imageUri = "http://10.0.2.22:9666/images/red.jpg";
        ImageView  iv = (ImageView) findViewById(R.id.imageView2);
        Picasso.with(MainActivity.this)
                .load(imageUri)
                //.placeholder(R.drawable.w)
                .error(R.drawable.w)
                .into(iv);
        Picasso.with(MainActivity.this).setLoggingEnabled(true);
//        Glide.with(MainActivity.this).load(imageUri).into(iv);
*/
    }
}
