package com.hungtr.tictactoe.view.game.machine;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import com.hungtr.tictactoe.R;
import com.hungtr.tictactoe.databinding.ActivityGameMachineBinding;
import com.hungtr.tictactoe.model.Player;
import com.hungtr.tictactoe.utilities.StringUtils;
import com.hungtr.tictactoe.viewmodel.GameMachineViewModel;

public class GameMachineActivity extends AppCompatActivity {

    private static final String GAME_BEGIN_DIALOG_TAG = "game_dialog_tag";
    private static final String GAME_END_DIALOG_TAG = "game_end_dialog_tag";
    private static final String NO_WINNER = "No one";
    private GameMachineViewModel gameViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        promptForPlayers();
    }

    public void promptForPlayers() {
        GameBeginDialog dialog = GameBeginDialog.newInstance(this);
        dialog.setCancelable(false);
        dialog.show(getSupportFragmentManager(), GAME_BEGIN_DIALOG_TAG);
    }

    public void onPlayersSet(String player1) {
        initDataBinding(player1);
    }

    private void initDataBinding(String player1) {
        ActivityGameMachineBinding activityGameBinding = DataBindingUtil.setContentView(this, R.layout.activity_game_machine);
        gameViewModel = ViewModelProviders.of(this).get(GameMachineViewModel.class);
        gameViewModel.init(player1);
        activityGameBinding.setGameViewModel(gameViewModel);
        setUpOnGameEndListener();
    }

    private void setUpOnGameEndListener() {
        gameViewModel.getWinner().observe(this, this::onGameWinnerChanged);
    }

    public void onGameWinnerChanged(Player winner) {
        String winnerName = winner == null || StringUtils.isNullOrEmpty(winner.name) ? NO_WINNER : winner.name;
        GameEndDialog dialog = GameEndDialog.newInstance(this, winnerName);
        dialog.setCancelable(false);
        dialog.show(getSupportFragmentManager(), GAME_END_DIALOG_TAG);
    }

    public String player1() {
        return gameViewModel.getPlayer1();
    }

    public String player2() {
        return gameViewModel.getPlayer2();
    }
}
