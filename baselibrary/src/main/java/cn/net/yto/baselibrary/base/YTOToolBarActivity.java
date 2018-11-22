package cn.net.yto.baselibrary.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import cn.net.yto.baselibrary.R;
import cn.net.yto.baselibrary.widget.MyToolBar;

/**
 * Created by xiaoxiong on 2018/7/30.
 * 描述:
 * 路径:
 */
public abstract class YTOToolBarActivity extends YTOActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected ViewGroup setRootView() {
        ViewGroup viewGroup = (ViewGroup) getLayoutInflater().inflate(R.layout.act_title_layout, null);
        addToolBar(viewGroup);
        return viewGroup;
    }

    @Override
    protected void addContentLayout() {
        getLayoutInflater().inflate(getContentLayoutId(), rootView);
    }

    private void addToolBar(ViewGroup viewGroup) {
        View toolBar = getToolBar();
        viewGroup.addView(toolBar, 0);
        setToolBar(toolBar);

    }


    protected abstract String setTopTitle();


    protected View getToolBar() {
        return new MyToolBar(this);
    }

    /**
     * @param toolBar 基类返回toolBar对象
     */
    protected void setToolBar(View toolBar) {

    }


}
