package bean;


/**
 * Created by Administrator on 2017/8/10.
 */

public class News {
    private String title;
    private String time;
    private String picurl;
    private String url;
    private  String src;
    public News(String title, String time, String pic, String url, String src) {
        this.title=title;
        this.time=time;
        this.picurl=pic;
        this.url=url;
        this.src=src;
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
    public String getUrl(){return url;}
    public String getSrc(){return src;}
}

