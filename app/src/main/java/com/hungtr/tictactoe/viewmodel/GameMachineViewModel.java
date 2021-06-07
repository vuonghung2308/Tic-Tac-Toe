package com.hungtr.tictactoe.viewmodel;

import android.os.Handler;
import android.util.Pair;

import androidx.databinding.ObservableArrayMap;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.hungtr.tictactoe.model.Cell;
import com.hungtr.tictactoe.model.Game;
import com.hungtr.tictactoe.model.Player;
import com.hungtr.tictactoe.utilities.StringUtils;

public class GameMachineViewModel extends ViewModel {

    public ObservableArrayMap<String, String> cells;
    private Game game;
    private String player1;
    private String player2;

    public void init(String player1) {
        this.player1 = player1;
        this.player2 = "Machine";
        game = new Game(player1, "Machine");
        cells = new ObservableArrayMap<>();
    }

    public void onClickedCellAt(int row, int column) {
        if (game.cells[row][column] == null) {
            game.cells[row][column] = new Cell(game.currentPlayer);
            cells.put(StringUtils.stringFromNumbers(row, column), game.currentPlayer.value);
            if (game.hasGameEnded())
                game.reset();
            else {
                new Handler().postDelayed(
                        () -> {
                            game.switchPlayer();
                            Pair<Integer, Integer> cell = random();
                            game.cells[cell.first][cell.second] = new Cell(game.currentPlayer);
                            cells.put(StringUtils.stringFromNumbers(cell.first, cell.second), game.currentPlayer.value);
                            game.switchPlayer();
                            if (game.hasGameEnded())
                                game.reset();
                        },
                        200
                );
            }
        }
    }

    public String getPlayer1() {
        return player1;
    }

    public String getPlayer2() {
        return player2;
    }


    private Pair<Integer, Integer> random() {
        int x = (int) (Math.random() * 3);
        int y = (int) (Math.random() * 3);
        if (game.cells[x][y] != null)
            return random();
        else return new Pair<>(x, y);
    }

    public LiveData<Player> getWinner() {
        return game.winner;
    }
}