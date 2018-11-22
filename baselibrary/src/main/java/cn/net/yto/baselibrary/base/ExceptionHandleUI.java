package cn.net.yto.baselibrary.base;

import android.view.View;


import com.hazz.kotlinmvp.net.exception.ErrorStatus;
import com.hazz.kotlinmvp.net.exception.NetExceptionHandle;

import cn.net.yto.baselibrary.widget.MultipleStatusView;

/**
 * Created by xiaoxiong on 2018/7/27.
 * 描述:
 * 路径:
 */
public class ExceptionHandleUI {
  boolean  HandleExcetion(Throwable e, MultipleStatusView view, View.OnClickListener retryListener){
       String errorMsg  = NetExceptionHandle.INSTANCE.handleException(e);
       int errorCode = NetExceptionHandle.INSTANCE.getErrorCode();

      switch (errorCode){
          case ErrorStatus.NETWORK_ERROR:view.showNoNetwork();break;
          case ErrorStatus.SERVER_ERROR:view.showError();
          case ErrorStatus.UNKNOWN_ERROR:view.showError();
          case ErrorStatus.API_ERROR:view.showError();
      }

    return true;
  }
}
