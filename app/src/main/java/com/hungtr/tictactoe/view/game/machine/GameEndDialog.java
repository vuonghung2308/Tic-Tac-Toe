package com.hungtr.tictactoe.view.game.machine;


import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.hungtr.tictactoe.R;


public class GameEndDialog extends DialogFragment {

    private View rootView;
    private GameMachineActivity activity;
    private String winnerName;

    public static GameEndDialog newInstance(GameMachineActivity activity, String winnerName) {
        GameEndDialog dialog = new GameEndDialog();
        dialog.activity = activity;
        dialog.winnerName = winnerName;
        return dialog;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
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
