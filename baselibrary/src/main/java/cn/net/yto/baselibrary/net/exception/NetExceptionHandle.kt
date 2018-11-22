package com.hazz.kotlinmvp.net.exception

import cn.net.yto.baselibrary.BuildConfig
import com.google.gson.JsonParseException
import com.orhanobut.logger.Logger
import org.json.JSONException
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.text.ParseException

/**
 * Created by xuhao on 2017/12/5.
 * desc: 异常处理类
 */

object   NetExceptionHandle {
    var TAG = "NetExceptionHandle"
    var errorCode = ErrorStatus.UNKNOWN_ERROR
    var errorMsg = "请求失败，请稍后重试"


        fun handleException(e: Throwable): String {
            if (e is SocketTimeoutException
                ||e is ConnectException
                ||e is UnknownHostException

            ) {//均为网络异常
                errorMsg =if(BuildConfig.DEBUG) "网络异常:"+e.message.toString() else "网络异常"
                errorCode = ErrorStatus.NETWORK_ERROR
            }  else if (e is JsonParseException
                    || e is JSONException
                    || e is ParseException) {   //均视为解析错误
                errorMsg =if(BuildConfig.DEBUG) "数据解析异常:"+e.message.toString() else "数据解析异常"
                errorCode = ErrorStatus.SERVER_ERROR
            } else if (e is HttpException) {//服务器返回的错误信息
                errorMsg = e.message.toString()
                errorCode = ErrorStatus.SERVER_ERROR
            }  else if (e is IllegalArgumentException) {
                errorMsg = if (BuildConfig.DEBUG) "参数异常:" + e.message.toString() else "参数异常"
                errorCode = ErrorStatus.SERVER_ERROR
            }
            else {//未知错误
                errorMsg =if(BuildConfig.DEBUG) "未知错误:"+e.message.toString() else "未知错误"
                errorCode = ErrorStatus.UNKNOWN_ERROR
            }
            if(BuildConfig.DEBUG){
                Logger.e(TAG, errorMsg)
                e.printStackTrace()
            }
            return errorMsg
        }

}
