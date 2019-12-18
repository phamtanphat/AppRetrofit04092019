package com.example.apptodolistretrofit04092019.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.OkHttpClient.Builder;
import okhttp3.Protocol;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInit {

    private static Retrofit retrofit = null;

    private RetrofitInit(){

    }

    public static ApiResponse getInStance(){
        if (retrofit == null){
            retrofit = initRetrofit();
        }
        return  retrofit.create(ApiResponse.class);
    }

    private static Retrofit initRetrofit() {

        // Cong cu convert du lieu json sang java object
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();

        // OkhttpClient : setUp connection
        OkHttpClient okHttpClient = new Builder()
                            .readTimeout(10, TimeUnit.SECONDS)
                            .writeTimeout(10,TimeUnit.SECONDS)
                            .retryOnConnectionFailure(true)
                            .protocols(Arrays.asList(Protocol.HTTP_1_1))
                            .build();

        Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://172.16.1.17:8080/apiSinhvien/")
                    .client(okHttpClient)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        return retrofit;
    }
}
