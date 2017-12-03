package db;

import org.litepal.crud.DataSupport;

/**
 * Created by Administrator on 2017/11/28.
 */

public class NewsDb extends DataSupport{
    private String title;
    private String time;
    private String picurl;
    private String url;
    private  String src;

    public void setTitle(String title) {
        this.title = title;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setPicurl(String picurl) {
        this.picurl = picurl;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getTitle() {
        return title;
    }
    public String getTime() {
        return time;
    }
    public String getPicurl() {
        return picurl;
    }
    public String getUrl(){
        return url;
    }
    public String getSrc(){
        return src;
    }

}
