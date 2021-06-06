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
        binding.viewPaper.setAdapter(new ViewPaperAdapter(this));
        binding.bottomNavigation.setOnNavigationItemSelectedListener(item -> {
            if (item.getItemId() == R.id.play) {
                binding.bottomNavigation.getMenu()
                        .getItem(1).setChecked(true);
                binding.bottomNavigation.getMenu()
                        .getItem(0).setChecked(false);
                binding.viewPaper.setCurrentItem(0);
            }
            else if (item.getItemId() == R.id.history) {
                binding.bottomNavigation.getMenu()
                        .getItem(0).setChecked(true);
                binding.bottomNavigation.getMenu()
                        .getItem(1).setChecked(false);
                binding.viewPaper.setCurrentItem(1);
            }
            return false;
        });
    }
}
