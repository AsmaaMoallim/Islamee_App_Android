package com.alamat.besmellah.Hadees;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;

import com.alamat.besmellah.DbManager;
import com.alamat.besmellah.R;
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
        createHadeesList(getContext());
        hadeesAdapter = new HadeesRecyclerViewAdapter(hadeesModelList);
        layoutManager = new GridLayoutManager(getActivity(), 2);
        binding.rvHadeesRecyclerView.setAdapter(hadeesAdapter);
        binding.rvHadeesRecyclerView.setLayoutManager(layoutManager);


//        if (hadeesModelList.size() != 0){
//
            binding.imgbtnHadeesGetdown.setVisibility(View.VISIBLE);

            binding.imgbtnHadeesGetdown.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    binding.rvHadeesRecyclerView.smoothScrollToPosition(HadeesFragment.hadeesModelList.size());
//                                recyclerView.scrollToPosition(0);
                    binding.imgbtnHadeesGetdown.setVisibility(View.GONE);
                }
            });
            binding.rvHadeesRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {


                @Override
                public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                    super.onScrollStateChanged(recyclerView, newState);

                    LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();

                    int firstVisibleItemPosition = manager.findFirstVisibleItemPosition();

                    // Judge not to slide
                    if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                        // Determine whether to scroll more than one screen
                        if (firstVisibleItemPosition == 0) {// No more than one screen
                            // Hide here is for display after sliding, after manually sliding to the home page, it is hidden
                            binding.imgbtnHadeesBackup.setVisibility(View.GONE);

                        } else {
                            binding.imgbtnHadeesBackup.setVisibility(View.VISIBLE);
                            binding.imgbtnHadeesBackup.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    recyclerView.smoothScrollToPosition(0);
//                                recyclerView.scrollToPosition(0);
                                    binding.imgbtnHadeesBackup.setVisibility(View.GONE);
                                }
                            });
                        }
                    }


                    int findLastVisibleItemPosition = manager.findLastVisibleItemPosition();

                    // Judge not to slide
                    if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                        // Determine whether to scroll more than one screen
                        if (findLastVisibleItemPosition == HadeesFragment.hadeesModelList.size() - 1) {// No more than one screen
                            // Hide here is for display after sliding, after manually sliding to the home page, it is hidden
                            binding.imgbtnHadeesGetdown.setVisibility(View.GONE);

                        } else {
                            binding.imgbtnHadeesGetdown.setVisibility(View.VISIBLE);
                            binding.imgbtnHadeesGetdown.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    recyclerView.smoothScrollToPosition(HadeesFragment.hadeesModelList.size());
//                                recyclerView.scrollToPosition(0);
                                    binding.imgbtnHadeesGetdown.setVisibility(View.GONE);
                                }
                            });
                        }


                    }
                }
            });

//        }
//        else {
//
//            binding.imgbtnHadeesGetdown.setVisibility(View.GONE);
//            binding.imgbtnHadeesBackup.setVisibility(View.GONE);
//
//        }

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
    public static void createHadeesList(Context context) {

        Cursor cursor = new DbManager(context).readdata();
        hadeesModelList = new ArrayList<>();

        while (cursor.moveToNext()) {
            HadeesModel obj = null;
            hadeesModelList.add(obj);
            int ind = hadeesModelList.indexOf(obj);
            obj = new HadeesModel(ind, cursor.getString(1), cursor.getString(2));
            hadeesModelList.set(ind, obj);
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

//    @Override
//    public void onResume() {
//        super.onResume();
//
//    }
}
