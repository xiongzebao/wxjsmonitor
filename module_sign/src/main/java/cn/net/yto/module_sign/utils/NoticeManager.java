package cn.net.yto.module_sign.utils;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.support.v4.app.NotificationCompat;


import java.security.PublicKey;
import java.util.Random;

import cn.net.yto.module_sign.MainActivity;
import cn.net.yto.module_sign.R;

public class NoticeManager {

    @TargetApi(Build.VERSION_CODES.O)
    private static void createNotificationChannel(Context context,String channelId, String channelName, int importance) {
        NotificationChannel channel = new NotificationChannel(channelId, channelName, importance);
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.createNotificationChannel(channel);
    }

    //开启通知
    public static void startNotification(Context context,String title, String content,String ChannelId,String ChannelName,int importance) {
        createNotificationChannel(context,ChannelId,ChannelName,importance);
        NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        if (mNotificationManager !=null) {
            mNotificationManager.notify(getRandom(),getDefaultNotification(context,title,content,ChannelId));
        }
    }


    public static Notification getNotification(PendingIntent pendingIntent,Context context,String title, String content, String channelId, int smallIconDrawableId, int largeIconDrawableId){

        NotificationCompat.Builder builder=null;
       if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            builder = new NotificationCompat.Builder(context, channelId);

       } else {
           builder = new NotificationCompat.Builder(context);
       }
        Notification notification =   builder.setContentTitle(title)
                .setContentText(content)
                .setContentIntent(pendingIntent)
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(smallIconDrawableId)
                .setPriority(Notification.PRIORITY_HIGH)
                .setLargeIcon(BitmapFactory.decodeResource(context.getResources(), largeIconDrawableId))
                .setAutoCancel(true)
                .build();

        return notification;
    }

    public static  Notification getDefaultNotification(Context context,String title, String content, String channelId){
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0,
                new Intent(context, MainActivity.class), 0);
        Notification notification = getNotification(pendingIntent,context,title,content,channelId,R.mipmap.ic_launcher_round,R.mipmap.ic_launcher_round);
        return notification;
   }

    private static int getRandom(){
        Random random = new Random();
        return random.nextInt(100);
    }
}
