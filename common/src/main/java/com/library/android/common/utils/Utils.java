package com.library.android.common.utils;


import android.content.Intent;
import android.os.Parcelable;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.reflect.TypeToken;
import com.library.android.common.baseconstants.BaseKeys;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public final class Utils {

    public static Object getType(LinkedTreeMap linkedTreeMap, Object newEmptyObject, Class classNameOfObject) {
        Gson gson = new Gson();
        String dataMap = Utils.getStringFromLinkedTreeMap(linkedTreeMap);
        Type type = TypeToken.getParameterized(classNameOfObject, classNameOfObject).getType();
        gson.toJson(newEmptyObject, type);
        newEmptyObject = gson.fromJson(dataMap, type);
        return newEmptyObject;
    }

    public static String getStringFromLinkedTreeMap(LinkedTreeMap linkedTreeMap) {
        Gson gson = new Gson();
        return gson.toJson(linkedTreeMap);
    }

    public static JsonObject getJsonObject(LinkedTreeMap linkedTreeMap) {
        Gson gson = new Gson();
        return gson.toJsonTree(linkedTreeMap).getAsJsonObject();
    }

    public static List getTypeList(List<LinkedTreeMap> linkedTreeMapList, Class classNameOfObject) {
        Gson gson = new Gson();
        String listMap = gson.toJson(linkedTreeMapList);
        List dataList;
        Type type = TypeToken.getParameterized(ArrayList.class, classNameOfObject).getType();
        dataList = gson.fromJson(listMap, type);
        return dataList;
    }

    public static boolean isNotNullNotEmpty(List list) {
        return list != null && list.size() > 0;
    }

    public static Intent setParcel(Intent intent, Object parcelableObject) {
        if (intent == null) {
            intent = new Intent();
        }
        intent.putExtra(BaseKeys.PARCEL, (Parcelable) parcelableObject);
        return intent;
    }

    public static Object getParcel(Intent intent) {
        if (intent != null && intent.hasExtra(BaseKeys.PARCEL) && intent.getParcelableExtra(BaseKeys.PARCEL) != null) {
            return intent.getParcelableExtra(BaseKeys.PARCEL);
        }
        return null;
    }

    public static boolean hasParcel(Intent intent) {
        return intent != null && intent.hasExtra(BaseKeys.PARCEL) && intent.getParcelableExtra(BaseKeys.PARCEL) != null;
    }

    public static JsonObject getJsonObject(Object object, Class objectClass) {
        /*https://stackoverflow.com/questions/47193364/using-gson-convert-java-object-into-jsonobject*/
        Gson gson = new Gson();
        // JSON data structure
        Type type = TypeToken.getParameterized(JsonObject.class, objectClass).getType();
        JsonElement jsonElement = gson.toJsonTree(object, type);
        return (JsonObject) jsonElement;
    }

    public static JsonElement getJsonElement(Object object) {
        Gson gson = new Gson();
        String javaObjectString = gson.toJson(object);
        /*JsonObject jsonObject = (JsonObject) jsonElement;
        javaObjectString = gson.toJson(jsonObject);*/
        return gson.toJsonTree(javaObjectString, object.getClass());
    }

    public static String getJson(Object object) {
        if (object != null) {
            Gson gson = new Gson();
            return gson.toJson(object);
        }
        return "{}";
    }
}
