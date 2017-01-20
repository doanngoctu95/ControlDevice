package com.example.dell.controldevice.Object;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


/**
 * Created by dell on 18/01/2017.
 */
public class ResultRobo {
    @SerializedName("setting")
    @Expose
    private Setting setting;

    @SerializedName("return")
    @Expose
    private String status;

    public Setting getSetting() {
        return setting;
    }

    public void setSetting(Setting setting) {
        this.setting = setting;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
