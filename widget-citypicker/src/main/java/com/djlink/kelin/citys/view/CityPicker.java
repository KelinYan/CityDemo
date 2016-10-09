package com.djlink.kelin.citys.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.bigkoo.pickerview.R;
import com.bigkoo.pickerview.adapter.ArrayWheelAdapter;
import com.bigkoo.pickerview.lib.WheelView;
import com.bigkoo.pickerview.listener.OnItemSelectedListener;
import com.djlink.kelin.citys.utils.CityUtils;

import java.util.ArrayList;

/**
 * Created by kelin on 16/10/8.
 */

public class CityPicker extends LinearLayout {

    public OnSelectedCity onSelectedCity;

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
    private String currentProvince;
    private String currentCity;
    private String currentCounty;


    public CityPicker(Context context) {
        super(context);
        inflate = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.picker_view, this, true);
        provinces = CityUtils.getProvince(context);
    }

    public CityPicker(Context context, AttributeSet attrs) {
        super(context, attrs);
        inflate = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.picker_view, this, true);
        provinces = CityUtils.getProvince(context);
    }

    public CityPicker(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inflate = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.picker_view, this, true);
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
        updateCity(0);
        updateCounty(0);

        province.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(int index) {
                updateCity(index);
                updateCounty(0);
            }
        });

        city.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(int index) {
                updateCounty(index);
            }
        });

        county.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(int index) {
                currentCounty = counties.get(index);
                onCityChange(currentProvince, currentCity, currentCounty);
            }
        });
    }

    private void updateCity(int index){
        currentProvince = provinces.get(index);
        cities = CityUtils.getCityByParentName(currentProvince);
        if (cities.size() == 0) {
            cities.add(currentProvince);
        }
        cityAdapter = new ArrayWheelAdapter(cities, cities.size());
        city.setAdapter(cityAdapter);
        city.setCurrentItem(0);
        currentCity = cities.get(0);
    }
    private void updateCounty(int index){
        currentCity = cities.get(index);
        counties = CityUtils.getCityByParentName(currentCity);
        if (counties.size() == 0) {
            counties.add(currentCity);
        }
        countyAdapter = new ArrayWheelAdapter(counties, counties.size());
        county.setAdapter(countyAdapter);
        county.setCurrentItem(0);
        currentCounty = counties.get(0);
        onCityChange(currentProvince, currentCity, currentCounty);
    }

    private void onCityChange(String province, String city, String county) {
        if (this.onSelectedCity != null) {
            onSelectedCity.selectedCity(province, city, county);
        }
    }

    public void setOnSelectedCity(OnSelectedCity onSelectedCity) {
        this.onSelectedCity = onSelectedCity;
    }

    public interface OnSelectedCity {
        void selectedCity(String province, String city, String county);
    }
}
