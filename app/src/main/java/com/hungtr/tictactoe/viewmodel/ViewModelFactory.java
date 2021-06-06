package com.hungtr.tictactoe.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

public class ViewModelFactory implements ViewModelProvider.Factory {


    @Override
    public <T extends ViewModel> T create( Class<T> modelClass) {
        return ((T)new GameViewModel());
    }
}
