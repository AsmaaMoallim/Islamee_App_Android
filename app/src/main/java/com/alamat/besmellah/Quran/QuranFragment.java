package com.alamat.besmellah.Quran;

import android.content.Intent;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alamat.besmellah.R;
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

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_quran, container, false);
        createQuranList();
        quranAdapter = new QuranRecyclerViewAdapter(quranModelList);
        layoutManager = new LinearLayoutManager(getActivity());
        binding.rvQuranRecyclerView.setAdapter(quranAdapter);
        binding.rvQuranRecyclerView.setLayoutManager(layoutManager);


        quranAdapter.setOnItemClickListener(new QuranRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int pos, QuranModel quranModel) {
                Intent intent = new Intent(getActivity(), SuraActivity.class);
                String surasNames = quranModel.quranText;
                int indexOfSurasNames = pos;
                intent.putExtra("surasNames", surasNames);
                intent.putExtra("indexOfSurasNames", indexOfSurasNames);
                startActivity(intent);

//                Toast.makeText(getActivity(), "onItemClick" + quranModel, Toast.LENGTH_SHORT).show();
            }
        });
        view = binding.getRoot();
        return view;
    }

    public static String[] listOfSewarNames = {"الفاتحة", "البقرة", "آل عِمْران", "النسَاء", "المائدة", "الأنعَام", "الأعراف", "الأنفال", "التوبَة", "يونس", "هُود"
            , "یوسُف", "الرّعد", "إبراهيم", "الحِجر", "النحل", "الإسرَاء", "الكهف", "مَریم", "طه", "الأنبیَاء", "الحَج", "المؤمِنون"
            , "النّور", "الفُرقان", "الشُّعَراء", "النّمل", "القصص", "العنكبوت", "الرّوم", "لقمان", "السجدة", "الأحزاب", "سبأ"
            , "فاطِر", "يس", "الصّافّات", "صۤ", "الزمَر", "غافِر", "فصّلت", "الشورى", "الزخرف", "الدخَان", "الجاثيَة", "الأحقاف"
            , "محمد", "الفتح", "الحجرات", "قۤ", "الذاريات", "الطور", "النجم", "القمَر", "الرحمن", "الواقعة", "الحديد", "المجادلة"
            , "الحشر", "الممتحنة", "الصّف", "الجمعة", "المنافقون", "التغابُن", "الطلاق", "التحريم", "الملك", "القلم", "الحاقة", "المعَارِج"
            , "نوح", "الجِنّ", "المزمّل", "المدّثر", "القيَامة", "الإنسان", "المرسَلات", "النّبَأ", "النّازعَات", "عبَس", "التكوير", "الإنفطار"
            , "المطفّفين", "الانشِقَاق", "البُروج", "الطّارق", "الأعلىٰ", "الغَاشِيَة", "الفجْر", "البَلد", "الشمس", "الليل", "الضّحَىٰ", "الشّرْح"
            , "التين", "العَلَق", "القدْر", "البیّنة", "الزَّلزّلة", "العَاديَات", "القارعَة", "التكاثر", "العَصر",
            "الهُمَزة", "الفِیل", "قريش", "الماعُون", "الكوثر", "الكافِرون", "النّصر", "المسَد", "الإخلاص", "الفَلَق", "الناس"};

    public void createQuranList() {
        quranModelList = new ArrayList<>();
        for (int i = 0; i < listOfSewarNames.length; i++) {
            quranModelList.add(new QuranModel(listOfSewarNames[i]));
        }
    }
}