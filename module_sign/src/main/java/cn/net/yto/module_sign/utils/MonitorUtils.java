package cn.net.yto.module_sign.utils;

import android.util.Log;

import com.github.nkzawa.emitter.Emitter;

import org.json.JSONException;
import org.json.JSONObject;

public class MonitorUtils {
    public static String url = "http://192.168.8.216:8888";
    public static String url1 = "http://xiongbin.nat300.top";
    public static String url2 = "http://xiongbin.top:8888";

   public static void init(){


       SocketIOUtils.instanceSocket(url2);
        SocketIOUtils.connect();
       SocketIOUtils.sendEvent("message", "hi,xiongbin");
    }

    public  static void addEventListener(String event,Emitter.Listener l){
        SocketIOUtils.addEventListener(event,l);
    }



}
