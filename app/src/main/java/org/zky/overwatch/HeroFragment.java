package org.zky.overwatch;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 *
 * Created by zhan9 on 2016/11/11.
 */

public class HeroFragment extends Fragment {

    public static final String POSITION = "123";

    int mCurrentPosition = -1;
    private ArrayList<View> list;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (savedInstanceState != null) {
            mCurrentPosition = savedInstanceState.getInt(POSITION);
        }


        View view =inflater.inflate(R.layout.fragment_hero, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.tl);

        ViewPager viewPager = (ViewPager) view.findViewById(R.id.vp);
        list = new ArrayList<>();

        ImageView iv =new ImageView(getContext());
        iv.setImageResource(R.drawable.soldier76_gaikuang);
        iv.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        list.add(iv);

        ImageView iv1 =new ImageView(getContext());
        iv1.setImageResource(R.drawable.soldier76_gushi);
        iv1.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        list.add(iv1);

        viewPager.setAdapter(new MyPagerAdapter(list, new String[]{"概况"," 故事"}));
        tabLayout.setupWithViewPager(viewPager);

    }

    @Override
    public void onStart() {
        super.onStart();

        Bundle args = getArguments();
        if (args != null) {
            updateView(args.getInt(POSITION));
        } else if (mCurrentPosition != -1) {
            updateView(mCurrentPosition);
        }
    }

    public void updateView(int position){
        Log.i("debug", "更新位置：" + position);

    }

    class MyPagerAdapter extends PagerAdapter {

        //界面列表
        private ArrayList<View> views;
        private String[] titles;

        public MyPagerAdapter (ArrayList<View> views,String[] titles){
            this.views = views;
            this.titles = titles;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }

        /**
         * 获得当前界面数
         */
        @Override
        public int getCount() {
            if (views != null) {
                return views.size();
            }
            return 0;
        }

        /**
         * 初始化position位置界面
         */
        @Override
        public Object instantiateItem(View view, int position) {

            ((ViewPager) view).addView(views.get(position), 0);

            return views.get(position);
        }

        /**
         *判断是否由对象生成界面
         */
        @Override
        public boolean isViewFromObject(View view, Object arg1) {
            return (view == arg1);
        }

        /**
         * 销毁position位置界面
         */
        @Override
        public void destroyItem(View view, int position, Object arg2) {
            ((ViewPager) view).removeView(views.get(position));
        }
    }
}
