package com.hungtr.tictactoe;

import android.app.Application;
import android.content.Context;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    private static App instance;

    public static Context getContext() {
        return instance;
    }
}
