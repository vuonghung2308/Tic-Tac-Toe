package com.hungtr.tictactoe.viewmodel;

import androidx.databinding.ObservableArrayMap;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.hungtr.tictactoe.model.Cell;
import com.hungtr.tictactoe.model.Game;
import com.hungtr.tictactoe.model.Player;
import com.hungtr.tictactoe.utilities.StringUtils;

public class GameViewModel extends ViewModel {

    public ObservableArrayMap<String, String> cells;
    private Game game;
    private String player1;
    private String player2;

    public void init(String player1, String player2) {
        this.player1 = player1;
        this.player2 = player2;
        game = new Game(player1, player2);
        cells = new ObservableArrayMap<>();
    }

    public void onClickedCellAt(int row, int column) {
        if (game.cells[row][column] == null) {
            game.cells[row][column] = new Cell(game.currentPlayer);
            cells.put(StringUtils.stringFromNumbers(row, column), game.currentPlayer.value);
            if (game.hasGameEnded())
                game.reset();
            else
                game.switchPlayer();
        }
    }

    public String getPlayer1() {
        return player1;
    }

    public String getPlayer2() {
        return player2;
    }

    public LiveData<Player> getWinner() {
        return game.winner;
    }
}