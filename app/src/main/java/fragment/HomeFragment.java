package fragment;

/**
 * Created by Administrator on 2017/11/20.
 */

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.view.ViewPager;

import java.util.ArrayList;
import java.util.List;
import android.support.design.widget.TabLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import activity.MySearchView;
import com.example.yunnews.R;
import adapter.TabFragmentAdapter;

public class HomeFragment extends Fragment {

    private View view;
    private View rootView;//缓存Fragmen view
    public TabLayout tabLayout;
    public ViewPager viewPager;
    public ImageView imageView;
    public List<String> strings = new ArrayList<>();
    public List<Fragment> fragments = new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_home, container, false);
            imageView = (ImageView) view.findViewById(R.id.imageView);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getContext(), MySearchView.class);
                    startActivity(intent);
                }
            });
            initdate();
            initView();
        }
        ViewGroup parent = (ViewGroup) view.getParent();
        if (parent != null) {
            parent.removeView(view);
        }
        return view;
    }
    /*@Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initdate();
        initView();


    }*/


    public void initView(){
        tabLayout = (TabLayout)view.findViewById(R.id.tablayout);
        viewPager = (ViewPager)view.findViewById(R.id.viewpager);
        viewPager.setAdapter(new TabFragmentAdapter(fragments,strings,
                getActivity().getSupportFragmentManager(),getContext()));
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabTextColors(getResources().getColor(R.color.radiobutton)
                ,getResources().getColor(R.color.radiobuttonzhong));
    }

    public void initdate(){
        NewsFragment newsFragment = new NewsFragment().newInstance("新闻");
        fragments.add(newsFragment);
        strings.add("推荐");
        NewsFragment fragment2 = new NewsFragment().newInstance("头条");
        fragments.add(fragment2);
        strings.add("新闻");
        NewsFragment fragment3 = new NewsFragment().newInstance("财经");
        fragments.add(fragment3);
        strings.add("财经");
        NewsFragment fragment4 = new NewsFragment().newInstance("体育");
        fragments.add(fragment4);
        strings.add("体育");
        NewsFragment fragment5 = new NewsFragment().newInstance("娱乐");
        fragments.add(fragment5);
        strings.add("娱乐");
        NewsFragment fragment6 = new NewsFragment().newInstance("军事");
        fragments.add(fragment6);
        strings.add("军事");
        NewsFragment fragment7 = new NewsFragment().newInstance("教育");
        fragments.add(fragment7);
        strings.add("教育");
        NewsFragment fragment8 = new NewsFragment().newInstance("科技");
        fragments.add(fragment8);
        strings.add("科技");
        NewsFragment fragment9 = new NewsFragment().newInstance("NBA");
        fragments.add(fragment9);
        strings.add("NBA");
        NewsFragment newsFragment10 = new NewsFragment().newInstance("股票");
        fragments.add(newsFragment10);
        strings.add("股票");
        NewsFragment newsFragment11 = new NewsFragment().newInstance("星座");
        fragments.add(newsFragment11);
        strings.add("星座");
        NewsFragment newsFragment12 = new NewsFragment().newInstance("健康");
        fragments.add(newsFragment12);
        strings.add("健康");
        NewsFragment newsFragment13 = new NewsFragment().newInstance("育儿");
        fragments.add(newsFragment13);
        strings.add("育儿");
    }

}