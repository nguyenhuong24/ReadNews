package com.nguyenhuong.baitapday14.parser;
import android.os.AsyncTask;

import com.nguyenhuong.baitapday14.listener.OnParserComplete;
import com.nguyenhuong.baitapday14.modal.News24h;

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


public class PullParserNews224h extends AsyncTask<String, Void, List<News24h>> {
    private OnParserComplete mListner;

    public PullParserNews224h(OnParserComplete mListner) {
        this.mListner = mListner;
    }

    @Override
    protected List<News24h> doInBackground(String... args) {
        String link=args[0];
        List<News24h> item=parseURL(link);
        return item;
    }

    private List<News24h> parseURL(String link) {
        List<News24h> news24hs=new ArrayList<>();
        URL url= null;
        try {
            url = new URL(link);
            HttpsURLConnection connection= (HttpsURLConnection) url.openConnection();
            InputStream inputStream=connection.getInputStream();
            XmlPullParserFactory factory=XmlPullParserFactory.newInstance();
            XmlPullParser parser=factory.newPullParser();
            parser.setInput(inputStream,"UTF-8");
            int type=parser.getEventType();
            String text=null;
            News24h item=null;
            while (type!=XmlPullParser.END_DOCUMENT){
                String tag=parser.getName();
                switch (type){
                    case XmlPullParser.START_TAG:
                        if (tag.equals("item")){
                            item=new News24h();
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
                            news24hs.add(item);
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
            mListner.onFailure(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
            mListner.onFailure(e.getMessage());
        } catch (XmlPullParserException e) {
            e.printStackTrace();
            mListner.onFailure(e.getMessage());
        }

        return news24hs;
    }

    @Override
    protected void onPostExecute(List<News24h> news24hs) {
        super.onPostExecute(news24hs);
        mListner.onComplete(news24hs);
    }
}
