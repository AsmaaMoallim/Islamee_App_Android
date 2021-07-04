package com.alamat.besmellah;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.alamat.besmellah.databinding.FragmentHadeesBinding;

import java.util.ArrayList;
import java.util.List;


public class HadeesFragment extends Fragment {

    private FragmentHadeesBinding binding;
    View view;

    static HadeesRecyclerViewAdapter hadeesAdapter;
    RecyclerView.LayoutManager layoutManager;
    static List<HadeesModel> hadeesModelList;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_hadees, container, false);
        createHadeesList();
        hadeesAdapter = new HadeesRecyclerViewAdapter(hadeesModelList);
        layoutManager = new GridLayoutManager(getActivity(), 2);
        binding.rvHadeesRecyclerView.setAdapter(hadeesAdapter);
        binding.rvHadeesRecyclerView.setLayoutManager(layoutManager);


        hadeesAdapter.setOnItemClickListener(new HadeesRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int pos, HadeesModel hadeesModel) {
                Intent intent = new Intent(getActivity(), HadessContentActivity.class);
                String hadeescontent = hadeesModel.content;
                intent.putExtra("hadeescontent", hadeescontent);
                intent.putExtra("id", pos);
                startActivity(intent);
//                Toast.makeText(getActivity(), "onItemClick" + hadeesModel, Toast.LENGTH_SHORT).show();

            }
        });

        view = binding.getRoot();

        return view;
    }


    //        hadeesModelList = new ArrayList<>();
//        for (int i= 0;i<listOfAhadethNames.length;i++){
//            hadeesModelList.add(new HadeesModel( listOfAhadethNames[i] ));
//        }
    public void createHadeesList(){

        Cursor cursor=new DbManager(getContext()).readdata();
        hadeesModelList=new ArrayList<>();

        while(cursor.moveToNext())
        {
            HadeesModel obj = null;
            hadeesModelList.add(obj);
            int ind = hadeesModelList.indexOf(obj);
            obj=new HadeesModel(ind,cursor.getString(1),cursor.getString(2));
            hadeesModelList.set(ind,obj);
        }
    }

//    @Override
//    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
//        super.onViewStateRestored(savedInstanceState);
//        hadeesAdapter.notifyDataSetChanged();
//
//    }
//        @Override
//    public void onStart() {
//        super.onStart();
//        hadeesAdapter.notifyDataSetChanged();
//
//    }
//
//
//    @Override
//    public void onResume() {
//        super.onResume();
//        hadeesAdapter.notifyDataSetChanged();
//    }
}
