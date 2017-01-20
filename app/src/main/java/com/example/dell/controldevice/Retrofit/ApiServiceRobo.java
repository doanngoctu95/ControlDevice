package com.example.dell.controldevice.Retrofit;

import com.example.dell.controldevice.Object.Example;
import com.example.dell.controldevice.Object.ResultRobo;
import com.example.dell.controldevice.Object.Utils;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by dell on 18/01/2017.
 */
public interface ApiServiceRobo {

    @GET(Utils.MANUAL_MODE)
    Call<ResultRobo> manualMode();

    @GET(Utils.AUTO_MODE)
    Call<ResultRobo> autoMode();

    @GET(Utils.ON_ROBO)
    Call<ResultRobo> turnOnRobo();

    @GET(Utils.OFF_ROBO)
    Call<ResultRobo> turnOffRobo();

    @GET(Utils.GET_STATUS_ROBO)
    Call<ResultRobo> getStatusRobo();

    @GET(Utils.GO_AHEAD)
    Call<ResultRobo> goAhead();

    @GET(Utils.GO_DOWN)
    Call<ResultRobo> goDown();

    @GET(Utils.TURN_LEFT)
    Call<ResultRobo> turnLeft();

    @GET(Utils.TURN_RIGHT)
    Call<ResultRobo> turnRight();

    @GET(Utils.STOP)
    Call<ResultRobo> stop();
}
