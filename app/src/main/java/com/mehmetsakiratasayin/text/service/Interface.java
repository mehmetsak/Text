package com.mehmetsakiratasayin.text.service;


import com.mehmetsakiratasayin.text.model.Button;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface  Interface {
    @GET("mini/test")
    Call<Button> getpost();
}
