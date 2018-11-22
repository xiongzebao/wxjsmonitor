package cn.net.yto.baselibrary.utils;

import android.support.v7.app.AppCompatDelegate;

import cn.net.yto.baselibrary.Constants;

/**
 * Created by xiaoxiong on 2018/8/11.
 * 描述:
 * 路径:
 */
public class UserInfoUtils {

   public static int getNightMode(){
     return   PreferenceUtils.getIntValue(Constants.Preferences.Night_Mode, AppCompatDelegate.MODE_NIGHT_NO);
   }

   public static void setNightMode(int mode){
       PreferenceUtils.putShare(Constants.Preferences.Night_Mode,mode);
   }



}
