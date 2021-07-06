package com.alamat.besmellah.Hadees;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.alamat.besmellah.R;
import com.alamat.besmellah.databinding.HadeesItemBinding;

import java.util.List;

public class HadeesRecyclerViewAdapter extends RecyclerView.Adapter<HadeesRecyclerViewAdapter.ViewHolder> {

    List<HadeesModel> hadeesModels;
    int itemView;
    OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public HadeesRecyclerViewAdapter(List<HadeesModel> hadeesModels) {
        this.hadeesModels = hadeesModels;
        this.itemView = itemView;
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
        holder.hadeesItemBinding.tvHadeesHadeesText.setText(hadeesModel.title);

        if (onItemClickListener != null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(position,hadeesModel);
                }
            });
        }
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

    public interface OnItemClickListener{
        void onItemClick(int pos , HadeesModel hadeesModel);
    }
}
