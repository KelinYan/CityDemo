package com.djlink.kelin.citys.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.bigkoo.pickerview.adapter.ArrayWheelAdapter;
import com.bigkoo.pickerview.lib.WheelView;
import com.bigkoo.pickerview.listener.OnItemSelectedListener;
import com.djlink.kelin.citys.R;
import com.djlink.kelin.citys.utils.CityUtils;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

/**
 * Created by kelin on 16/10/8.
 */

public class CityPicker extends LinearLayout {

    private LinearLayout inflate;
    private WheelView province;
    private WheelView city;
    private WheelView county;
    private ArrayList<String> provinces;
    private ArrayList<String> cities;
    private ArrayWheelAdapter provinceAdapter;
    private ArrayWheelAdapter cityAdapter;
    private ArrayList<String> counties;
    private ArrayWheelAdapter countyAdapter;

    public CityPicker(Context context) {
        this(context,null);


    }

    public CityPicker(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CityPicker(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inflate = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.picker_view,this,true);
        provinces = CityUtils.getProvince(context);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        province = (WheelView) inflate.findViewById(R.id.wv_province);
        city = (WheelView) inflate.findViewById(R.id.wv_city);
        county = (WheelView) inflate.findViewById(R.id.wv_county);




        provinceAdapter = new ArrayWheelAdapter(provinces, provinces.size());
        province.setAdapter(provinceAdapter);
        province.setCurrentItem(0);

        cities = CityUtils.getCityByParentName(provinces.get(0));
        if (cities.size()==0) {
            cities.add(provinces.get(0));
        }
        cityAdapter = new ArrayWheelAdapter(cities,cities.size());
        city.setAdapter(cityAdapter);
        city.setCurrentItem(0);

        counties = CityUtils.getCityByParentName(cities.get(0));
        if (counties.size()==0) {
            counties.add(cities.get(0));
        }
        countyAdapter = new ArrayWheelAdapter(counties, counties.size());
        county.setAdapter(countyAdapter);
        county.setCurrentItem(0);



        province.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(int index) {
                cities = CityUtils.getCityByParentName(provinces.get(index));
                if (cities.size()==0) {
                    cities.add(provinces.get(index));
                }
                cityAdapter = new ArrayWheelAdapter(cities,cities.size());
                city.setAdapter(cityAdapter);
                city.setCurrentItem(0);

                counties = CityUtils.getCityByParentName(cities.get(0));
                if (counties.size()==0) {
                    counties.add(cities.get(0));
                }
                countyAdapter = new ArrayWheelAdapter(counties, counties.size());
                county.setAdapter(countyAdapter);
                county.setCurrentItem(0);
            }
        });

        city.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(int index) {
                Log.d(TAG, "onItemSelected: "+index+cities.get(index));
                counties = CityUtils.getCityByParentName(cities.get(index));
                Log.d(TAG, "onItemSelected: "+counties.size());
                if (counties.size()==0) {
                    counties.add(cities.get(index));
                }
                countyAdapter = new ArrayWheelAdapter(counties, counties.size());
                county.setAdapter(countyAdapter);
                county.setCurrentItem(0);
            }
        });

    }
}
