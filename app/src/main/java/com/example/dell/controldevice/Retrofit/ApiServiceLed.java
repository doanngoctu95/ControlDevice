package com.example.dell.controldevice.Retrofit;

import com.example.dell.controldevice.Object.Example;
import com.example.dell.controldevice.Object.Utils;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * @author Pratik Butani.
 */
public interface ApiServiceLed {

    @GET(Utils.GET_STATUS)
    Call<Example> getStatusJSON();

    @GET(Utils.LED_ONE_ON)
    Call<Example> setOneOn();

    @GET(Utils.LED_TWO_ON)
    Call<Example> setTwoOn();

    @GET(Utils.LED_THREE_ON)
    Call<Example> setThreeOn();

    @GET(Utils.LED_ONE_OFF)
    Call<Example> setOneOff();

    @GET(Utils.LED_TWO_OFF)
    Call<Example> setTwoOff();

    @GET(Utils.LED_THREE_OFF)
    Call<Example> setThreeOff();

    @GET(Utils.LED_ALL_OFF)
    Call<Example> setAllOff();

    @GET(Utils.LED_ALL_ON)
    Call<Example> setAllOn();



}
