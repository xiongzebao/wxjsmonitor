package cn.net.yto.module_baselibrary_test;

import cn.net.yto.baselibrary.base.BaseActivity;

public class TestActivity extends BaseActivity {


    @Override
    public void setPresenter() {

    }

    @Override
    protected String setTopTitle() {
        return "teset";
    }

    @Override
    protected int getContentLayoutId() {
        setTheme(R.style.MyAppTheme);
        return R.layout.act_test;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }
}
