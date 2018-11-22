package cn.net.yto.baselibrary.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;



public class BaseAppUtils  {

    public static Context getContext(){
        Activity activity = getActivity();
        if(activity!=null){
            return activity.getBaseContext();
        }
        return null;
    }


    public static BaseActivity getBaseActivity(){
         Activity act =   getActivity();
         BaseActivity baseActivity =null;
         if(act instanceof BaseActivity){
               baseActivity = (BaseActivity)act;
         }
         return baseActivity;
     }

    public  static Activity getActivity(){
        return BaseAppHelper.Instance().getActivity();
    }

    public static BaseFragment getBaseFragment(){
        return BaseAppHelper.Instance().getFragment();
    }

    public static void closeApp(){
        BaseAppHelper.Instance().closeApp();
    }

    public static void forceKillApp(){
        BaseAppHelper.Instance().forceStopAPK();
    }

    public static boolean doubleBackToCloseApp(int keycode){
         return BaseAppHelper.Instance().doubleBackToCloseApp(keycode);
    }

    public static void startActivity(Class classz){
         Activity activity = getActivity();
        Intent intent = new Intent(activity,classz);
        getActivity().startActivity(intent);
    }

}
