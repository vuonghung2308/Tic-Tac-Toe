package com.hungtr.tictactoe.view.main;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.hungtr.tictactoe.view.main.fragment.HistoryFragment;
import com.hungtr.tictactoe.view.main.fragment.HomeFragment;

public class ViewPaperAdapter extends FragmentStateAdapter {
    public ViewPaperAdapter(FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (position == 0) {
            return new HomeFragment();
        } else return new HistoryFragment();
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
