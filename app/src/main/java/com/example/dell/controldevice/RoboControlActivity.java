package com.example.dell.controldevice;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;

import com.example.dell.controldevice.Object.Example;
import com.example.dell.controldevice.Object.ResultRobo;
import com.example.dell.controldevice.Retrofit.ApiServiceLed;
import com.example.dell.controldevice.Retrofit.ApiServiceRobo;
import com.example.dell.controldevice.Retrofit.RetroClientLed;
import com.example.dell.controldevice.Retrofit.RetroClientRobo;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by dell on 10/01/2017.
 */
public class RoboControlActivity extends AppCompatActivity implements View.OnTouchListener {
    private ImageView imGoAhead,imTurnRight,imTurnLeft,imGoDown,imStop;
    private Switch swMode,swOnOff,swClean;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.robot_control_activity);
        initView();
        getStatusRobo();

    }

    private void initView() {

        imGoAhead= (ImageView) findViewById(R.id.btnGoHead);
        imGoDown= (ImageView) findViewById(R.id.btnGoDown);
        imTurnLeft= (ImageView) findViewById(R.id.btnGoLeft);
        imTurnRight= (ImageView) findViewById(R.id.btnGoRight);

        swMode= (Switch) findViewById(R.id.swAuto);
        swOnOff= (Switch) findViewById(R.id.swOnOff);



        swOnOff.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    turnOnRobo();
                    swMode.setClickable(true);
                }
                else {
                    turnOffRobo();

                    swMode.setChecked(false);
                    swMode.setClickable(false);
                }

            }
        });



        swMode.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    turnAutoRobo();
                }
                else turnManualRobo();
            }
        });

        imGoAhead.setOnTouchListener(this);
        imGoDown.setOnTouchListener(this);
        imTurnLeft.setOnTouchListener(this);
        imTurnRight.setOnTouchListener(this);

    }

    private void getStatusRobo(){
        ApiServiceRobo apiServiceRobo = RetroClientRobo.getApiService();
        Call<ResultRobo> datalist= apiServiceRobo.getStatusRobo();

        datalist.enqueue(new Callback<ResultRobo>() {
            @Override
            public void onResponse(Call<ResultRobo> call, Response<ResultRobo> response) {
                if (response.isSuccessful()){
                    response.body().getStatus();
                    Log.e("RoboControl AA",response.body().getSetting().getStatus()+"");
                    Log.e("RoboControl STATUS",response.body().getStatus());

                    if (response.body().getSetting().getStatus().equals("1")) {
                        swOnOff.setChecked(true);
                        if (response.body().getSetting().getMode().equals("1")){
                            swMode.setChecked(false);
                        }
                        else swMode.setChecked(true);
                    }
                    else swOnOff.setChecked(false);


                }
            }

            @Override
            public void onFailure(Call<ResultRobo> call, Throwable t) {
                Log.e("onFailure","Failure");
            }
        });
    }





    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        int id= view.getId();

        switch (id){

            case R.id.btnGoHead:

                switch (motionEvent.getAction()){
                    case MotionEvent.ACTION_UP:
                        imGoAhead.setImageResource(R.drawable.up_button);
                        stop();
                        return true;
                    case MotionEvent.ACTION_DOWN:
                        imGoAhead.setImageResource(R.drawable.up_button_pres);
                        goAhead();
                        return true;
                }
                break;
            case R.id.btnGoLeft:
                switch (motionEvent.getAction()){
                    case MotionEvent.ACTION_UP:
                        imTurnLeft.setImageResource(R.drawable.left_button1);
                        stop();
                        return true;
                    case MotionEvent.ACTION_DOWN:
                        imTurnLeft.setImageResource(R.drawable.left_button_pres);
                        turnLeft();
                        return true;
                }
                break;
            case R.id.btnGoRight:
                switch (motionEvent.getAction()){
                    case MotionEvent.ACTION_UP:
                        imTurnRight.setImageResource(R.drawable.right_button);
                        stop();
                        return true;
                    case MotionEvent.ACTION_DOWN:
                        imTurnRight.setImageResource(R.drawable.right_button_pres);
                        turnRight();
                        return true;
                }
                break;
            case R.id.btnGoDown:
                switch (motionEvent.getAction()){
                    case MotionEvent.ACTION_UP:
                        imGoDown.setImageResource(R.drawable.down_button);
                        stop();
                        return true;
                    case MotionEvent.ACTION_DOWN:
                        imGoDown.setImageResource(R.drawable.down_button_pres);
                        goDown();
                        return true;
                }
                break;

        }
        return false;
    }

    private void turnOnRobo(){
        ApiServiceRobo apiServiceRobo = RetroClientRobo.getApiService();
        Call<ResultRobo> datalist= apiServiceRobo.turnOnRobo();

        datalist.enqueue(new Callback<ResultRobo>() {
            @Override
            public void onResponse(Call<ResultRobo> call, Response<ResultRobo> response) {
                if (response.isSuccessful()){
                    response.body().getStatus();
                    Log.e("RoboControl Resutl",response.body().getSetting().getStatus()+"");
                    Log.e("RoboControl STATUS",response.body().getStatus());
                    if (response.body().getSetting().getMode().equals("0")){
                        swMode.setChecked(true);
                    }else{
                        swMode.setChecked(false);
                    }
                }
            }

            @Override
            public void onFailure(Call<ResultRobo> call, Throwable t) {
                Log.e("onFailure","Failure");
            }
        });
    }

    private void turnAutoRobo(){
        ApiServiceRobo apiServiceRobo = RetroClientRobo.getApiService();
        Call<ResultRobo> datalist= apiServiceRobo.autoMode();

        datalist.enqueue(new Callback<ResultRobo>() {
            @Override
            public void onResponse(Call<ResultRobo> call, Response<ResultRobo> response) {
                if (response.isSuccessful()){
                    response.body().getStatus();
                    Log.e("RoboControl Resutl",response.body().getSetting().getStatus()+"");
                    Log.e("RoboControl STATUS",response.body().getStatus());
                }
            }

            @Override
            public void onFailure(Call<ResultRobo> call, Throwable t) {
                Log.e("onFailure","Failure");
            }
        });
    }

    private void turnManualRobo(){
        ApiServiceRobo apiServiceRobo = RetroClientRobo.getApiService();
        Call<ResultRobo> datalist= apiServiceRobo.manualMode();

        datalist.enqueue(new Callback<ResultRobo>() {
            @Override
            public void onResponse(Call<ResultRobo> call, Response<ResultRobo> response) {
                if (response.isSuccessful()){
                    response.body().getStatus();
                    Log.e("RoboControl Resutl",response.body().getSetting().getStatus()+"");
                    Log.e("RoboControl STATUS",response.body().getStatus());
                }
            }

            @Override
            public void onFailure(Call<ResultRobo> call, Throwable t) {
                Log.e("onFailure","Failure");
            }
        });
    }

    private void turnOffRobo(){
        ApiServiceRobo apiServiceRobo = RetroClientRobo.getApiService();
        Call<ResultRobo> datalist= apiServiceRobo.turnOffRobo();

        datalist.enqueue(new Callback<ResultRobo>() {
            @Override
            public void onResponse(Call<ResultRobo> call, Response<ResultRobo> response) {
                if (response.isSuccessful()){
                    response.body().getStatus();
//                    Log.e("RoboControl Resutl",response.body().getResult()+"");
                    Log.e("RoboControl STATUS",response.body().getStatus());
                }
            }

            @Override
            public void onFailure(Call<ResultRobo> call, Throwable t) {
                Log.e("onFailure","Failure");
            }
        });
    }

    private void goAhead(){
        ApiServiceRobo apiServiceRobo = RetroClientRobo.getApiService();
        Call<ResultRobo> datalist= apiServiceRobo.goAhead();

        datalist.enqueue(new Callback<ResultRobo>() {
            @Override
            public void onResponse(Call<ResultRobo> call, Response<ResultRobo> response) {
                if (response.isSuccessful()){
                    response.body().getStatus();
//                    Log.e("RoboControl Resutl",response.body().getResult()+"");
                    Log.e("RoboControl STATUS",response.body().getStatus());
                }
            }

            @Override
            public void onFailure(Call<ResultRobo> call, Throwable t) {
                Log.e("onFailure","Failure");
            }
        });
    }

    private void goDown(){
        ApiServiceRobo apiServiceRobo = RetroClientRobo.getApiService();
        Call<ResultRobo> datalist= apiServiceRobo.goDown();

        datalist.enqueue(new Callback<ResultRobo>() {
            @Override
            public void onResponse(Call<ResultRobo> call, Response<ResultRobo> response) {
                if (response.isSuccessful()){
                    response.body().getStatus();
//                    Log.e("RoboControl Resutl",response.body().getResult()+"");
                    Log.e("RoboControl STATUS",response.body().getStatus());
                }
            }

            @Override
            public void onFailure(Call<ResultRobo> call, Throwable t) {
                Log.e("onFailure","Failure");
            }
        });
    }

    private void turnLeft(){
        ApiServiceRobo apiServiceRobo = RetroClientRobo.getApiService();
        Call<ResultRobo> datalist= apiServiceRobo.turnLeft();

        datalist.enqueue(new Callback<ResultRobo>() {
            @Override
            public void onResponse(Call<ResultRobo> call, Response<ResultRobo> response) {
                if (response.isSuccessful()){
                    response.body().getStatus();
//                    Log.e("RoboControl Resutl",response.body().getResult()+"");
                    Log.e("RoboControl STATUS",response.body().getStatus());
                }
            }

            @Override
            public void onFailure(Call<ResultRobo> call, Throwable t) {
                Log.e("onFailure","Failure");
            }
        });
    }

    private void turnRight(){
        ApiServiceRobo apiServiceRobo = RetroClientRobo.getApiService();
        Call<ResultRobo> datalist= apiServiceRobo.turnRight();

        datalist.enqueue(new Callback<ResultRobo>() {
            @Override
            public void onResponse(Call<ResultRobo> call, Response<ResultRobo> response) {
                if (response.isSuccessful()){
                    response.body().getStatus();
//                    Log.e("RoboControl Resutl",response.body().getResult()+"");
                    Log.e("RoboControl STATUS",response.body().getStatus());
                }
            }

            @Override
            public void onFailure(Call<ResultRobo> call, Throwable t) {
                Log.e("onFailure","Failure");
            }
        });
    }

    private void stop(){
        ApiServiceRobo apiServiceRobo = RetroClientRobo.getApiService();
        Call<ResultRobo> datalist= apiServiceRobo.stop();

        datalist.enqueue(new Callback<ResultRobo>() {
            @Override
            public void onResponse(Call<ResultRobo> call, Response<ResultRobo> response) {
                if (response.isSuccessful()){
                    response.body().getStatus();
//                    Log.e("RoboControl Resutl",response.body().getResult()+"");
                    Log.e("RoboControl STATUS",response.body().getStatus());
                }
            }

            @Override
            public void onFailure(Call<ResultRobo> call, Throwable t) {
                Log.e("onFailure","Failure");
            }
        });
    }

}
