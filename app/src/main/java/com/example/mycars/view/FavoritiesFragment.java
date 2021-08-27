package com.example.mycars.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.mycars.databinding.FragmentFavoritiesBinding;
import com.example.mycars.viewmodel.FavoritiesViewModel;

public class FavoritiesFragment extends Fragment {

    private FavoritiesViewModel favoritiesViewModel;
    private FragmentFavoritiesBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        favoritiesViewModel = new ViewModelProvider(this).get(FavoritiesViewModel.class);

        binding = FragmentFavoritiesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }
}