package com.hungtr.tictactoe.view.game.people;


import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.hungtr.tictactoe.R;
import com.hungtr.tictactoe.db.AppDatabase;
import com.hungtr.tictactoe.db.History;

import java.util.concurrent.Executors;


public class GameEndDialog extends DialogFragment {

    private View rootView;
    private GameActivity activity;
    private String winnerName;

    public static GameEndDialog newInstance(GameActivity activity, String winnerName) {
        GameEndDialog dialog = new GameEndDialog();
        dialog.activity = activity;
        dialog.winnerName = winnerName;
        return dialog;
    }

    private void update() {
        History history = new History();
        history.winner = winnerName;
        history.player1 = activity.player1();
        history.player2 = activity.player2();
        history.type = "People";
        Thread thread = new Thread(() -> {
            AppDatabase.getInstance()
                    .getHistoryDao()
                    .insertAll(history);
        });
        thread.start();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        update();
        initViews();
        AlertDialog alertDialog = new AlertDialog.Builder(getContext())
                .setView(rootView)
                .setCancelable(false)
                .setPositiveButton(R.string.done, ((dialog, which) -> onNewGame()))
                .create();
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.setCancelable(false);
        return alertDialog;
    }

    private void initViews() {
        rootView = LayoutInflater.from(getContext())
                .inflate(R.layout.game_end_dialog, null, false);
        ((TextView) rootView.findViewById(R.id.tv_winner)).setText(winnerName);
    }

    private void onNewGame() {
        dismiss();
        activity.promptForPlayers();
    }
}
