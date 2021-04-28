package com.mehmetsakiratasayin.text.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.widget.Switch;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mehmetsakiratasayin.text.R;
import com.mehmetsakiratasayin.text.model.Button;
import com.mehmetsakiratasayin.text.service.Interface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    Switch P1,P2,P3,P4;
    Runnable runnable;
    Handler handler;
ArrayList<Button> buttons;
private  String BASE_URL="https://app.hextechgreen.com/";
Retrofit retrofit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        P1=findViewById(R.id.P1);
        P2=findViewById(R.id.P2);
        P3=findViewById(R.id.P3);
        P4=findViewById(R.id.P4);
        Gson gson =new GsonBuilder().setLenient().create();

retrofit=new Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build();
// Again
        boolean shouldStopLoop = false;
        Handler mHandler = new Handler();

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                getdata();
                if (!shouldStopLoop) {
                    mHandler.postDelayed(this, 15000);
                }
            }
        };mHandler.post(runnable);




    }
    public void getdata(){
        Interface inter =retrofit.create(Interface.class);
        Call<Button> call = inter.getpost();
        call.enqueue(new Callback<Button>() {
            @Override
            public void onResponse(Call<Button> call, Response<Button> response) {
                if(response.isSuccessful()){
                   Button button= response.body();

                    P1.setChecked(button.isp1);
                    P2.setChecked(button.isp2);
                    P3.setChecked(button.isp3);
                    P4.setChecked(button.isp4);

                    System.out.println(button.isp1);
                    System.out.println(button.isp2);
                    System.out.println(button.isp3);
                    System.out.println(button.isp4);
                    System.out.println("-------------------------------");

                }
            }

            @Override
            public void onFailure(Call<Button> call, Throwable t) {

                t.printStackTrace();
                System.out.println("Hata");

            }
        });
    }

}