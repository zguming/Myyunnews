package activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.SearchView;

import com.example.yunnews.R;

/**
 * Created by Administrator on 2017/10/17.
 */

public class MySearchView extends AppCompatActivity {
    private SearchView mSearchView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_search_view);
        mSearchView = (SearchView) findViewById(R.id.searchView);


        // 设置搜索文本监听
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            // 当点击搜索按钮时触发该方法
            @Override
            public boolean onQueryTextSubmit(String query) {
                Intent intent = new Intent(MySearchView.this, BrowseNewsActivity.class);
                intent.putExtra("url", "https://m.toutiao.com/search/?keyword="+query);
                startActivity(intent);
                return false;
            }


            // 当搜索内容改变时触发该方法
            @Override
            public boolean onQueryTextChange(String newText) {
                //if (!TextUtils.isEmpty(newText)){
                   // mListView.setFilterText(newText);
               // }else{
                //  mListView.clearTextFilter();
               // }
                return false;
            }
        });

    }

}
