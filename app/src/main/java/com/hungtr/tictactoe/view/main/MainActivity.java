package com.hungtr.tictactoe.view.main;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.hungtr.tictactoe.R;
import com.hungtr.tictactoe.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.bottomNavigation.setOnNavigationItemSelectedListener(item -> {
            binding.viewPaper.setCurrentItem(item.getItemId());
            return false;
        });
    }
}
