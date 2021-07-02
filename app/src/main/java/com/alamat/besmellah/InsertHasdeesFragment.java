package com.alamat.besmellah;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alamat.besmellah.databinding.FragmentInsertHasdeesBinding;


public class InsertHasdeesFragment extends Fragment {

    private FragmentInsertHasdeesBinding binding;
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_insert_hasdees, container, false);
        view = binding.getRoot();

        return view;
    }
}