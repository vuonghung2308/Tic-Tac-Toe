package com.hungtr.tictactoe.view.main.fragment;


import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.hungtr.tictactoe.R;
import com.hungtr.tictactoe.databinding.FragmentHistoryBinding;
import com.hungtr.tictactoe.db.AppDatabase;
import com.hungtr.tictactoe.db.History;

import java.util.ArrayList;
import java.util.List;

public class HistoryFragment extends Fragment {
    private final HistoryAdapter adapter = new HistoryAdapter();
    private final Handler handler = new Handler(Looper.getMainLooper());
    private FragmentHistoryBinding binding;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_history,
                container,
                false
        );
        binding.history.setAdapter(adapter);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.btnClear.setOnClickListener((v) -> {
            clear();
            adapter.refresh(new ArrayList<>());
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        getList();
    }

    private void getList() {
        Thread thread = new Thread(() -> {
            List<History> list = AppDatabase.getInstance()
                    .getHistoryDao()
                    .getAll();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            updateList(list);
        });
        thread.start();
    }

    private void clear() {
        Thread thread = new Thread(() -> {
            AppDatabase.getInstance()
                    .getHistoryDao()
                    .deleteAll();
        });
        thread.start();
    }

    private void updateList(List<History> list) {
        handler.post(() -> adapter.refresh(list));
    }
}
