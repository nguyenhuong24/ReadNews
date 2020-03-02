package com.nguyenhuong.baitapday14.listener;

import com.nguyenhuong.baitapday14.modal.News24h;

import java.util.List;

public interface OnParserComplete {
    void onComplete(List<News24h> news24hs);
    void onFailure(String eror);
}
