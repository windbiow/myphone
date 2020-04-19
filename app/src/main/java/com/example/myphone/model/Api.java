package com.example.myphone.model;

import com.example.myphone.model.domain.Commodity;
import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {

    @GET("phone/index/1")
    Call<Commodity> getCommodities();

}
