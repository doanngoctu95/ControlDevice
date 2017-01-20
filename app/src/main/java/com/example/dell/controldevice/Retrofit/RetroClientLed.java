package com.example.dell.controldevice.Retrofit;

import com.example.dell.controldevice.Object.Utils;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * @author Pratik Butani
 */
public class RetroClientLed {

    /********
     * URLS
     *******/


    /**
     * Get Retrofit Instance
     */
    private static Retrofit getRetrofitInstance() {
        return new Retrofit.Builder()
                .baseUrl(Utils.ROOT_URL_LED)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    /**
     * Get API Service
     *
     * @return API Service LED
     */
    public static ApiServiceLed getApiService() {
        return getRetrofitInstance().create(ApiServiceLed.class);
    }

}
