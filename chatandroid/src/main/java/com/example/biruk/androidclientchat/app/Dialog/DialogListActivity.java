package com.example.biruk.androidclientchat.app.Dialog;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.Toast;
import com.example.biruk.androidclientchat.APIService.Injection;
import com.example.biruk.androidclientchat.model.Dialog;
import com.example.biruk.androidclientchat.R;
import com.example.biruk.androidclientchat.model.StringConstants;
import com.github.clans.fab.FloatingActionButton;
import com.squareup.picasso.Picasso;
import com.stfalcon.chatkit.commons.ImageLoader;
import com.stfalcon.chatkit.dialogs.DialogsList;
import com.stfalcon.chatkit.dialogs.DialogsListAdapter;

//import com.stfalcon.chatkit.dialogs.DialogsList;
//import com.stfalcon.chatkit.dialogs.DialogsListAdapter;

import java.util.List;

public class DialogListActivity extends AppCompatActivity implements DialogListContracts.View,
        DialogsListAdapter.OnDialogLongClickListener<Dialog>,
        DialogsListAdapter.OnDialogClickListener<Dialog>

        {
    DialogListPresenter dialogListPresenter;
    DialogsList dialogsList;
    DialogsListAdapter<Dialog> dialogsAdapter;
    FloatingActionButton fb1;
    FloatingActionButton fb2;


            @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_default_dialogs);
        dialogsList = (DialogsList) findViewById(R.id.dialogsList);

        initPresenter();
        dialogAdapterInit();
        fb1 = (FloatingActionButton) findViewById(R.id.fab1);
        fb2 = (FloatingActionButton) findViewById(R.id.fab2);
        fb1.setOnClickListener(floatClikListener);
        fb2.setOnClickListener(floatClikListener);

        dialogListPresenter.getDialogs();

//        dialogListPresenter.getUserInfo();

    }
    public void dialogAdapterInit(){
        ImageLoader imageLoader = new ImageLoader() {
            @Override
            public void loadImage(ImageView imageView, String url) {
                String ur = StringConstants.URL_BASIC+url;
                Picasso.with(DialogListActivity.this).load(ur)
//                        .error(R.drawable.w)
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
        Log.v("dialogs",dialogs.get(0).getName().toString());
        dialogsAdapter.setItems(dialogs);
    }

    @Override
    public void onDialogClick(Dialog dialog) {

        Toast.makeText(this, dialog.getType(), Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onDialogLongClick(Dialog dialog) {

    }
            private View.OnClickListener floatClikListener = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    switch (v.getId()) {
                        case R.id.fab1:
                            Log.d("FloatButton ", "reate group");
                            break;
                        case R.id.fab2:

                            Log.d("FloatButton ", "reate annel");
                            break;

                    }
                }
            };

            @Override
            public boolean onCreateOptionsMenu(Menu menu) {
                MenuInflater menuInflater = getMenuInflater();
                menuInflater.inflate(R.menu.menu_search, menu);
                MenuItem menuItem = menu.findItem(R.id.menuSearch);

                SearchView searchView = (SearchView) menuItem.getActionView();

                searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                    @Override
                    public boolean onQueryTextSubmit(String s) {
                        return false;
                    }

                    @Override
                    public boolean onQueryTextChange(String s) {
                        Log.d("QUERYSEAC", ""+s);
//                        dialogsAdapter.clear();


                        //do your search here, probably prepare two list adapters.
                        //or use fragment I don't know
                        return false;
                    }
                });

                return super.onCreateOptionsMenu(menu);


            }
        }
