package com.nguyenhuong.baitapday14.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.nguyenhuong.baitapday14.R;
import com.nguyenhuong.baitapday14.adapter.Adapter24h;
import com.nguyenhuong.baitapday14.listener.OnNewsItemClick;
import com.nguyenhuong.baitapday14.listener.OnParserComplete;
import com.nguyenhuong.baitapday14.modal.News24h;

import com.nguyenhuong.baitapday14.parser.PullParserNews224h;

import java.util.ArrayList;
import java.util.List;

public class Fragment24h extends Fragment implements OnNewsItemClick, OnParserComplete {
    public static Fragment24h INSTANCE;
    private List<News24h> mNews24hs;
    private RecyclerView rvNews24h;
    private Adapter24h mAdapter24h;

    public Fragment24h() {
    }
    public static Fragment24h getInstance(){
        if (INSTANCE==null){
            INSTANCE=new Fragment24h();
        }
        return INSTANCE;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.news24h_fragment_layout,container,false);
        rvNews24h=view.findViewById(R.id.rv_news2h);
        mNews24hs=new ArrayList<>();
        mAdapter24h=new Adapter24h(getContext(),mNews24hs,this);
        LinearLayoutManager layoutManager=new LinearLayoutManager(getContext());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        rvNews24h.setLayoutManager(layoutManager);
        rvNews24h.setAdapter(mAdapter24h);
        new PullParserNews224h(this).execute("https://cdn.24h.com.vn/upload/rss/trangchu24h.rss");
        return view;
    }

    @Override
    public void onItemClick(String url) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(browserIntent);
    }

    @Override
    public void onComplete(List<News24h> news24hs) {
        mNews24hs.clear();
        mNews24hs.addAll(news24hs);
        mAdapter24h.notifyDataSetChanged();
    }

    @Override
    public void onFailure(String eror) {
        Toast.makeText(getContext(), eror, Toast.LENGTH_SHORT).show();
    }
}
