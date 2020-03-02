package com.nguyenhuong.baitapday14.listener;

import com.nguyenhuong.baitapday14.modal.DantriNews;

import java.util.List;

public interface OnParseComplete1 {
    void onFailure(String eror);
    void onCompleteVN(List<DantriNews> dantriNews);
}
