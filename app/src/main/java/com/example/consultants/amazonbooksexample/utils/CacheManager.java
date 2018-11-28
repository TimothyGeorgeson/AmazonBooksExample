package com.example.consultants.amazonbooksexample.utils;

import android.content.Context;
import android.content.SharedPreferences;

//was just following along in class and made this separate util class for future reference
//I had already built the caching into the LocalDataSource in the HW last night, so this is unused
public class CacheManager {
    Context context;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    public static final String TIME = "time";
    public static final long TIME_LIMIT = 20000;

    public CacheManager(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences("mySharedPref", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void saveTime(long time) {
        editor.putLong(TIME, time);
        editor.commit();
    }

    public long getLastTime() {
        return sharedPreferences.getLong(TIME, 0);
    }

    public boolean isCacheDirty() {
        long currentTime = System.currentTimeMillis();
        long timeDiff = currentTime - getLastTime();

        if(timeDiff > TIME_LIMIT) return true;
        else return false;
    }
}
