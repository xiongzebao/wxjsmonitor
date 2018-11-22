package cn.net.yto.baselibrary.base;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;

import com.hazz.kotlinmvp.net.exception.ErrorStatus;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import cn.net.yto.baselibrary.R;
import cn.net.yto.baselibrary.mvp.IBaseView;
import cn.net.yto.baselibrary.widget.DialogUtils;
import cn.net.yto.baselibrary.widget.MultipleStatusView;
import cn.net.yto.baselibrary.widget.MyToolBar;


abstract  public class BaseActivity  extends YTOToolBarActivity implements IBaseView,OnRefreshListener {

    private MultipleStatusView multipleStatusView;
    private SmartRefreshLayout smartRefreshLayout;

    boolean isStart = true;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setPresenter();
        initRefreshView();
    }

    /**
     * <p>子类改变根布局重写次方法</p>
     * @return 根布局
     */
    @Override
    protected ViewGroup setRootView() {
         ViewGroup toolBarLayout=  super.setRootView();
         return (ViewGroup) getLayoutInflater().inflate(R.layout.fragment_base_layout,toolBarLayout);
    }

    /**
     *子类改变根布局内容布局填充方式重写次方法
     */
    @Override
    protected void addContentLayout() {
        ViewGroup smartRefreshLayout =  findViewById(R.id.smartRefreshLayout);
        getLayoutInflater().inflate(getContentLayoutId(),smartRefreshLayout);
    }

    private void initRefreshView(){
        smartRefreshLayout =  findViewById(R.id.smartRefreshLayout);
        smartRefreshLayout.setOnRefreshListener(this);
        multipleStatusView =  findViewById(R.id.multipleStatusView);
        multipleStatusView.setOnRetryClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                start();
            }
        });
    }



    @Override
    protected void initView() {

    }


   public abstract void setPresenter();

    @Override
    final public void onRefresh(RefreshLayout refreshlayout) {
        start();
    }

    final public void finishRefresh(){
        smartRefreshLayout.finishRefresh();
    }

    final public void setEnableRefresh(boolean isEnable){
        smartRefreshLayout.setEnableRefresh(isEnable);
    }

    @Override
    public void showContent() {
        multipleStatusView.showContent();
    }

    @Override
    public void showLoading() {
        multipleStatusView.showLoading();
    }

    @Override
    public void dismissLoading() {
        multipleStatusView.showContent();

    }

 /*   @Override
    public void showNetError() {
        multipleStatusView.showNoNetwork();

    }*/


  /*  @Override
    public void showContent() {
        multipleStatusView.showContent();
        finishRefresh();
    }
*/
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

  private void finishLoading(){
      finishRefresh();
  }

    @Override
    protected View getToolBar() {
        return new MyToolBar(this);
    }

    @Override
    protected void setToolBar(View toolBar) {
       MyToolBar myToolBar  = (MyToolBar) toolBar;
       myToolBar.getTitleView().setTextColor(Color.RED);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();


    }


}



