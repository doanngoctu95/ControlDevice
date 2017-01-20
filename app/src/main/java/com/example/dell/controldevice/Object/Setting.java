package com.example.dell.controldevice.Object;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by dell on 18/01/2017.
 */
public class Setting {
    @SerializedName("mode")
    @Expose
    private String mode;

    @SerializedName("status")
    @Expose
    private String status;

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
