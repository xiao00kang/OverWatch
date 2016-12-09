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


        //单碎片布局
        if (findViewById(R.id.fragment_container) != null) {
            //处理保存信息
            if (savedInstanceState != null) {
                return;
            }

            HeroListFragment firstFragment = new HeroListFragment();

            //给碎片传intent，暂时没用
            firstFragment.setArguments(getIntent().getExtras());

            // 添加碎片
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, firstFragment).commit();
        }
    }


    @Override
    public void onHeroSelected(int position) {
        // 当列表英雄被选中的时候
        HeroFragment heroFragment = (HeroFragment)
                getSupportFragmentManager().findFragmentById(R.id.article_fragment);

        if (heroFragment != null&&isScreenChange()) {
            //说明是横屏，俩碎片
            heroFragment.updateView(position);

        } else {
            //单个碎片，替换成Hero碎片
            HeroFragment newFragment = new HeroFragment();
            Bundle args = new Bundle();
            args.putInt(HeroFragment.POSITION, position);
            newFragment.setArguments(args);
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

            // 添加到回退栈
            transaction.replace(R.id.fragment_container, newFragment);
            transaction.addToBackStack(null);

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
