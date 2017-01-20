package com.example.dell.controldevice.Object;

/**
 * Created by dell on 18/01/2017.
 */
public class Utils {
    public static final String GET_STATUS="?GET_STATUS";
    public static final String LED_ONE_ON="?LED_ONE=ON";
    public static final String LED_TWO_ON="?LED_TWO=ON";
    public static final String LED_THREE_ON="?LED_THREE=ON";
    public static final String LED_ONE_OFF="?LED_ONE=OFF";
    public static final String LED_TWO_OFF="?LED_TWO=OFF";
    public static final String LED_THREE_OFF="?LED_THREE=OFF";
    public static final String LED_ALL_OFF="?LED_ALL=OFF";
    public static final String LED_ALL_ON="?LED_ALL=ON";
    public static final String ROOT_URL_LED = "http://118.70.80.24:10000/";

//    + Switch mode:
//    manual mode: 10.0.2.12\Mode\1
//    auto mode: 10.0.2.12\Mode\0
//    + On\off:
//    turn on: 10.0.2.12\Status\1
//    turn off: 10.0.2.12\Status\0
//    + Move:
//    tiến: turn on: 10.0.2.12\Moving\1
//    lùi: turn on: 10.0.2.12\Moving\2
//    trái: turn on: 10.0.2.12\Moving\3
//    phải: turn on: 10.0.2.12\Moving\4
//    stop: turn on: 10.0.2.12\Moving\5

    public static final String ROOT_URL_ROBO = "http://10.0.2.12:5010/";
    public static final String MANUAL_MODE="Mode/1";
    public static final String AUTO_MODE="Mode/0";
    public static final String ON_ROBO="Status/1";
    public static final String OFF_ROBO="Status/0";
    public static final String GO_AHEAD="Moving/1";
    public static final String GO_DOWN="Moving/2";
    public static final String TURN_LEFT="Moving/3";
    public static final String TURN_RIGHT="Moving/4";
    public static final String STOP="Moving/5";
    public static final String GET_STATUS_ROBO="Setting";




}
