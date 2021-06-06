package com.hungtr.tictactoe.db;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "history")
public class History {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String player1;
    public String player2;
    public String type;
}
