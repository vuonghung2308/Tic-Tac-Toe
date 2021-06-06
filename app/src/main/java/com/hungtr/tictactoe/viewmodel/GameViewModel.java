package com.hungtr.tictactoe.viewmodel;

import android.arch.lifecycle.ViewModel;

import androidx.databinding.ObservableArrayMap;
import androidx.lifecycle.LiveData;;

import com.hungtr.tictactoe.model.Cell;
import com.hungtr.tictactoe.model.Game;
import com.hungtr.tictactoe.model.Player;

import static com.hungtr.tictactoe.model.utilities.StringUtility.stringFromNumbers;

public class GameViewModel extends ViewModel {

    public ObservableArrayMap<String, String> cells;
    private Game game;

    public void init(String player1, String player2) {
        game = new Game(player1, player2);
        cells = new ObservableArrayMap<>();
    }

    public void onClickedCellAt(int row, int column) {
        if (game.cells[row][column] == null) {
            game.cells[row][column] = new Cell(game.currentPlayer);
            cells.put(stringFromNumbers(row, column), game.currentPlayer.value);
            if (game.hasGameEnded())
                game.reset();
            else
                game.switchPlayer();
        }
    }

    public LiveData<Player> getWinner() {
        return game.winner;
    }
}