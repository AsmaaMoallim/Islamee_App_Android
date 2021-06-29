package com.alamat.besmellah;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alamat.besmellah.databinding.FragmentHadeesBinding;

import java.util.ArrayList;
import java.util.List;


public class HadeesFragment extends Fragment {

    private FragmentHadeesBinding binding;
    View view;

    HadeesRecyclerViewAdapter hadeesAdapter;
    RecyclerView.LayoutManager layoutManager;
    List<HadeesModel> hadeesModelList;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_hadees, container, false);
        createHadeesList();
        hadeesAdapter = new HadeesRecyclerViewAdapter(hadeesModelList);
        layoutManager = new GridLayoutManager(getActivity(), 2);
        binding.rvHadeesRecyclerView.setAdapter(hadeesAdapter);
        binding.rvHadeesRecyclerView.setLayoutManager(layoutManager);


        view = binding.getRoot();

        return view;
    }
    public static String[] listOfAhadethNames = {"الحديث الأول","الحديث الثاني","الحديث الـثـالـث","الحديث الـرابع","الحديث الخامس","الحديث السادس","الحديث السابع","الحديث الثامن","الحديث التاسع","الحديث العاشر",
            "الحديث الحادي عشر","الحديث الثانى عشر","الحديث الثالث عشر","الحد يث الرابع عشر","الحديث الخامس عشر","الحديث السادس عشر","الحديث السابع عشر","الحد يث الثامن عشر","الحد يث التاسع عشر","الحديث العشرون",
            "الحديث الحادي والعشرون","الحديث الثانى والعشرون","الحديث الثالث والعشرون","الحديث الرابع والعشرون","الحديث الخامس والعشرون","الحديث السادس والعشرون","الحديث السابع والعشرون","الحديث الثامن والعشرون","الحديث التاسع والعشرون","الحديث الثلاثون",
            "الحديث الحادي والثلاثون","الحديث الثانى والثلاثون","الحديث الثالث والثلاثون","الحديث الرابع والثلاثون","الحديث الخامس والثلاثون","الحديث السادس والثلاثون","الحديث السابع والثلاثون","الحديث الثامن والثلاثون","الحديث التاسع والثلاثون","الحديث الأربعون",
            "الحديث الحادي والأربعون","الحديث الثانى والأربعون","الحديث الثالث والأربعون","الحديث الرابع والأربعون","الحديث الخامس والأربعون","الحديث السادس والأربعون","الحديث السابع والأربعون","الحديث الثامن والأربعون","الحديث التاسع والأربعون","الحديث الخمسون",
    };
    public void createHadeesList(){
        hadeesModelList = new ArrayList<>();
        for (int i= 0;i<listOfAhadethNames.length;i++){
            hadeesModelList.add(new HadeesModel( listOfAhadethNames[i] ));
        }
    }
}
