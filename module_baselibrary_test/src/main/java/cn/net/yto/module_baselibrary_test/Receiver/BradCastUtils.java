package cn.net.yto.module_baselibrary_test.Receiver;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.UserHandle;

public class BradCastUtils {
    public static final String TYPE = "type";
    public static final String MSG = "msg";
    public static final String MYRECEIVER_ACTION = "cn.net.yto.module_sign.MY_BROADCAST";//cn.net.yto.module_sign.MY_BROADCAST

    public static void send(Context context,int type, String  msg){
        Intent intent = new Intent(MYRECEIVER_ACTION);
        intent.setComponent(new ComponentName("cn.net.yto.module_sign.Receiver",
                "cn.net.yto.module_sign.Receiver.MyReceiver"));
       // intent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
        intent.putExtra("msg", msg);
        intent.putExtra("type",type);
      //context.sendBroadcast(intent,"cn.net.yto.module_sign.RECV_MYBC");
        context.sendBroadcast(intent);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//            context.sendBroadcastAsUser(intent ,UserHandle.getUserHandleForUid(1));
//        }
    }
}
