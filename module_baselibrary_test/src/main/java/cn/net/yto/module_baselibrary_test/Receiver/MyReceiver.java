package cn.net.yto.module_baselibrary_test.Receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import cn.net.yto.baselibrary.base.BaseAppUtils;


public class MyReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e("xiong","oooooooooo --------");
        String msg = intent.getStringExtra("msg");
        Log.e("xiong","oooooooooo"+msg+"--------");
       // NoticeManager.startNotification(BaseAppUtils.getActivity(),"登录通知",msg);
    }
}
