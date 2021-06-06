package com.hungtr.tictactoe.model;

import com.hungtr.tictactoe.utilities.StringUtils;

public class Cell {

    public Player player;

    public Cell(Player player) {
        this.player = player;
    }

    public boolean isEmpty() {
        return player == null || StringUtils.isNullOrEmpty(player.value);
    }
}
