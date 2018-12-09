package cn.net.yto.module_sign.utils;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;

import cn.net.yto.baselibrary.base.BaseAppUtils;
import cn.net.yto.module_sign.Receiver.MyReceiver;
import cn.net.yto.module_sign.event.MessageEvent;

public class BradCastUtils {
    public static final String TYPE = "type";
    public static final String MSG = "msg";
    public static final String MYRECEIVER_ACTION = "cn.net.yto.module_sign.MY_BROADCAST";//cn.net.yto.module_sign.MY_BROADCAST
    public static LocalBroadcastManager mLocalBroadcastManager;
    public static String PERMISSIOM = "cn.net.yto.module_sign.RECV_MYBC";

    public static void send(Context context, int type, String title, String msg) {
        Intent intent = new Intent(MYRECEIVER_ACTION);

        intent.setComponent(new ComponentName("cn.net.yto.module_sign.Receiver",
                "cn.net.yto.module_sign.Receiver.MyReceiver"));
        // intent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
        intent.putExtra("msg", msg);
        intent.putExtra("type", type);
        intent.putExtra("title", title);
        context.sendBroadcast(intent, PERMISSIOM);

    }

    public static void register(Context context, BroadcastReceiver recevier, IntentFilter intentFilter) {
//        if(mLocalBroadcastManager==null){
//            mLocalBroadcastManager = LocalBroadcastManager.getInstance(context);//获得实例
//        }
//        mLocalBroadcastManager.registerReceiver(recevier, intentFilter);//注册监听
        context.registerReceiver(recevier, intentFilter, PERMISSIOM, null);
    }








    public static void registerNoticeBroadCast(Context context) {
        IntentFilter intentFilter = new IntentFilter(BradCastUtils.MYRECEIVER_ACTION);
        BradCastUtils.register(context, new MyReceiver(), intentFilter);
    }
}
