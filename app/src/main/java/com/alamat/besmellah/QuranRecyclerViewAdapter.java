package com.alamat.besmellah;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.alamat.besmellah.databinding.HadeesItemBinding;
import com.alamat.besmellah.databinding.QuranItemBinding;

import java.util.List;

public class QuranRecyclerViewAdapter extends RecyclerView.Adapter<QuranRecyclerViewAdapter.ViewHolder> {

    List<QuranModel> quranModels;

    public QuranRecyclerViewAdapter(List<QuranModel> quranModels) {
        this.quranModels = quranModels;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        QuranItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.quran_item, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        QuranModel quranModel = quranModels.get(position);
        holder.quranItemBinding.tvQuranQuranText.setText(quranModel.quranText);
    }

    @Override
    public int getItemCount() {
        if (quranModels == null) {
            return 0;
        } else {
            return quranModels.size();
        }
    }


    ////////////////////////////////////////////////////////
    class ViewHolder extends RecyclerView.ViewHolder {
        QuranItemBinding quranItemBinding;

        public ViewHolder(QuranItemBinding quranItemBinding) {
            super(quranItemBinding.getRoot());
            this.quranItemBinding = quranItemBinding;

        }
    }

}
