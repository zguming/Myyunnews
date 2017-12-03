package com.example.yunnews;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TabWidget;
import android.widget.TextView;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabHost.TabSpec;
import android.widget.Toast;

public class MainActivity extends FragmentActivity implements OnTabChangeListener {

    private FragmentTabHost mTabHost;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);


        initHost();

        initTab();



        mTabHost.onTabChanged(TabDb.getTabsTxt()[0]);
    }
    //声明一个long类型变量：用于存放上一点击“返回键”的时刻
    private long mExitTime;
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //判断用户是否点击了“返回键”
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            //与上次点击返回键时刻作差
            if ((System.currentTimeMillis() - mExitTime) > 2000) {
                //大于2000ms则认为是误操作，使用Toast进行提示
                Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                //并记录下本次点击“返回键”的时刻，以便下次进行判断
                mExitTime = System.currentTimeMillis();
            } else {
                //小于2000ms则认为是用户确实希望退出程序-调用System.exit()方法进行退出
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }



    private void initTab() {
        String[] tabs = TabDb.getTabsTxt();
        for (int i = 0; i < tabs.length; i++) {

            TabSpec tabSpec = mTabHost.newTabSpec(TabDb.getTabsTxt()[i]);

            View view = LayoutInflater.from(this).inflate(R.layout.tabs_foot, null);
            ((TextView) view.findViewById(R.id.foot_tv)).setText(TabDb.getTabsTxt()[i]);
            ((ImageView) view.findViewById(R.id.foot_iv)).setImageResource(TabDb.getTabsImg()[i]);
            tabSpec.setIndicator(view);

            mTabHost.addTab(tabSpec,TabDb.getFramgent()[i],null);
        }
    }

    private void initHost() {
        mTabHost = (FragmentTabHost) findViewById(R.id.main_tab);

        mTabHost.setup(this, getSupportFragmentManager(),R.id.main_view);

        mTabHost.getTabWidget().setDividerDrawable(null);

        mTabHost.setOnTabChangedListener(this);
    }

    @Override
    public void onTabChanged(String arg0) {
        TabWidget tabw = mTabHost.getTabWidget();
        for (int i = 0; i < tabw.getChildCount(); i++) {
            View v = tabw.getChildAt(i);
            TextView tv = (TextView) v.findViewById(R.id.foot_tv);
            ImageView iv = (ImageView) v.findViewById(R.id.foot_iv);

            if (i == mTabHost.getCurrentTab()) {
                tv.setTextColor(Color.rgb(12, 5, 108));
                iv.setImageResource(TabDb.getTabsImgLight()[i]);
            }else{
                tv.setTextColor(getResources().getColor(R.color.tab_color));
                iv.setImageResource(TabDb.getTabsImg()[i]);
            }
        }
    }
}