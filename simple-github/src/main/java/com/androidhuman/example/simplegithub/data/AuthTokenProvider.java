package com.androidhuman.example.simplegithub.data;

import android.content.Context;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public final class AuthTokenProvider {

    private static final String KEY_AUTH_TOKEN = "auth_token";

    private Context context;

    public AuthTokenProvider(@NonNull Context context) {
        this.context = context;
    }

    public void updateToken(@NonNull String token) {
        PreferenceManager.getDefaultSharedPreferences(context).edit()
                .putString(KEY_AUTH_TOKEN, token)
                .apply();
    }

    @Nullable
    public String getToken() {
        return PreferenceManager.getDefaultSharedPreferences(context)
                .getString(KEY_AUTH_TOKEN, null);
    }

}
