package com.alamat.besmellah;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.alamat.besmellah.databinding.HadeesItemBinding;

import java.util.List;

public class HadeesRecyclerViewAdapter extends RecyclerView.Adapter<HadeesRecyclerViewAdapter.ViewHolder> {

    List<HadeesModel> hadeesModels;

    public HadeesRecyclerViewAdapter(List<HadeesModel> hadeesModels) {
        this.hadeesModels = hadeesModels;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        HadeesItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.hadees_item, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        HadeesModel hadeesModel = hadeesModels.get(position);
        holder.hadeesItemBinding.tvHadeesHadeesText.setText(hadeesModel.hadeesText);
    }

    @Override
    public int getItemCount() {
        if (hadeesModels == null) {
            return 0;
        } else {
            return hadeesModels.size();
        }
    }


    ////////////////////////////////////////////////////////
    class ViewHolder extends RecyclerView.ViewHolder {
        HadeesItemBinding hadeesItemBinding;

        public ViewHolder(HadeesItemBinding hadeesItemBinding) {
            super(hadeesItemBinding.getRoot());
            this.hadeesItemBinding = hadeesItemBinding;

        }
    }

}
