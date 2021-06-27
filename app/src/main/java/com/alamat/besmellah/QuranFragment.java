package com.alamat.besmellah;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alamat.besmellah.databinding.FragmentQuranBinding;

import java.util.ArrayList;
import java.util.List;

public class QuranFragment extends Fragment {

    private FragmentQuranBinding binding;
    View view;
    QuranRecyclerViewAdapter quranAdapter;
    RecyclerView.LayoutManager layoutManager;
    List<QuranModel> quranModelList;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_quran, container,false);
        createQuranList();
        quranAdapter = new QuranRecyclerViewAdapter(quranModelList);
        layoutManager = new LinearLayoutManager(getActivity());
        binding.rvQuranRecyclerView.setAdapter(quranAdapter);
        binding.rvQuranRecyclerView.setLayoutManager(layoutManager);



        view = binding.getRoot();
        return view;
    }

    public void createQuranList(){
        quranModelList = new ArrayList<>();
        for (int i= 0;i<50;i++){
            quranModelList.add(new QuranModel("السورة رقم "+i ));
        }
    }
}