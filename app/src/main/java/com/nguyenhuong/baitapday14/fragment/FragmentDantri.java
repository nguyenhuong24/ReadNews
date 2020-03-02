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
import com.nguyenhuong.baitapday14.adapter.AdapterDantri;
import com.nguyenhuong.baitapday14.listener.OnNewsItemClick;
import com.nguyenhuong.baitapday14.listener.OnParseComplete1;
import com.nguyenhuong.baitapday14.modal.DantriNews;
import com.nguyenhuong.baitapday14.parser.PullParserDantri;

import java.util.ArrayList;
import java.util.List;

public class FragmentDantri extends Fragment implements OnNewsItemClick, OnParseComplete1 {
    public static FragmentDantri INSSTANCE;
    private List<DantriNews> vnNews;
    private AdapterDantri adapter;
    private RecyclerView rvExpress;
    public FragmentDantri() {
    }
    public static FragmentDantri getInstance(){
        if (INSSTANCE==null){
            INSSTANCE=new FragmentDantri();
        }
        return INSSTANCE;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.vn_fragment_layout,container,false);
        rvExpress=view.findViewById(R.id.rv_vn);
        vnNews=new ArrayList<>();
        adapter=new AdapterDantri(getContext(),vnNews,this);
        LinearLayoutManager layoutManager=new LinearLayoutManager(getContext());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        rvExpress.setLayoutManager(layoutManager);
        rvExpress.setAdapter(adapter);
        new PullParserDantri(this).execute("https://dantri.com.vn/trangchu.rss");
        return view;
    }

    @Override
    public void onItemClick(String url) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(browserIntent);
    }

    @Override
    public void onFailure(String eror) {
        Toast.makeText(getContext(), eror, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCompleteVN(List<DantriNews> dantriNews) {
        vnNews.clear();
        vnNews.addAll(dantriNews);
        adapter.notifyDataSetChanged();
    }
}
