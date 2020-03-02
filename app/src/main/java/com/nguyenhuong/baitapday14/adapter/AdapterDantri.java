package com.nguyenhuong.baitapday14.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.nguyenhuong.baitapday14.R;
import com.nguyenhuong.baitapday14.listener.OnNewsItemClick;
import com.nguyenhuong.baitapday14.modal.DantriNews;

import java.util.List;

public class AdapterDantri extends RecyclerView.Adapter<AdapterDantri.ViewHolder> {
    private Context mContext;
    private List<DantriNews> mVnExpress;
    private OnNewsItemClick mListener;

    public AdapterDantri(Context mContext, List<DantriNews> mVnExpress, OnNewsItemClick mListener) {
        this.mContext = mContext;
        this.mVnExpress = mVnExpress;
        this.mListener = mListener;
    }

    @NonNull
    @Override
    public AdapterDantri.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(mContext).inflate(R.layout.vnexpress_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterDantri.ViewHolder holder, int position) {
        final DantriNews vnEx=mVnExpress.get(position);
        holder.tvDate.setText(vnEx.getPubDate());
        holder.tvDes.setText(vnEx.getDescription());
        holder.tvTitle.setText(vnEx.getTitle());
        Glide.with(mContext).load(vnEx.getThumbUrl()).placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher).into(holder.imgThumb);
        holder.llItems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onItemClick(vnEx.getDetailUrl());
            }
        });
    }

    @Override
    public int getItemCount() {
        return mVnExpress.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgThumb;
        TextView tvDes,tvTitle,tvDate;
        LinearLayout llItems;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgThumb=itemView.findViewById(R.id.img_thumb2);
            tvDate=itemView.findViewById(R.id.tv_date2);
            tvDes=itemView.findViewById(R.id.tv_des2);
            tvTitle=itemView.findViewById(R.id.tv_title2);
            llItems=itemView.findViewById(R.id.ll_vn);
        }
    }
}
