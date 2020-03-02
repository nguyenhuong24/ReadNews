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
import com.nguyenhuong.baitapday14.modal.News24h;

import java.util.List;

public class Adapter24h extends RecyclerView.Adapter<Adapter24h.ViewHolder> {
    private Context mContext;
    private List<News24h> mNews24hs;
    private OnNewsItemClick mListner;

    public Adapter24h(Context mContext, List<News24h> mNews24hs, OnNewsItemClick mListner) {
        this.mContext = mContext;
        this.mNews24hs = mNews24hs;
        this.mListner = mListner;
    }

    @NonNull
    @Override
    public Adapter24h.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(mContext).inflate(R.layout.news24h_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter24h.ViewHolder holder, int position) {
        final News24h news=mNews24hs.get(position);
        holder.tvTitle.setText(news.getTitle());
        holder.tvDes.setText(news.getDescription());
        holder.tvDate.setText(news.getPubDate());
        Glide.with(mContext).load(news.getThumbUrl())
                .error(R.mipmap.ic_launcher)
                .placeholder(R.mipmap.ic_launcher)
                .into(holder.imgThumb);
        holder.llItems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListner.onItemClick(news.getDetailUrl());
            }
        });

    }

    @Override
    public int getItemCount() {
        return mNews24hs.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgThumb;
        TextView tvDes,tvTitle,tvDate;
        LinearLayout llItems;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgThumb=itemView.findViewById(R.id.img_thumb1);
            tvDate=itemView.findViewById(R.id.tv_date1);
            tvDes=itemView.findViewById(R.id.tv_des1);
            tvTitle=itemView.findViewById(R.id.tv_title1);
            llItems=itemView.findViewById(R.id.ll_24h);
        }
    }
}
