package org.zky.overwatch;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 *
 * Created by zhan9 on 2016/11/11.
 */

public class HeroListFragment extends Fragment {
    OnHeadlineSelectedListener mCallback;

    // The container Activity must implement this interface so the frag can deliver messages
    public interface OnHeadlineSelectedListener {
        /** Called by HeadlinesFragment when a list item is selected */
        public void onArticleSelected(int position);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_hero_list, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        RecyclerView rv = (RecyclerView) view.findViewById(R.id.rv);
    }

    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception.
        try {
            mCallback = (OnHeadlineSelectedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnHeadlineSelectedListener");
        }
    }

//    @Override
//    public void onListItemClick(ListView l, View v, int position, long id) {
//        // Notify the parent activity of selected item
//        mCallback.onArticleSelected(position);
//
//        // Set the item as checked to be highlighted when in two-pane layout
//        getListView().setItemChecked(position, true);
//    }

}
