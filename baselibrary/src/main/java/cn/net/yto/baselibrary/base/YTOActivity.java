package cn.net.yto.baselibrary.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.ViewGroup;

import cn.net.yto.baselibrary.R;

/**
 * Created by xiaoxiong on 2018/7/30.
 * 描述:
 * 路径:
 */
public abstract class YTOActivity extends YTOBaseActivity {

    protected ViewGroup rootView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        rootView=setRootView();
        setContentView(rootView);

        addContentLayout();
        beforeInit();
        init();
    }



    protected void addContentLayout(){

    }

    protected ViewGroup setRootView(){
        return  (ViewGroup) getLayoutInflater().inflate(getContentLayoutId(),null);
    }


    abstract  protected int getContentLayoutId();
    protected void beforeInit(){

    }

    private void init() {
        initView();
        initData();
        initListener();
        start();
    }


    protected abstract void initView();

    protected abstract void initData();

    protected abstract void initListener();

    protected void start() {
    }

}
