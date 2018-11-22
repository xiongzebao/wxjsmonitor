package cn.net.yto.baselibrary.base;

import com.hazz.kotlinmvp.net.exception.ErrorStatus;
import com.hazz.kotlinmvp.net.exception.NetExceptionHandle;

import cn.net.yto.baselibrary.R;
import cn.net.yto.baselibrary.mvp.IBaseView;

import cn.net.yto.baselibrary.utils.NetworkUtils;
import io.reactivex.observers.DefaultObserver;

/**
 * Created by xiaoxiong on 2018/7/28.
 * 描述:
 * 路径:
 */
public abstract class ResponseObserver<T> extends DefaultObserver<T> {
    IBaseView mRootView;

    public ResponseObserver(IBaseView v){
        mRootView = v;
    }

    @Override
    public void onError(Throwable e) {

        String msg=  NetExceptionHandle.INSTANCE.handleException(e);
            int code = NetExceptionHandle.INSTANCE.getErrorCode();
            if(mRootView!=null){
                  mRootView.showError(msg,code);
             }

    }

    @Override
    public void onComplete() {
        if(mRootView!=null){
            mRootView.dismissLoading();
            mRootView.showContent();
        }
    }

    @Override
    protected void onStart() {

        if(!NetworkUtils.isConnected()){
            if(mRootView!=null){
                mRootView.showError(BaseAppUtils.getActivity().getResources().getString(R.string.no_network),ErrorStatus.NETWORK_ERROR);
            }
            cancel();
            return;
        }
    }


}
