package com.hungtr.tictactoe.db;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.hungtr.tictactoe.App;

@Database(entities = {History.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract HistoryDao getHistoryDao();

    private static AppDatabase instance = null;

    public static AppDatabase getInstance() {
        if (instance == null) {
            instance = Room.databaseBuilder(
                    App.getContext(),
                    AppDatabase.class,
                    "app_database"
            ).build();
        }
        return instance;
    }
}
