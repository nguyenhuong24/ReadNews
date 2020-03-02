package com.nguyenhuong.baitapday14.parser;

import android.os.AsyncTask;

import com.nguyenhuong.baitapday14.listener.OnParseComplete1;
import com.nguyenhuong.baitapday14.modal.DantriNews;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

public class PullParserDantri extends AsyncTask<String,Void, List<DantriNews>> {
    private OnParseComplete1 mListner;

    public PullParserDantri(OnParseComplete1 mListner) {
        this.mListner = mListner;
    }

    @Override
    protected List<DantriNews> doInBackground(String... args) {
        String link=args[0];
        List<DantriNews> item=parseURL(link);
        return item;
    }

    private List<DantriNews> parseURL(String link) {
        List<DantriNews> vnList=new ArrayList<>();
        try {
            URL url=new URL(link);
            HttpsURLConnection connection= (HttpsURLConnection) url.openConnection();
            InputStream inputStream=connection.getInputStream();
            XmlPullParserFactory factory=XmlPullParserFactory.newInstance();
            XmlPullParser parser=factory.newPullParser();
            parser.setInput(inputStream,"UTF-8");
            int type=parser.getEventType();
            String text=null;
            DantriNews item=null;
            while (type!=XmlPullParser.END_DOCUMENT){
                String tag=parser.getName();
                switch (type){
                    case XmlPullParser.START_TAG:
                        if (tag.equals("item")){
                            item=new DantriNews();
                        }else  if (tag.equals("img")){
                            String thumb=parser.getAttributeValue(0);
                            item.setThumbUrl(thumb);
                        }
                        break;
                    case XmlPullParser.TEXT:
                        text=parser.getText();
                        break;
                    case XmlPullParser.END_TAG:
                        if (item==null){
                            break;
                        }else if (tag.equals("item")){
                            vnList.add(item);
                        }else if (tag.equals("description")){
                            item.setDescription(text);
                        }else if (tag.equals("link")){
                            item.setDetailUrl(text);
                        }else if (tag.equals("pubDate")){
                            item.setPubDate(text);
                        }else if (tag.equals("title")){
                            item.setTitle(text);
                        }
                        break;
                }
                type=parser.next();
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }
        return vnList;
    }

    @Override
    protected void onPostExecute(List<DantriNews> dantriNews) {
        super.onPostExecute(dantriNews);
        mListner.onCompleteVN(dantriNews);
    }
}
