package com.aldomora.petagram.restAPI.deser;

import com.aldomora.petagram.Pojo.User;
import com.aldomora.petagram.restAPI.JsonKeys;
import com.aldomora.petagram.restAPI.model.SearchResponse;
import com.aldomora.petagram.restAPI.model.StatusResponse;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.ArrayList;


/**
 * Created by root on 24/06/16.
 */
public class StatusDeser implements JsonDeserializer<StatusResponse>{

    @Override
    public StatusResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Gson gson = new Gson();
        StatusResponse statusResponse = gson.fromJson(json, StatusResponse.class);
        JsonObject searchResponseData = json.getAsJsonObject().getAsJsonObject(JsonKeys.MEDIA_RESPONSE_ARRAY);
        statusResponse = deserSearchJson(searchResponseData);
        return statusResponse;
    }

    private StatusResponse deserSearchJson(JsonObject statusResponseData){
        StatusResponse status = new StatusResponse();

            String outgoing_status = statusResponseData.get(JsonKeys.OUTGOING_STATUS).getAsString();
            String incoming_status = statusResponseData.get(JsonKeys.INCOMING_STATUS).getAsString();

            status.setIncoming_status(incoming_status);
            status.setOutgoing_status(outgoing_status);
        return status;
    }
}
