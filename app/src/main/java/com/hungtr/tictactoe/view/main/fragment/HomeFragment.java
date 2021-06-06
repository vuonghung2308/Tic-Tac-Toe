package com.hungtr.tictactoe.view.main.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.hungtr.tictactoe.R;
import com.hungtr.tictactoe.databinding.FragmentHomeBinding;
import com.hungtr.tictactoe.view.game.machine.GameMachineActivity;
import com.hungtr.tictactoe.view.game.people.GameActivity;
import com.hungtr.tictactoe.viewmodel.GameMachineViewModel;

public class HomeFragment extends Fragment {
    private FragmentHomeBinding binding;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_home,
                container,
                false
        );
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.btnPeople.setOnClickListener(v -> {
            requireActivity().startActivity(
                    new Intent(requireContext(), GameActivity.class)
            );
        });
        binding.btnMachine.setOnClickListener(v -> {
            requireActivity().startActivity(
                    new Intent(requireContext(), GameMachineActivity.class)
            );
        });
    }
}
