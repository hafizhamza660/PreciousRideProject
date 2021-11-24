package com.example.task.Session;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class SaveSharedPreference {
    static final String PREF_USER_ID= "0";
    static final String PREF_FIRST_NAME= "firstname";
    static final String PREF_LAST_NAME= "lastname";
    static final String PREF_MOBILE_NUMBER= "000";
    static final String PREF_EMAIL= "abc@gmail.com";
    static final String PREF_CITY= "name";
    static final String PREF_INTER_CITY= "city";
    static final String PREF_STATUS= "Nothing";
    static final String PREF_CHAT_ID= "chat_id";
    static final String PREF_RANGE= "1";
    static final String PREF_IMAGE_URL= "123.jpg";
    static final String PREF_COUNTRY_CODE= "2654848";
    static final String LOCATION_LAT= "9854";
    static final String LOCATION_LNG= "12635";

    static SharedPreferences getSharedPreferences(Context ctx) {
        return PreferenceManager.getDefaultSharedPreferences(ctx);
    }

    public static void setClientId(Context ctx, String clientId)
    {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(PREF_USER_ID, clientId);
        editor.commit();
    }

    public static void setFirstName(Context ctx,String first_name)
    {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(PREF_FIRST_NAME, first_name);
        editor.commit();
    }

    public static void setLastName(Context ctx,String last_name)
    {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(PREF_LAST_NAME, last_name);
        editor.commit();
    }

    public static void setEmail(Context ctx,String email)
    {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(PREF_EMAIL, email);
        editor.commit();
    }

    public static void setMobileNumber(Context ctx,String number)
    {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(PREF_MOBILE_NUMBER, number);
        editor.commit();
    }

    public static void setCity(Context ctx,String city)
    {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(PREF_CITY, city);
        editor.commit();
    }

    public static void setInterCity(Context ctx,String intercity)
    {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(PREF_INTER_CITY, intercity);
        editor.commit();
    }


    public static void setStatus(Context ctx,String status)
    {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(PREF_STATUS, status);
        editor.commit();
    }
    public static void setChatId(Context ctx,String chat_id)
    {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(PREF_CHAT_ID, chat_id);
        editor.commit();
    }

    public static void setRange(Context ctx,String range)
    {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(PREF_RANGE, range);
        editor.commit();
    }

    public static void setImageUrl(Context ctx,String imageurl)
    {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(PREF_IMAGE_URL, imageurl);
        editor.commit();
    }

    public static void setCountryCode(Context ctx,String countrycode)
    {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(PREF_COUNTRY_CODE, countrycode);
        editor.commit();
    }

    public static void setLocaionLat(Context ctx,String lat)
    {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(LOCATION_LAT, lat);
        editor.commit();
    }

    public static void setLocaitonLng(Context ctx,String lng)
    {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(LOCATION_LNG, lng);
        editor.commit();
    }

    public static String getClientId(Context ctx)
    {
        return getSharedPreferences(ctx).getString(PREF_USER_ID, "");
    }

    public static String getFirstName(Context ctx)
    {
        return getSharedPreferences(ctx).getString(PREF_FIRST_NAME, "");
    }

    public static String getLastName(Context ctx)
    {
        return getSharedPreferences(ctx).getString(PREF_LAST_NAME, "");
    }

    public static String getEmail(Context ctx)
    {
        return getSharedPreferences(ctx).getString(PREF_EMAIL, "");
    }

    public static String getMobileNumber(Context ctx)
    {
        return getSharedPreferences(ctx).getString(PREF_MOBILE_NUMBER, "");
    }

    public static String getCity(Context ctx)
    {
        return getSharedPreferences(ctx).getString(PREF_CITY, "");
    }

    public static String getInterCity(Context ctx)
    {
        return getSharedPreferences(ctx).getString(PREF_INTER_CITY, "");
    }
    public static String getStatus(Context ctx)
    {
        return getSharedPreferences(ctx).getString(PREF_STATUS, "");
    }

    public static String getChatId(Context ctx)
    {
        return getSharedPreferences(ctx).getString(PREF_CHAT_ID, "");
    }

    public static String getRange(Context ctx)
    {
        return getSharedPreferences(ctx).getString(PREF_RANGE, "");
    }

    public static String getImageUrl(Context ctx)
    {
        return getSharedPreferences(ctx).getString(PREF_IMAGE_URL, "");
    }

    public static String getCountryCode(Context ctx)
    {
        return getSharedPreferences(ctx).getString(PREF_COUNTRY_CODE, "");
    }

    public static String getLocationLat(Context ctx)
    {
        return getSharedPreferences(ctx).getString(LOCATION_LAT, "");
    }

    public static String getLocaitonLng(Context ctx)
    {
        return getSharedPreferences(ctx).getString(LOCATION_LNG, "");
    }

    public static void clearClientId(Context ctx)
    {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.clear(); //clear all stored data
        editor.commit();
    }
}
