package com.sch.ibeauty.util;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.Set;

/**
 * Created by shichaohui on 16/4/14.
 * <p>
 * SharedPreferences工具类
 */
public class SharedPreferencesUtil {

    private static final String SHAREDPREFERENCE_NAME = "sharedpreferences_pztuan";

    private static SharedPreferences mSharedPreferences;

    private static SharedPreferences.Editor mEditor;

    private static Context mContext;

    private static class SingletonFactory {
        public static SharedPreferencesUtil instance = new SharedPreferencesUtil(mContext);
    }

    public static SharedPreferencesUtil getInstance(Context context) {
        mContext = context;
        return SingletonFactory.instance;
    }

    private SharedPreferencesUtil(Context context) {
        mSharedPreferences = context.getSharedPreferences(
                SHAREDPREFERENCE_NAME, Context.MODE_PRIVATE);
        mEditor = mSharedPreferences.edit();
    }

    public synchronized boolean putString(String key, String value) {
        mEditor.putString(key, value);
        return mEditor.commit();
    }

    public synchronized boolean putStringArrayList(String key, ArrayList<String> value) {

        for (int j = 0; j < value.size() - 1; j++) {
            if (value.get(value.size() - 1).equals(value.get(j))) {
                value.remove(j);
            }
        }
        mEditor.putInt("citySize", value.size());

        if (value.size() == 4) {
            mEditor.putString(key + 0, value.get(3));
            mEditor.putString(key + 1, value.get(0));
            mEditor.putString(key + 2, value.get(1));
        } else if (value.size() == 3) {
            mEditor.putString(key + 0, value.get(2));
            mEditor.putString(key + 1, value.get(0));
            mEditor.putString(key + 2, value.get(1));
        } else {
            for (int i = 0; i < value.size(); i++) {
                mEditor.putString(key + i, value.get(value.size() - 1 - i));
            }

        }
        return mEditor.commit();
    }

    public synchronized boolean putInt(String key, int value) {
        mEditor.putInt(key, value);
        return mEditor.commit();
    }

    public synchronized boolean putLong(String key, long value) {
        mEditor.putLong(key, value);
        return mEditor.commit();
    }

    public synchronized boolean putFloat(String key, float value) {
        mEditor.putFloat(key, value);
        return mEditor.commit();
    }

    public synchronized boolean putBoolean(String key, boolean value) {
        mEditor.putBoolean(key, value);
        return mEditor.commit();
    }

    public synchronized boolean putStringSet(String key, Set<String> value) {
        mEditor.putStringSet(key, value);
        return mEditor.commit();
    }

    public String getString(String key, String value) {
        return mSharedPreferences.getString(key, value);
    }

    public ArrayList<String> getStringArrayList(String key, int size) {
        ArrayList<String> al = new ArrayList<String>();
        int loop;
        if (size > 4)
            loop = 4;
        else
            loop = size;
        for (int i = 0; i < loop; i++) {
            String name = mSharedPreferences.getString(key + i, null);
            al.add(name);
        }
        return al;
    }

    public int getInt(String key, int value) {
        return mSharedPreferences.getInt(key, value);
    }

    public long getLong(String key, long value) {
        return mSharedPreferences.getLong(key, value);
    }

    public float getFloat(String key, float value) {
        return mSharedPreferences.getFloat(key, value);
    }

    public boolean getBoolean(String key, boolean value) {
        return mSharedPreferences.getBoolean(key, value);
    }

    public Set<String> getStringSet(String key, Set<String> value) {
        return mSharedPreferences.getStringSet(key, value);
    }

    public boolean remove(String key) {
        mEditor.remove(key);
        return mEditor.commit();
    }

    private static final String PREFERENCES_AUTO_LOGIN = "userAutoLogin";
    private static final String PREFERENCES_USER_NAME = "userName";
    private static final String PREFERENCES_USER_PASSWORD = "userPassword";

    public static String getKeyAutoLogin() {
        return PREFERENCES_AUTO_LOGIN;
    }

    public boolean isAutoLogin() {
        return mSharedPreferences.getBoolean(PREFERENCES_AUTO_LOGIN, false);
    }

    public String getUserName() {
        return mSharedPreferences.getString(PREFERENCES_USER_NAME, "");
    }

    public String getUserPwd() {
        return mSharedPreferences.getString(PREFERENCES_USER_PASSWORD, "");
    }

    public void saveLoginInfo(Boolean autoLogin, String userName, String userPassword) {
        assert (mEditor != null);
        mEditor.putBoolean(PREFERENCES_AUTO_LOGIN, autoLogin);
        mEditor.putString(PREFERENCES_USER_NAME, userName);
        mEditor.putString(PREFERENCES_USER_PASSWORD, userPassword);
        mEditor.commit();
    }

    public void saveLoginPassword(String userPassword) {
        mEditor.putString(PREFERENCES_USER_PASSWORD, userPassword);
        mEditor.commit();
    }

    public void saveLoginUserid(String userid) {
        mEditor.putString("userid", userid);
        mEditor.commit();
    }

    public void clearUserInfo() {
        assert (mEditor != null);
        mEditor.putBoolean(PREFERENCES_AUTO_LOGIN, false);
        mEditor.putString(PREFERENCES_USER_NAME, "");
        mEditor.putString(PREFERENCES_USER_PASSWORD, "");
        mEditor.putString("userid", "");
        mEditor.commit();
    }

}
