package com.alamat.besmellah.Quran;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.alamat.besmellah.R;
import com.alamat.besmellah.databinding.QuranItemBinding;

import java.util.List;

public class QuranRecyclerViewAdapter extends RecyclerView.Adapter<QuranRecyclerViewAdapter.ViewHolder> {

    List<QuranModel> quranModels;
    int itemView;
    OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }


    public QuranRecyclerViewAdapter(List<QuranModel> quranModels) {
        this.quranModels = quranModels;
        this.itemView = itemView;
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

        if (onItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(position, quranModel);
                }
            });
        }
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

    public interface OnItemClickListener {
        void onItemClick(int pos, QuranModel quranModel);

    }
}
