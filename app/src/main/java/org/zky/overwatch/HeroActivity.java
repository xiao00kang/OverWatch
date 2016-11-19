package org.zky.overwatch;

import android.content.res.Configuration;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;

import org.zky.overwatch.fragment.HeroFragment;
import org.zky.overwatch.fragment.HeroListFragment;

public class HeroActivity extends AppCompatActivity implements HeroListFragment.OnHeadlineSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hero);

        // Check whether the activity is using the layout version with
        // the fragment_container FrameLayout. If so, we must add the first fragment
        if (findViewById(R.id.fragment_container) != null) {

            // However, if we're being restored from a previous state,
            // then we don't need to do anything and should return or else
            // we could end up with overlapping fragments.
            if (savedInstanceState != null) {
                return;
            }

            // Create an instance of ExampleFragment
            HeroListFragment firstFragment = new HeroListFragment();

            // In case this activity was started with special instructions from an Intent,
            // pass the Intent's extras to the fragment as arguments
            firstFragment.setArguments(getIntent().getExtras());

            // Add the fragment to the 'fragment_container' FrameLayout
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, firstFragment).commit();
        }
    }


    @Override
    public void onArticleSelected(int position) {
        // The user selected the headline of an article from the HeadlinesFragment

// Capture the article fragment from the activity layout
        HeroFragment heroFragment = (HeroFragment)
                getSupportFragmentManager().findFragmentById(R.id.article_fragment);

        if (heroFragment != null&&isScreenChange()) {
            // If article frag is available, we're in two-pane layout...

            // Call a method in the ArticleFragment to update its content
            heroFragment.updateView(position);

        } else {
            // If the frag is not available, we're in the one-pane layout and must swap frags...

            // Create fragment and give it an argument for the selected article
            HeroFragment newFragment = new HeroFragment();
            Bundle args = new Bundle();
            args.putInt(HeroFragment.POSITION, position);
            newFragment.setArguments(args);
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

            // Replace whatever is in the fragment_container view with this fragment,
            // and add the transaction to the back stack so the user can navigate back
            transaction.replace(R.id.fragment_container, newFragment);
            transaction.addToBackStack(null);

            // Commit the transaction
            transaction.commit();
        }
    }


    private boolean isScreenChange() {

        Configuration mConfiguration = this.getResources().getConfiguration(); //获取设置的配置信息
        int ori = mConfiguration.orientation ; //获取屏幕方向

        if(ori == mConfiguration.ORIENTATION_LANDSCAPE){

            return true;
        }else if(ori == mConfiguration.ORIENTATION_PORTRAIT){

            return false;
        }
        return false;
    }
}
