package cn.net.yto.module_info.fragment.activity;

import cn.net.yto.baselibrary.base.YTOActivity;
import cn.net.yto.baselibrary.base.YTOToolBarActivity;
import cn.net.yto.module_info.R;


public class InfoListActivity extends YTOToolBarActivity  {


    @Override
    protected int getContentLayoutId() {
        return R.layout.act_fragment_infolist_layout;
    }

    @Override
    protected void initView() {
   /*     //获取到FragmentManager，在V4包中通过getSupportFragmentManager，
        //在系统中原生的Fragment是通过getFragmentManager获得的。
        android.support.v4.app.FragmentManager FM = getSupportFragmentManager();
        //2.开启一个事务，通过调用beginTransaction方法开启。
        android.support.v4.app.FragmentTransaction MfragmentTransaction  = FM.beginTransaction();
        //把自己创建好的fragment创建一个对象
        Fragment f =  new InfoListFragment();
        //向容器内加入Fragment，一般使用add或者replace方法实现，需要传入容器的id和Fragment的实例。
        MfragmentTransaction.add(R.id.fragment,f);
        //提交事务，调用commit方法提交。
        MfragmentTransaction.commit();*/

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected String setTopTitle() {
        return "资讯";
    }


}
