package cn.net.yto.module_sign.utils;

import android.util.Log;

import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;


import java.net.URISyntaxException;

public class SocketIOUtils {
    public static String testUrl = "http://chat.socket.io";

    private static Socket mSocket;

    public static void instanceSocket(String url){
        try {
            mSocket = IO.socket(url);
        } catch (URISyntaxException e) {
            Log.e("xiong",e.getMessage());
        }
    }

    public static void connect(){
        if(mSocket==null){
            throw new IllegalArgumentException("please instanceSocket first");
        }
        mSocket.connect();
    }

    public static void  sendEvent(String event,String message){
        mSocket.emit(event, message);
    }

    public static void addEventListener(String event,Emitter.Listener onNewMessage ){
        mSocket.on(event, onNewMessage);
    }

    public static void disconnect(){
        mSocket.disconnect();
        mSocket.off();
    }

}
