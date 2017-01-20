package com.example.dell.controldevice.Object;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Example {

    @SerializedName("cmd")
    @Expose
    private Integer cmd;
    @SerializedName("status")
    @Expose
    private List<Integer> status = null;

    public Integer getCmd() {
        return cmd;
    }

    public void setCmd(Integer cmd) {
        this.cmd = cmd;
    }

    public List<Integer> getStatus() {
        return status;
    }

    public void setStatus(List<Integer> status) {
        this.status = status;
    }

}