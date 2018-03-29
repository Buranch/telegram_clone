package com.example.biruk.androidclientchat.Retrofit;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.biruk.androidclientchat.model.UserData;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Biruk on 3/10/2018.
 */

public class ApiServices{
    public static final String PREFS_NAME = "ChatApp";
    public static final String PREFS_TOKEN = "token";
    public static final String PREFS_USERID = "userID";
    public static final String PREFS_USERDATA = "userID";



    private Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://10.0.2.22:9666/")
            .addConverterFactory(GsonConverterFactory.create())
//            .addCallAdapterFactory()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build();
    private IAPIService service = retrofit.create(IAPIService.class);






    public void getUserData(Context context) throws JSONException {
        JSONObject obj = new JSONObject();
        SharedPreferences settings = context.getSharedPreferences(PREFS_NAME, 0);
        obj.put("id", settings.getString(PREFS_USERID, "null"));
        Call<UserData> call = service.getUserData(obj);

        call.enqueue(new Callback<UserData>() {
            @Override
            public void onResponse(Call<UserData> call, Response<UserData> response) {

            }

            @Override
            public void onFailure(Call<UserData> call, Throwable t) {

            }
        });
    }
    public void getConvList(String id,  final BookmarkCallback bookmarkCallback){
//        Call<Object> call = service.getConvList(id);
//        call.enqueue(new Callback<Object>() {
//            @Override
//            public void onResponse(Call<Object> call, Response<Object> response) {
//                Log.d("getConv", ""+response);
//                Gson gson = new Gson();
//                try {
//                    JSONObject jsonObject = new JSONObject(gson.toJson(response));
//                    Log.d("ResponseJSON", jsonObject.getJSONArray("body").toString());
//                    JSONArray jsonArray = jsonObject.getJSONArray("body");
//                    ArrayList<Dialog> dialogs = new ArrayList<Dialog>();
//
//                    for (int i =0; i < jsonArray.length(); i++){
//                        JSONObject ele = jsonArray.getJSONObject(i);
//                        Log.d("LoopingThr", ""+ele);
//                        String[] users = new String[ele.getJSONArray("participants").length()];
//                        for(int j = 0; j < ele.getJSONArray("participants").length(); j++){
//                            users[j] = ele.getJSONArray("participants").getString(j);
//                            Log.d("usr", users[j]);
//                        }
//                            dialogs.add(new Dialog(ele.getString("_id"), ele.getString("name"),
//                                ele.getString("pic"),users,ele.getString("lastMsg"),3));
//                            Log.d("dialog", gson.toJson(dialogs.get(i)));
//                    }
//
//                    bookmarkCallback.onSuccess(dialogs);
//
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<Object> call, Throwable t) {
//
//            }
//        });
    }


}
