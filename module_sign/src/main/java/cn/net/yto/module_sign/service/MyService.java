package cn.net.yto.module_sign.service;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.github.nkzawa.emitter.Emitter;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Timer;
import java.util.TimerTask;

import cn.net.yto.baselibrary.base.BaseAppUtils;
import cn.net.yto.module_sign.MainActivity;
import cn.net.yto.module_sign.R;
import cn.net.yto.module_sign.Receiver.Action;
import cn.net.yto.module_sign.Receiver.MyReceiver;
import cn.net.yto.module_sign.event.MessageEvent;
import cn.net.yto.module_sign.utils.BradCastUtils;
import cn.net.yto.module_sign.utils.MonitorUtils;
import cn.net.yto.module_sign.utils.NoticeManager;
import xiaofei.library.hermeseventbus.HermesEventBus;

public class MyService extends Service {

    public static final String TAG = "MyService";

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();


        MonitorUtils.init();
        MonitorUtils.addEventListener("message", new Emitter.Listener() {
            @Override
            public void call(Object... args) {

                JSONObject jsonObject = (JSONObject) args[0];
                String msg = "";
                String title = "";
                int type = 0;
                try {
                    msg = jsonObject.getString("msg");
                    type = jsonObject.getInt("type");
                    title = jsonObject.getString("title");

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                Log.e("xiong","service"+msg);
              //  NoticeManager.startNotification(MyService.this,title,msg,NoticeManager.EXCEPTION);
              //  BradCastUtils.send(MyService.this, type, title,msg);

                MessageEvent event = new MessageEvent(type,title,msg);

                Intent intent = new Intent(Action.ACTION_LOCAL_MESSAGE_EVENT);
                intent.putExtra("MessageEvent",event);

                LocalBroadcastManager.getInstance(MyService.this).sendBroadcast(intent);

                NoticeManager.startNotification(MyService.this, title,msg,"chananna","消息通道",NotificationManager.IMPORTANCE_HIGH);

            }
        });


       startForegroundService();

    }

    private void startForegroundService(){

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("fore_service", "前台服务", NotificationManager.IMPORTANCE_HIGH);
            NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannel(channel);

            Intent intentForeSerive = new Intent(this, MainActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intentForeSerive, 0);
            Notification notification = new NotificationCompat.Builder(this, "fore_service")
                    .setContentTitle("小程序监视服务")
                    .setContentText("正在运行中")
                    .setWhen(System.currentTimeMillis())
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                    .setContentIntent(pendingIntent)
                    .build();
            startForeground(1, notification);
        }else{

        }

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.w(TAG, "in onStartCommand");
        Log.w(TAG, "MyService:" + this);


        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("xiong", "Service in onDestroy");
    }


}