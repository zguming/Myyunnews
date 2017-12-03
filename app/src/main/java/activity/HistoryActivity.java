package activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import com.example.yunnews.LoadMoreListView;
import com.example.yunnews.R;

import org.litepal.crud.DataSupport;

import java.util.List;

import adapter.NewsDbAdapter;
import bean.News;
import db.NewsDb;

import static org.litepal.LitePalApplication.getContext;

/**
 * Created by Administrator on 2017/11/28.
 */

public class HistoryActivity extends AppCompatActivity implements View.OnClickListener {
    ListView mListView;
    NewsDbAdapter adapter;
    private ImageView bImageView;
    List<NewsDb> newsList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        mListView= (ListView) findViewById(R.id.history_list_view);
        newsList= DataSupport.findAll(NewsDb.class);
        adapter=new NewsDbAdapter(getContext(),
                R.layout.listview_item,newsList);
        mListView.setAdapter(adapter);
        OnItemClick();
        findViews();
    }
    public void findViews() {
        bImageView = (ImageView) findViewById(R.id.imageview_back);
        bImageView.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.imageview_back:

                finish();
                break;
            default:
                break;
        }
    }
    public void OnItemClick(){
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final NewsDb news;
                news = newsList.get(position);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        NewsDb newsDb = new NewsDb();
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
    }
}
