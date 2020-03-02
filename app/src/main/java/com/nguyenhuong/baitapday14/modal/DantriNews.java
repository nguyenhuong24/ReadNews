package com.nguyenhuong.baitapday14.modal;

public class DantriNews {
    private String title;
    private String description;
    private String detailUrl;
    private String thumbUrl;
    private String pubDate;

    public DantriNews() {
    }

    public DantriNews(String title, String description, String detailUrl, String thumbUrl, String pubDate) {
        this.title = title;
        this.description = description;
        this.detailUrl = detailUrl;
        this.thumbUrl = thumbUrl;
        this.pubDate = pubDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDetailUrl() {
        return detailUrl;
    }

    public void setDetailUrl(String detailUrl) {
        this.detailUrl = detailUrl;
    }

    public String getThumbUrl() {
        return thumbUrl;
    }

    public void setThumbUrl(String thumbUrl) {
        this.thumbUrl = thumbUrl;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }
}
