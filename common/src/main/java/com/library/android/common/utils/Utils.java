package com.library.android.common.utils;


import android.content.Intent;
import android.os.Parcelable;
import android.util.Log;

import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.reflect.TypeToken;
import com.library.android.common.baseconstants.BaseKeys;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public final class Utils {

    public static final String TAG = " :Utils: ";

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

    public static String getKeyValue(Intent intent, String key, String defaultValue) {
        return Utils.hasKeyValue(intent, key) ? intent.getStringExtra(key) : defaultValue;
    }

    public static boolean hasKeyValue(Intent intent, String key) {
        return intent != null && intent.hasExtra(key);
    }

    public static Intent setApi(Intent intent, String apiValue) {
        if (intent == null) {
            intent = new Intent();
        }
        intent.putExtra(BaseKeys.API, apiValue);
        return intent;
    }

    public static String getApi(Intent intent, String defaultValue) {
        return Utils.hasApi(intent) ? intent.getStringExtra(BaseKeys.API) : defaultValue;
    }

    public static boolean hasApi(Intent intent) {
        return intent != null && intent.hasExtra(BaseKeys.API);
    }

    public static String getSourceScreen(Intent intent, String defaultValue) {
        return Utils.hasSourceScreen(intent) ? intent.getStringExtra(BaseKeys.SOURCE_SCREEN) : defaultValue;
    }

    public static boolean hasSourceScreen(Intent intent) {
        return intent != null && intent.hasExtra(BaseKeys.SOURCE_SCREEN);
    }

    public static int getPosition(Intent intent, int defaultValue) {
        return Utils.hasPosition(intent) ? intent.getIntExtra(BaseKeys.POSITION, defaultValue) : defaultValue;
    }

    public static boolean hasPosition(Intent intent) {
        return intent != null && intent.hasExtra(BaseKeys.POSITION);
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

    public static String getString(Object object) {
        if (object != null) {
            Gson gson = new Gson();
            return gson.toJson(object);
        }
        return "{}";
    }

    public static JsonObject getJsonObject(Object javaObject) {
        Gson gson = new Gson();
        // JSON data structure
        JsonElement jsonElement = gson.toJsonTree(javaObject);
        JsonObject jsonObject = (JsonObject) jsonElement;
        // serialization to String
        String javaObjectString = jsonObject.toString();
        Log.d(TAG, "getJsonObject: ");
        return jsonObject;
    }

    public static <T> List<T> getFilteredList(List<T> list, Predicate<T> predicate) {
        if (Utils.isNotNullNotEmpty(list)) {
            Collection<T> filter = Collections2.filter(list, predicate);
            return new ArrayList<>(filter);
        }
        return list;
    }

    public static boolean isNotNullNotEmpty(List list) {
        return list != null && list.size() > 0;
    }

    public static boolean hasElement(List list, int position) {
        return Utils.isNotNullNotEmpty(list) && position != -1 && list.size() > position;
    }

    public static boolean hasMore(List mList, int paginationLimit) {
        if (Utils.isNotNullNotEmpty(mList) && paginationLimit != -1) {
            return mList.size() >= paginationLimit;
        }
        return false;
    }
}
