package com.example.apptodolistretrofit04092019.view.activty;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.apptodolistretrofit04092019.R;
import com.example.apptodolistretrofit04092019.api.RetrofitInit;
import com.example.apptodolistretrofit04092019.model.Response;
import com.example.apptodolistretrofit04092019.model.Sinhvien;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RetrofitInit
                .getInStance()
                .getAllSinhVien()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Response<List<Sinhvien>>>() {
                    @Override
                    public void accept(Response<List<Sinhvien>> listResponse) throws Exception {
                        List<Sinhvien> arraySinhvien = listResponse.getData();
                        Log.d("BBB",arraySinhvien.size() + "");
                    }
                });
    }
}
