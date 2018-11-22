package com.example.module_main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.ViewGroup;

import com.alibaba.android.arouter.launcher.ARouter;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cn.net.yto.baselibrary.base.BaseAppUtils;
import cn.net.yto.baselibrary.base.YTOActivity;
import cn.net.yto.baselibrary.widget.DialogUtils;

/**
 * Created by xiaoxiong on 2018/8/7.
 * 描述:
 * 路径:
 */
public class MainActivity extends YTOActivity {

   private ViewPager viewPager;
   private FragmentPagerAdapter pagerAdapter;
   private List<Fragment> fragments=new ArrayList<>();
    //默认为0
    private int mIndex = 2;
    private  List<String> mTitles =  Arrays.asList("签到","资讯","我");

    private ArrayList<CustomTabEntity> entities  = new ArrayList<>();
    private CommonTabLayout tabLayout;

    // 未被选中的图标
    private List<Integer> mIconUnSelectIds = Arrays.asList(R.mipmap.ic_home_normal, R.mipmap.ic_discovery_normal, R.mipmap.ic_hot_normal, R.mipmap.ic_mine_normal);


    // 被选中的图标
    private List mIconSelectIds = Arrays.asList(R.mipmap.ic_home_selected, R.mipmap.ic_discovery_selected, R.mipmap.ic_hot_selected, R.mipmap.ic_mine_selected);


    @Override
    protected int getContentLayoutId() {
        return  R.layout.act_main_viewpager_layout;
    }

    @Override
    protected void initView() {
        viewPager = findViewById(R.id.viewpager);
        tabLayout = findViewById(R.id.tab_layout);


    }

    @Override
    protected void initData() {
        fragments.add((Fragment) ARouter.getInstance().build("/module_sign/sign_fragment").navigation());
        fragments.add((Fragment) ARouter.getInstance().build("/module_info/info_fragment").navigation());
        fragments.add((Fragment) ARouter.getInstance().build("/module_sign/sign_fragment1").navigation());


        pagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {

            }
        };
        viewPager.setOffscreenPageLimit(fragments.size());
        viewPager.setAdapter(pagerAdapter);

        initTab();
    }

    private void initTab(){
        for (int i=0;i<3;i++){
             final int finalI = i;
            CustomTabEntity entity = new CustomTabEntity() {
                @Override
                public String getTabTitle() {
                    return mTitles.get(finalI);
                }

                @Override
                public int getTabSelectedIcon() {
                    return (int) mIconSelectIds.get(finalI);
                }

                @Override
                public int getTabUnselectedIcon() {
                    return mIconUnSelectIds.get(finalI);
                }
            };
            entities.add(entity);
        }
        tabLayout.setTabData(entities);

    }

    @Override
    protected void initListener() {
      viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
          @Override
          public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

          }

          @Override
          public void onPageSelected(int position) {
              Log.e("xiong","position:"+position);
              tabLayout.setCurrentTab(position);
          }

          @Override
          public void onPageScrollStateChanged(int state) {

          }
      });

      tabLayout.setOnTabSelectListener(new OnTabSelectListener() {
          @Override
          public void onTabSelect(int position) {
              viewPager.setCurrentItem(position);
          }

          @Override
          public void onTabReselect(int position) {

          }
      });


    }




    private long mExitTime = 0;


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (System.currentTimeMillis()-mExitTime <= 2000) {
                BaseAppUtils.closeApp();
            } else {
                mExitTime = System.currentTimeMillis();
                DialogUtils.showToast("再按一次退出程序");
            }
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }


}
