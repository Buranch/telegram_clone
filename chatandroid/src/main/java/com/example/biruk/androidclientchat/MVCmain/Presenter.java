package com.example.biruk.androidclientchat.MVCmain;

/**
 * Created by Biruk on 3/27/2018.
 */

public class Presenter implements SampleInterfaces.Presenter{


    DataSource dataSource;
    SampleInterfaces.View view;

    public Presenter(DataSource dataSource, SampleInterfaces.View view) {
        this.view = view;
        this.dataSource = dataSource;
    }

    @Override
    public void loadText() {
        view.showText(dataSource.getStringText());
    }
}
