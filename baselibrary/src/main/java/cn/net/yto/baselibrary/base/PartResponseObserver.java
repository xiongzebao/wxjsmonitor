package cn.net.yto.baselibrary.base;

import android.widget.Toast;

import com.hazz.kotlinmvp.net.exception.ErrorStatus;
import com.hazz.kotlinmvp.net.exception.NetExceptionHandle;

import cn.net.yto.baselibrary.utils.NetworkUtils;
import io.reactivex.observers.DefaultObserver;

/**
 * Created by xiaoxiong on 2018/7/28.
 * 描述:
 * 路径:
 */
public abstract class PartResponseObserver<T> extends DefaultObserver<T> {


    @Override
    public void onError(Throwable e) {
            BaseActivity baseActivity = BaseAppUtils.getBaseActivity();
            if(baseActivity==null){
                return;
            }
            NetExceptionHandle.INSTANCE.handleException(e);
            int code = NetExceptionHandle.INSTANCE.getErrorCode();
            if(code == ErrorStatus.SERVER_ERROR){
                Toast.makeText(BaseAppUtils.getActivity(),"系统繁忙，请稍后再试",Toast.LENGTH_SHORT).show();
                return;
            }
            if(code==ErrorStatus.NETWORK_ERROR){
                Toast.makeText(BaseAppUtils.getActivity(),"网络连接断开，请检查网络稍后再试",Toast.LENGTH_SHORT).show();
                return;
            }
    }

    @Override
    public void onComplete() {

    }

    @Override
    protected void onStart() {
        BaseActivity baseActivity = BaseAppUtils.getBaseActivity();
        if(baseActivity==null){
          return;
        }
        if(!NetworkUtils.isConnected()){
            Toast.makeText(BaseAppUtils.getActivity(),"网络连接断开，请检查网络稍后再试",Toast.LENGTH_SHORT).show();
            cancel();
            return;
        }
    }


}
