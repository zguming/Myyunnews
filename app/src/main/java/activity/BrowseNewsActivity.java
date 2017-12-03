package activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.example.yunnews.R;

/**
 * Created by Administrator on 2017/9/13.
 */

public class BrowseNewsActivity extends AppCompatActivity implements View.OnClickListener{
    private WebView webView;
    private ImageView bImageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitivy_browse_news);
        findViews();

        webView = (WebView) findViewById(R.id.webView);
        String url = getIntent().getStringExtra("url");
        webView.loadUrl(url);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
    }
    public void findViews() {
        bImageView = (ImageView) findViewById(R.id.imageview_back3);
        bImageView.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.imageview_back3:

                finish();
                break;
            default:
                break;
        }
    }
}
