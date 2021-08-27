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
    static final String PREF_INTER_CITY= "0";

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

    public static void clearClientId(Context ctx)
    {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.clear(); //clear all stored data
        editor.commit();
    }
}
