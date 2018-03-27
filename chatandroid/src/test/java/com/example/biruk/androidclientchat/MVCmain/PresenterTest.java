package com.example.biruk.androidclientchat.MVCmain;

import android.support.annotation.NonNull;

import com.example.biruk.androidclientchat.ProviderData.RemoteSource.ApiEndPoint;
import com.example.biruk.androidclientchat.ProviderData.RemoteSource.IDataService;
import com.example.biruk.androidclientchat.ProviderData.RemoteSource.RetrofitInit;
import com.example.biruk.androidclientchat.ProviderData.model.User;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Biruk on 3/27/2018.
 */
public class PresenterTest {
    @Test
    public void shouldPassThis() {
        Assert.assertEquals(1, 1);
    }


    @Test
    public void shouldPassTextView() {

        //given

        SampleInterfaces.View view = new MockView();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.22:9666/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        final ApiEndPoint apiService = retrofit.create(ApiEndPoint.class);
//        IDataService iDataService = new MockDataService(apiService);
        Presenter presenter = new Presenter(new IDataService() {
            ApiEndPoint service = RetrofitInit.getClient().create(ApiEndPoint.class);
            @Override
            public void getNameList(@NonNull final LoadDataCallBack<User> callback) {
                Call<User> usersList = service.getUsers();
                usersList.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
//                callback.onDataLoaded(response.body());
                        //verify the list
//                    Log.d("onResponse", "wowww");
                        callback.onDataLoaded(Arrays.asList(new User("w3", "hello there", "aav", false)));
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        callback.onDataNotAvailable();
//                    Log.d("onF", "wowww");

                    }
                });
            }

            ;

        }, view);
        //when

        presenter.loadText();
        Assert.assertEquals(true, ((MockView) view).passed);
//        Assert.assertEquals(true, ((MockView) view).passedNo);
//

        //then
    }


    public class MockView implements SampleInterfaces.View {
        boolean passed;
        boolean passedNo;

        @Override
        public void showText(String a) {
            passed = true;
        }

        @Override
        public void showNoText() {

            passedNo = true;
        }
    }
}