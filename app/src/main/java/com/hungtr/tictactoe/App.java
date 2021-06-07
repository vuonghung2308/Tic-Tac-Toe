package com.hungtr.tictactoe;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.hungtr.tictactoe.db.AppDatabase;

import java.util.concurrent.Executors;

public class App extends Application {
    @Override
    public void onCreate() {
        Log.d("TAG", "onCreate: ");
        super.onCreate();
        instance = this;
    }

    private static App instance;

    public static Context getContext() {
        return instance;
    }
}
