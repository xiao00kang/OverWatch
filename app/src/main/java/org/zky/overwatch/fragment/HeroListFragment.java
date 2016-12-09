package org.zky.overwatch.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import org.zky.overwatch.Contents;
import org.zky.overwatch.R;
import org.zky.overwatch.widget.MyHeroesAdapter;

/**
 * Created by zhan9 on 2016/11/11.
 */

public class HeroListFragment extends Fragment {
    private static final String TAG = "HeroListFragment";
    private static final String POSITION = "position";
    int position_old = 0;

    boolean isFromBackStack = false;

    OnHeadlineSelectedListener mCallback;

    // The container Activity must implement this interface so the frag can deliver messages
    public interface OnHeadlineSelectedListener {
        public void onHeroSelected(int position);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hero_list, container, false);
        initView(view, savedInstanceState);
        Log.e(TAG, "onCreateView: position-" + position_old+",isFromBackStack:"+ isFromBackStack);
        return view;
    }

    private void initView(View view, Bundle b) {
        final ListView listView = (ListView) view.findViewById(R.id.lv);
        listView.setAdapter(new MyHeroesAdapter(getContext(), Contents.Heroes));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mCallback.onHeroSelected(position);

                //去除非当前item选择框
                View item = listView.findViewWithTag(position_old);
                if (item != null) {
                    item.setBackgroundResource(R.drawable.shape_background_solid);

                }
                //设置当前item背景
                view.setBackgroundResource(R.drawable.shape_background_stroken);
                position_old = position;
                view.setTag(position);
            }
        });

        //后台的时候数据恢复
        if (b != null) {
            position_old = b.getInt(POSITION);
            //TODO
        }
        //TODO 从回退栈出来的时候

    }

    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        // 确认activity实现了借口
        try {
            mCallback = (OnHeadlineSelectedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnHeadlineSelectedListener");
        }
    }

    @Override
    public void onStop() {
        isFromBackStack = true;
        super.onStop();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putInt(POSITION, position_old);
        Log.e(TAG, "onSaveInstanceState: " + position_old);
        super.onSaveInstanceState(outState);
    }
}
