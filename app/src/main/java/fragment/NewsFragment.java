package fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.example.yunnews.LoadMoreListView;
import adapter.NewsAdapter;
import com.example.yunnews.R;

import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

import activity.BrowseNewsActivity;
import bean.News;
import db.NewsDb;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
/**
 * Created by lwp940118 on 2016/11/25.
 */
public class NewsFragment extends Fragment  {

    private View view;
    public LoadMoreListView listView;
    public String title1;
    public static final int UPDATE=1;
    public static final int UPDATE1=2;
    private SwipeRefreshLayout swipeRefresh;
    NewsAdapter adapter;
    public List<News> newsList=new ArrayList<>();
    protected boolean isCreate = false;
    public int a=0;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view =  inflater.inflate(R.layout.fragement_news, container, false);
        listView= (LoadMoreListView) view.findViewById(R.id.list_view);
        listView.setOnLoadMoreListener(new LoadMoreListView.OnLoadMoreListener() {
            @Override
            public void onloadMore() {
                a=a+20;
                sendOkHttpRequest();
            }
        });
        return view;
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        isCreate=true;
        if (getUserVisibleHint()) {
            //加载数据相当于Fragment的onPause，这样就能看到第一页的数据了！
            lazyLoad();
        }

    }
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser && isCreate) {
            //可见时加载数据相当于Fragment的onResume
            lazyLoad();
        } else {
            //不可见时不加载数据

        }
    }
    public void lazyLoad(){
        Bundle bundle = this.getArguments();
        if(bundle != null){

            title1 = bundle.getString("title");
        }
        sendOkHttpRequest();
        swipeRefresh= (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh);
        swipeRefresh.setColorSchemeResources(R.color.colorPrimary);
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener(){
            @Override
            public void onRefresh() {
                refreshNews();
            }
        });
    }
    public void refreshNews(){
        a=0;
        newsList.clear();
        sendOkHttpRequest();
        swipeRefresh.setRefreshing(false);
    }
    public NewsFragment newInstance(String title){
        Bundle bundle = new Bundle();
        bundle.putString("title", title);
        NewsFragment Fm = new NewsFragment();
        Fm.setArguments(bundle);
        return Fm;
    }
    public void sendOkHttpRequest() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder().url("http://api.jisuapi.com/news/get?channel="+title1+"&start="+a+"&num=20&appkey=beba7be7334e27c0").build();
                    Response response = client.newCall(request).execute();
                    String responseData = response.body().string();
                    parseJSON(responseData);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
    Handler handler=new Handler() {
        public void handleMessage(Message msg){
            switch(msg.what){
                case UPDATE:
                    adapter=new NewsAdapter(getContext(),
                            R.layout.listview_item,newsList);
                    listView.setAdapter(adapter);
                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            final News news;
                            news=newsList.get(position);
                            new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    NewsDb newsDb =new NewsDb();
                                    newsDb.setPicurl(news.getPicurl());
                                    newsDb.setSrc(news.getSrc());
                                    newsDb.setTitle(news.getTitle());
                                    newsDb.setTime(news.getTime());
                                    newsDb.setUrl(news.getUrl());
                                    newsDb.save();
                                }
                            }).start();
                            Intent intent = new Intent(getContext(), BrowseNewsActivity.class);
                            intent.putExtra("url", news.getUrl());
                            startActivity(intent);
                        }
                    });
                    break;
                case UPDATE1:
                    adapter.notifyDataSetChanged();
                    listView.setLoadCompleted();
                    break;
                default:
                    break;
            }
        }
    };
    public  void parseJSON(String jsonData){
        try{
            JSONObject json = new JSONObject( jsonData);
            JSONObject jsonObject=json.getJSONObject("result");
            JSONArray jsonArray=jsonObject.getJSONArray("list");
            for(int i=0;i<jsonArray.length();i++){
                JSONObject obj=jsonArray.getJSONObject(i);
                String title = obj.getString("title");
                String time = obj.getString("time");
                String pic=obj.getString("pic");
                String url=obj.getString("url");
                String src=obj.getString("src");
                newsList.add(new News(title,time,pic,url,src));
            }
            Message message=new Message();
            if(a==0){
            message.what=UPDATE;
            }else{
                message.what=UPDATE1;
            }
            handler.sendMessage(message);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}