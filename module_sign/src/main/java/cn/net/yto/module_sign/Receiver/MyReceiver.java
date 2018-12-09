package cn.net.yto.module_sign.Receiver;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import cn.net.yto.baselibrary.base.BaseAppUtils;
import cn.net.yto.module_sign.service.MyService;
import cn.net.yto.module_sign.utils.NoticeManager;

public class MyReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String msg = intent.getStringExtra("msg");
        int type = intent.getIntExtra("type",0);
        String title = intent.getStringExtra("title");
        Log.e("xiong",msg);

        NoticeManager.startNotification(context, title,msg,"chananna","消息通道",NotificationManager.IMPORTANCE_HIGH);
    }



}
