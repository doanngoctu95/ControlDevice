package com.example.dell.controldevice;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dell.controldevice.Object.Example;
import com.example.dell.controldevice.Retrofit.ApiServiceLed;
import com.example.dell.controldevice.Retrofit.RetroClientLed;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by dell on 10/01/2017.
 */
public class LightControlActivity extends AppCompatActivity implements View.OnClickListener {
    private Switch stateLight;
    private TextView tvStatus;
    private CircleImageView lamp1,lamp2,lamp3;

    private boolean stateL1=true;
    private boolean stateL2=true;
    private boolean stateL3=true;
    private boolean stateAll=true;

    private ArrayList<Integer> arrayList= new ArrayList<>();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.light_control_activity);
        initView();

    }

    private void initView() {
        stateLight= (Switch) findViewById(R.id.switchButton);
//        stateLight.setChecked(true);
        tvStatus= (TextView) findViewById(R.id.statusSwitch);
        lamp1= (CircleImageView) findViewById(R.id.lamp1);
        lamp2= (CircleImageView) findViewById(R.id.lamp2);
        lamp3= (CircleImageView) findViewById(R.id.lamp3);

        lamp1.setOnClickListener(this);
        lamp2.setOnClickListener(this);
        lamp3.setOnClickListener(this);

        getLampSatus();
        setFirstStateOfLamp();

        //setCkeckedAllLamp
        if (stateL1==true&stateL2==true&stateL3==true){
            stateAll=true;
            stateLight.setChecked(true);
            tvStatus.setText(R.string.onStatus);
            turnOnAllLamp(lamp1,lamp2,lamp3);
        }
        else {
            stateAll=false;
            stateLight.setChecked(false);
            tvStatus.setText(R.string.offStatus);
            turnOOffAllLamp(lamp1,lamp2,lamp3);
        }

        stateLight.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked==true&stateAll==true){
//                    stateL1=true;
//                    stateL2=true;
//                    stateL3=true;
                    Toast.makeText(getApplicationContext(),"On",Toast.LENGTH_SHORT).show();
                    tvStatus.setText(R.string.onStatus);
                    turnOnAllLamp(lamp1,lamp2,lamp3);
                    setAllLightOn();

                }
                else if(isChecked==true&stateAll==false){
                    stateAll=true;
//                    stateL1=true;
//                    stateL3=true;
//                    stateL2=true;
                    Toast.makeText(getApplicationContext(),"Off",Toast.LENGTH_SHORT).show();
                    tvStatus.setText(R.string.offStatus);
                    turnOnAllLamp(lamp1,lamp2,lamp3);
                    setAllLightOn();
                }
                else if (isChecked==false&stateAll==true){
//                    stateL1=false;
//                    stateL3=false;
//                    stateL2=false;
                    setAllLightOff();
                    tvStatus.setText(R.string.offStatus);
                    turnOOffAllLamp(lamp1,lamp2,lamp3);

                }
                else {
                    tvStatus.setText(R.string.offStatus);
//
//                    setAllLightOff();

                }
            }
        });
    }


    private void turnOnAllLamp(CircleImageView lamp1,CircleImageView lam2,CircleImageView lamp3){
        lamp1.setImageDrawable(getResources().getDrawable(R.drawable.light_on));
        lamp2.setImageDrawable(getResources().getDrawable(R.drawable.light_on));
        lamp3.setImageDrawable(getResources().getDrawable(R.drawable.light_on));
        stateL1=true;
        stateL2=true;
        stateL3=true;

    }
    private void turnOOffAllLamp(CircleImageView lamp1,CircleImageView lam2,CircleImageView lamp3){
        lamp1.setImageDrawable(getResources().getDrawable(R.drawable.light_off));
        lamp2.setImageDrawable(getResources().getDrawable(R.drawable.light_off));
        lamp3.setImageDrawable(getResources().getDrawable(R.drawable.light_off));
        stateL1=false;
        stateL2=false;
        stateL3=false;

    }

    private void getLampSatus(){
        ApiServiceLed apiServiceLed = RetroClientLed.getApiService();
        Call<Example> datalist= apiServiceLed.getStatusJSON();
        datalist.enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {
                if (response.isSuccessful()){
                    arrayList= (ArrayList<Integer>) response.body().getStatus();
                    for (int i=0;i<arrayList.size();i++) {
                        Log.e("status", arrayList.get(i) + "");
                    }
                    setStateOfLamp(arrayList);
                }
            }
            @Override
            public void onFailure(Call<Example> call, Throwable t) {
                Log.e("onFailure",t.toString());
            }
        });
    }

    private void setAllLightOn(){
        ApiServiceLed apiServiceLed = RetroClientLed.getApiService();
        Call<Example> datalist= apiServiceLed.setAllOn();
        Log.e("logon","logOn");

        datalist.enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {
                if (response.isSuccessful()){
                    arrayList= (ArrayList<Integer>) response.body().getStatus();
                    setStateOfLamp(arrayList);
                }
            }
            @Override
            public void onFailure(Call<Example> call, Throwable t) {
                Log.e("onFailure",t.toString());
            }
        });
    }

    private void setAllLightOff(){
        ApiServiceLed apiServiceLed = RetroClientLed.getApiService();
        Call<Example> datalist= apiServiceLed.setAllOff();

        datalist.enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {
                if (response.isSuccessful()){
                    arrayList= (ArrayList<Integer>) response.body().getStatus();
                    setStateOfLamp(arrayList);
                }
            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {
                Log.e("onFailure",t.toString());
            }
        });
    }

    private void turnOnLamp1(){
        ApiServiceLed apiServiceLed = RetroClientLed.getApiService();
        Call<Example> datalist= apiServiceLed.setOneOn();

        datalist.enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {
                if (response.isSuccessful()){
                    arrayList= (ArrayList<Integer>) response.body().getStatus();

                    setStateOfLamp(arrayList);
                }
            }
            @Override
            public void onFailure(Call<Example> call, Throwable t) {
                Log.e("onFailure","Failure");
            }
        });
    }

    private void turnOffLamp1(){
        ApiServiceLed apiServiceLed = RetroClientLed.getApiService();
        Call<Example> datalist= apiServiceLed.setOneOff();

        datalist.enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {
                if (response.isSuccessful()){
                    arrayList= (ArrayList<Integer>) response.body().getStatus();
                    for (int i=0;i<arrayList.size();i++){
                        Log.e("status",arrayList.get(i)+"");
                    }

                    setStateOfLamp(arrayList);
                }
            }
            @Override
            public void onFailure(Call<Example> call, Throwable t) {
                Log.e("onFailure","Failure");
            }
        });
    }

    private void turnOnLamp2(){
        ApiServiceLed apiServiceLed = RetroClientLed.getApiService();
        Call<Example> datalist= apiServiceLed.setTwoOn();

        datalist.enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {
                if (response.isSuccessful()){
                    arrayList= (ArrayList<Integer>) response.body().getStatus();
                    for (int i=0;i<arrayList.size();i++){
                        Log.e("status",arrayList.get(i)+"");
                    }
                    setStateOfLamp(arrayList);
                }
            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {
                Log.e("onFailure","Failure");
            }
        });
    }

    private void turnOffLamp2(){
        ApiServiceLed apiServiceLed = RetroClientLed.getApiService();
        Call<Example> datalist= apiServiceLed.setTwoOff();

        datalist.enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {
                if (response.isSuccessful()){
                    arrayList= (ArrayList<Integer>) response.body().getStatus();
                    for (int i=0;i<arrayList.size();i++){
                        Log.e("status",arrayList.get(i)+"");
                    }

                    setStateOfLamp(arrayList);
                }
            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {
                Log.e("onFailure","Failure");
            }
        });
    }

    private void turnOnLamp3(){
        ApiServiceLed apiServiceLed = RetroClientLed.getApiService();
        Call<Example> datalist= apiServiceLed.setThreeOn();

        datalist.enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {
                if (response.isSuccessful()){
                    arrayList= (ArrayList<Integer>) response.body().getStatus();
                    for (int i=0;i<arrayList.size();i++){
                        Log.e("status",arrayList.get(i)+"");
                    }

                    setStateOfLamp(arrayList);
                }
            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {
                Log.e("onFailure","Failure");
            }
        });
    }

    private void turnOffLamp3(){
        ApiServiceLed apiServiceLed = RetroClientLed.getApiService();
        Call<Example> datalist= apiServiceLed.setThreeOff();

        datalist.enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {
                if (response.isSuccessful()){
                    arrayList= (ArrayList<Integer>) response.body().getStatus();
                    for (int i=0;i<arrayList.size();i++){
                        Log.e("status",arrayList.get(i)+"");
                    }

                    setStateOfLamp(arrayList);
                }
            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {
                Log.e("onFailure","Failure");
            }
        });
    }

    private void setFirstStateOfLamp(){
        if (stateL1){
            lamp1.setImageDrawable(getResources().getDrawable(R.drawable.light_on));
        }
        else {
            lamp1.setImageDrawable(getResources().getDrawable(R.drawable.light_off));
        }

        if (stateL2){
            lamp2.setImageDrawable(getResources().getDrawable(R.drawable.light_on));
        }
        else {
            lamp2.setImageDrawable(getResources().getDrawable(R.drawable.light_off));
        }

        if (stateL3){
            lamp3.setImageDrawable(getResources().getDrawable(R.drawable.light_on));
        }
        else {
            lamp3.setImageDrawable(getResources().getDrawable(R.drawable.light_off));
        }

    }

    private void setStateOfLamp(ArrayList<Integer> array){
        if (array.get(0)==0){
            stateL1=true;
        }
        else stateL1=false;

        if ((array.get(1)==0)){
            stateL2=true;
        }
        else stateL2=false;

        if (array.get(2)==0){
            stateL3=true;
        }
        else stateL3=false;

//        if(stateL1==true&stateL2==true&stateL3==true){
//            stateAll=true;
//            stateLight.setChecked(true);
//        }
//        else {
//            stateAll=false;
//            stateLight.setChecked(false);
//        }
    }


    private boolean ckeckAllOnOff(){
        if (stateL1&stateL2&stateL3){
            stateAll=true;
            stateLight.setChecked(true);
            return true;

        }
        else {
            stateAll=false;
            if (stateLight.isChecked()){
                stateLight.setChecked(false);
            }

            return false;
        }
    }


    @Override
    public void onClick(View view) {
        int id= view.getId();
        switch (id) {
            case R.id.lamp1:
                if (stateL1) {

                    lamp1.setImageDrawable(getResources().getDrawable(R.drawable.light_off));
                    stateL1=false;
                    turnOffLamp1();
//                    stateAll=ckeckAllOnOff();

                }
                else {

                    lamp1.setImageDrawable(getResources().getDrawable(R.drawable.light_on));
                    stateL1=true;
                    turnOnLamp1();

                }
                stateAll=ckeckAllOnOff();
                break;
            case R.id.lamp2:
                if (stateL2) {

                    lamp2.setImageDrawable(getResources().getDrawable(R.drawable.light_off));
                    stateL2=false;
                    turnOffLamp2();
//                    stateAll=ckeckAllOnOff();
                }
                else {

                    lamp2.setImageDrawable(getResources().getDrawable(R.drawable.light_on));
                    stateL2=true;
                    turnOnLamp2();

                }
                stateAll=ckeckAllOnOff();
                break;
            case R.id.lamp3:
                if (stateL3) {

                    lamp3.setImageDrawable(getResources().getDrawable(R.drawable.light_off));
                    stateL3=false;
                    turnOffLamp3();
//                    stateAll=ckeckAllOnOff();
                }
                else {

                    lamp3.setImageDrawable(getResources().getDrawable(R.drawable.light_on));
                    stateL3=true;
                    turnOnLamp3();
//                    stateAll=ckeckAllOnOff();
                }
                stateAll=ckeckAllOnOff();
                break;

        }

    }
}
