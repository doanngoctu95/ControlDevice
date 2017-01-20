package com.example.dell.controldevice;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Scene;
import android.transition.TransitionInflater;
import android.transition.TransitionManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import de.hdodenhof.circleimageview.CircleImageView;

public class OptionActivity extends AppCompatActivity implements View.OnClickListener {

    CircleImageView cIOption1,cIOption2;
    com.zuowei.circleimageview.CircleImageView cILightControl,cIRobotControl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option);

        initView();
    }

    private void initView() {
        ViewGroup sceneRoot= (ViewGroup) findViewById(R.id.sceneRoot);

        Scene scene= Scene.getSceneForLayout(sceneRoot,R.layout.layout_option,this);
        TransitionManager.go(scene, TransitionInflater.from(this).
                inflateTransition(R.transition.slide_and_changebounds_sequential));

        cILightControl= (com.zuowei.circleimageview.CircleImageView) sceneRoot.findViewById(R.id.lamp_control);
        cIRobotControl= (com.zuowei.circleimageview.CircleImageView) sceneRoot.findViewById(R.id.robot_control);
        cIOption1= (CircleImageView) sceneRoot.findViewById(R.id.option1);
        cIOption2= (CircleImageView) sceneRoot.findViewById(R.id.option2);

        cILightControl.setOnClickListener(this);
        cIRobotControl.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        int id= view.getId();
        switch (id){
            case R.id.lamp_control:
                Intent intent = new Intent(getApplicationContext(),LightControlActivity.class);
                startActivity(intent);
//                finish();
                break;
            case R.id.robot_control:
//                Toast.makeText(getApplicationContext(),"robo",Toast.LENGTH_SHORT).show();
                Intent intentRobo = new Intent(getApplicationContext(),RoboControlActivity.class);
                startActivity(intentRobo);
                break;
            case R.id.option1:

                break;
            case R.id.option2:

                break;

        }
    }
}
