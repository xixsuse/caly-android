package io.caly.calyandroid.Model.Deserializer;

import android.util.Log;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

import io.caly.calyandroid.Model.EventModel;

/**
 * Copyright 2017 JSpiner. All rights reserved.
 *
 * @author jspiner (jspiner@naver.com)
 * @project CalyAndroid
 * @since 17. 2. 21
 */

public class EventInstanceCreator implements JsonDeserializer<EventModel> {

    //로그에 쓰일 tag
    private static final String TAG = EventInstanceCreator.class.getSimpleName();

    @Override
    public EventModel deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Log.d(TAG, "deserialize");

        EventModel eventModel = new GsonBuilder()
                    .setDateFormat("yyyy-MM-dd HH:mm:ss")
                    .create()
                .fromJson(json, typeOfT);

        eventModel.setData();

        return eventModel;
    }


}
