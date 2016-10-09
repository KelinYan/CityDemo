package com.djlink.kelin.citys.utils;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import com.djlink.kelin.citys.entity.City;
import com.djlink.kelin.citys.entity.Response;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;

/**
 * Created by kelin on 16/10/7.
 */

public class CityUtils {
    static Response response = null;
    /**
     * 初始化数据
     * @param context
     */
    private static void initData(Context context){

        AssetManager assets = context.getResources().getAssets();
        try {
            InputStream open = assets.open("city.json");
            Reader reader = new BufferedReader(new InputStreamReader(open));
            Gson gson = new Gson();
            response = gson.fromJson(reader, Response.class);
            Log.d("dddd", "onCreate: "+ CityUtils.response.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 返回省
     * @param context
     * @return
     */
    public static ArrayList<String> getProvince(Context context){
        ArrayList<String> province = new ArrayList<>();
        if (response == null) {
            initData(context);
        }

        for (City city : response.getResult()) {
            if ("0".equals(city.getParentid())) {
                province.add(city.getName());
            }
        }
        
        return province;
    }

    /**
     * 根据城市id获得辖区城市
     * @param name
     * @return
     */
    public static ArrayList<String> getCityByParentName(String name){
        if (response.getResult() == null) {
            return null;
        }
        ArrayList<String> cities = new ArrayList<>();

        for (City city : response.getResult()) {
            if (name.equals(city.getParentname())) {
                cities.add(city.getName());
            }
        }

        return cities;
    }




}
