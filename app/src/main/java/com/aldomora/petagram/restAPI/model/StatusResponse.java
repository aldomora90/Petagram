package com.aldomora.petagram.restAPI.model;

import com.aldomora.petagram.Pojo.User;

import java.util.ArrayList;

/**
 * Created by root on 23/06/16.
 */
public class StatusResponse {
    String outgoing_status;
    String incoming_status;

    public StatusResponse() {
    }

    public String getOutgoing_status() {
        return outgoing_status;
    }

    public void setOutgoing_status(String outgoing_status) {
        this.outgoing_status = outgoing_status;
    }

    public String getIncoming_status() {
        return incoming_status;
    }

    public void setIncoming_status(String incoming_status) {
        this.incoming_status = incoming_status;
    }
}
