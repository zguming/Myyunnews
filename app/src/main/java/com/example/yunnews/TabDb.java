package com.example.yunnews;

import fragment.SetFragment;
import fragment.MineFragment;
import fragment.HomeFragment;
import fragment.VedioFragment;

public class TabDb {

	public static String[] getTabsTxt() {
		String[] tabs = {"首页","视频","我的","设置"};
		return tabs;
	}

	public static Class[] getFramgent(){
		Class[] cls = {HomeFragment.class, VedioFragment.class, MineFragment.class, SetFragment.class};
		return cls ;
	}

	public static int[] getTabsImg(){
		int[] img = {R.drawable.home1,R.drawable.vedio1,R.drawable.user1,R.drawable.set1};
		return img ;
	}

	public static int[] getTabsImgLight(){
		int[] img = {R.drawable.home2,R.drawable.vedio2,R.drawable.user2,R.drawable.set2};
		return img ;
	}
}