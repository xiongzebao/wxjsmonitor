package cn.net.yto.baselibrary.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;

import com.hazz.kotlinmvp.net.exception.ErrorStatus;
import com.hazz.kotlinmvp.net.exception.NetExceptionHandle;
import com.scwang.smartrefresh.header.PhoenixHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import cn.net.yto.baselibrary.R;
import cn.net.yto.baselibrary.mvp.IBaseView;
import cn.net.yto.baselibrary.widget.DialogUtils;
import cn.net.yto.baselibrary.widget.MultipleStatusView;
import cn.net.yto.baselibrary.widget.MyToolBar;

public abstract class BaseFragment extends YTOFragment implements IBaseView {

    private MultipleStatusView multipleStatusView;
    private SmartRefreshLayout smartRefreshLayout;
    private View toolBar;

    private boolean toolBarVisible = false;
    private boolean isStart = true;

    @Override
    protected View getRootView() {
        ViewGroup rootView = (ViewGroup) getLayoutInflater().inflate(R.layout.fragment_base_layout, null);
        installToolBar(rootView);
        init(rootView);
        return rootView;
    }

    protected View getToolBar() {
        return new MyToolBar(getContext());
    }

    protected void setToolBarVisibility(boolean isVisible) {
        toolBarVisible = isVisible;
        if (!toolBarVisible) {
            return;
        }
        if (toolBar == null) {
            installToolBar((ViewGroup) rootView);
        }
        if (!toolBarVisible) {
            toolBar.setVisibility(View.GONE);
            return;
        }
        toolBar.setVisibility(View.VISIBLE);
    }

    private void installToolBar(ViewGroup rootView) {
        if (!toolBarVisible) {
            return;
        }
        View toolBar = getToolBar();
        if (toolBar == null) {
            throw new IllegalArgumentException("you try to show Toolbar ,but you have not implement getToolBar method to give a ToolBar instance");
        }
        if (rootView == null) {
            return;
        }
        this.toolBar = toolBar;
        rootView.addView(toolBar, 0);
    }

    private void init(View view) {
        int layoutId = getContentLayoutId();
        if (layoutId <= 0) {
            throw new IllegalArgumentException("Content layout ID is invalid");
        }
        multipleStatusView = view.findViewById(R.id.multipleStatusView);
        multipleStatusView.setOnRetryClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                start();
            }
        });
        smartRefreshLayout = view.findViewById(R.id.smartRefreshLayout);
        smartRefreshLayout.addView(getLayoutInflater().inflate(layoutId, null));
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                start();
            }
        });
    }

    public void setEnableRefresh(boolean isEnable) {
        smartRefreshLayout.setEnableRefresh(isEnable);
    }

    public void finishRefresh() {
        smartRefreshLayout.finishRefresh();
    }

    @Override
    public void showLoading() {
        multipleStatusView.showLoading();
    }

    @Override
    public void dismissLoading() {
        multipleStatusView.showContent();

    }



    @Override
    public void showError(String errMsg,int code) {
        if (!isStart) {
            DialogUtils.showToast(errMsg);
            finishLoading();
            return;
        }
        if(code== ErrorStatus.NETWORK_ERROR){
            multipleStatusView.showNoNetwork();
        }else{
            multipleStatusView.showError();
        }

    }

    @Override
    public void showContent() {
        isStart = false;
        multipleStatusView.showContent();
        finishLoading();
    }

    private void finishLoading() {
        finishRefresh();
        finishLoadMore();
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    protected void finishLoadMore() {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }

}
