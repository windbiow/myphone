package com.example.myphone.presenter.impl;

import android.util.Log;
import com.example.myphone.callback.IHomeCallback;
import com.example.myphone.model.Api;
import com.example.myphone.model.domain.Commodity;
import com.example.myphone.presenter.IHomePresenter;
import com.example.myphone.util.RetrofitManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import java.net.HttpURLConnection;

public class HomePresenterImpl implements IHomePresenter {

    IHomeCallback callback;

    @Override
    public void getCommodities() {
        Retrofit retrofit = RetrofitManager.getInstance().getRetrofit();
        Api api =retrofit.create(Api.class);
        Call<Commodity> task = api.getCommodities();
        task.enqueue(new Callback<Commodity>() {
            @Override
            public void onResponse(Call<Commodity> call, Response<Commodity> response) {
                int code = response.code();
                if(code == HttpURLConnection.HTTP_OK){
                    Commodity commodity = response.body();
                    Log.d("this.toString()","responseBody -->"+ commodity.toString());
                    callback.onCategoriesLoaded(commodity.getData());
                }
            }

            @Override
            public void onFailure(Call<Commodity> call, Throwable t) {
                Log.d("this.toString()","faliure -->"+t.toString());
                Log.d("this.toString()","faliure -->"+t.toString());
            }
        });
    }

    @Override
    public void registerCallback(IHomeCallback callback) {
       this.callback = callback;
    }

    @Override
    public void unregisterCallback() {
        this.callback = null;
    }
}
