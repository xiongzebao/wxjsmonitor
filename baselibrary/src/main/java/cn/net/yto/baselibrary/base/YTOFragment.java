package cn.net.yto.baselibrary.base;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.scwang.smartrefresh.header.PhoenixHeader;
import com.scwang.smartrefresh.layout.api.RefreshHeader;

public abstract class YTOFragment  extends Fragment  {

    protected View rootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.e("xiong","onCreateView");
        if (rootView == null) {
            rootView = getRootView();
        } else {
            ViewGroup parent = (ViewGroup) rootView.getParent();
            if (parent != null) {
                parent.removeView(rootView);
            }
        }
        return rootView;

    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        initOnCreate();
    }

      protected View getRootView(){
        return rootView;
    }


    protected View findViewById(int id){
        return rootView.findViewById(id);
    }

    private void initOnCreate() {
        attachPresenter();
        initView();
        initData();
        initListener();
        start();
    }

    protected abstract void attachPresenter();

    protected abstract int getContentLayoutId();

    protected abstract void initView();

    protected abstract void initData();

    protected abstract void initListener();

    abstract protected void start();

}
