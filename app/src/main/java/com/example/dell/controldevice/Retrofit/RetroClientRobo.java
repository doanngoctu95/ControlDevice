package com.example.dell.controldevice.Retrofit;

import com.example.dell.controldevice.Object.Utils;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by dell on 18/01/2017.
 */
public class RetroClientRobo {
    /********
     * URLS
     *******/


    /**
     * Get Retrofit Instance
     */
    private static Retrofit getRetrofitInstanceRobo() {
        return new Retrofit.Builder()
                .baseUrl(Utils.ROOT_URL_ROBO)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    /**
     * Get API Service
     *
     * @return API Service ROBO
     */
    public static ApiServiceRobo getApiService() {
        return getRetrofitInstanceRobo().create(ApiServiceRobo.class);
    }
}
