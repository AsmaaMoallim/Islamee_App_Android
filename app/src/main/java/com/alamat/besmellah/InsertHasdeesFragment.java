package com.alamat.besmellah;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.alamat.besmellah.databinding.FragmentInsertHasdeesBinding;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;


public class InsertHasdeesFragment extends Fragment {

    private FragmentInsertHasdeesBinding binding;
//    DbManager database;
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_insert_hasdees, container, false);
        view = binding.getRoot();

        binding.btnInsertHadeesInsertBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                processinsert(binding.etInsertHadeesHadeesTitle.getText().toString(), binding.etInsertHadeesHadeesContent.getText().toString());
            }
        });

        binding.btnInsertHadeesDeleteAllData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DbManager(getContext()).emptydb();
            }
        });


        binding.btnInsertHadeesReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                defualtinsertion();
            }
        });


        return view;


    }

    private void processinsert(String title, String content) {
        String res = new DbManager(getContext()).addrecord(title, content);

//        String res = database.addrecord(title, content);

        binding.etInsertHadeesHadeesTitle.setText("");
        binding.etInsertHadeesHadeesContent.setText("");

        Toast.makeText(getContext(), res, Toast.LENGTH_SHORT).show();
    }

        public static String[] listOfAhadethNames = {"الحديث الأول","الحديث الثاني","الحديث الـثـالـث","الحديث الـرابع","الحديث الخامس","الحديث السادس","الحديث السابع","الحديث الثامن","الحديث التاسع","الحديث العاشر",
            "الحديث الحادي عشر","الحديث الثانى عشر","الحديث الثالث عشر","الحد يث الرابع عشر","الحديث الخامس عشر","الحديث السادس عشر","الحديث السابع عشر","الحد يث الثامن عشر","الحد يث التاسع عشر","الحديث العشرون",
            "الحديث الحادي والعشرون","الحديث الثانى والعشرون","الحديث الثالث والعشرون","الحديث الرابع والعشرون","الحديث الخامس والعشرون","الحديث السادس والعشرون","الحديث السابع والعشرون","الحديث الثامن والعشرون","الحديث التاسع والعشرون","الحديث الثلاثون",
            "الحديث الحادي والثلاثون","الحديث الثانى والثلاثون","الحديث الثالث والثلاثون","الحديث الرابع والثلاثون","الحديث الخامس والثلاثون","الحديث السادس والثلاثون","الحديث السابع والثلاثون","الحديث الثامن والثلاثون","الحديث التاسع والثلاثون","الحديث الأربعون",
            "الحديث الحادي والأربعون","الحديث الثانى والأربعون","الحديث الثالث والأربعون","الحديث الرابع والأربعون","الحديث الخامس والأربعون","الحديث السادس والأربعون","الحديث السابع والأربعون","الحديث الثامن والأربعون","الحديث التاسع والأربعون","الحديث الخمسون",
    };

    private void defualtinsertion(){
        for (int i= 0;i<listOfAhadethNames.length;i++){
            String content = hadeescontent(i);
            new DbManager(getContext()).addrecord(listOfAhadethNames[i], content);
        }
    }

    private String hadeescontent(int i) {
        String text = "";




        return text;
    }
}