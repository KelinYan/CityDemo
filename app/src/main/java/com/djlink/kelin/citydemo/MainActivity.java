package com.djlink.kelin.citydemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.djlink.kelin.citys.view.CityPicker;


public class MainActivity extends AppCompatActivity {


    private CityPicker city;
    private String TAG = "aaa";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        city = (CityPicker) findViewById(R.id.main_city);

        city.setOnSelectedCity(new CityPicker.OnSelectedCity() {


            @Override
            public void selectedCity(String province, String city, String county) {

                Log.d(TAG, "selectedCity: "+province+"=="+city+"=="+county);
            }
        });


    }
}
