package cn.net.yto.baselibrary.base;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.view.KeyEvent;
import android.widget.Toast;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Stack;


/*
*
* 框架层的帮助类，应用层无法访问，BaseAppUtils暴露应用层的访问接口
*
* */
final class BaseAppHelper {

    private BaseFragment fragment;
    private Stack actStack = new Stack();

    private static BaseAppHelper baseAppHelper=null;

    private BaseAppHelper(){

    }

    public static BaseAppHelper  Instance(){
        if(baseAppHelper==null){
            synchronized (BaseAppHelper.class){
                if(baseAppHelper==null){
                    baseAppHelper= new BaseAppHelper();
                    return baseAppHelper;
                }
            }
        }
        return baseAppHelper;
    }


    public BaseFragment getFragment() {
        return fragment;
    }

    public void setFragment(BaseFragment fragment) {
        this.fragment = fragment;
    }

    public Activity getActivity() {
        if(actStack.isEmpty()){
            return null;
        }
       Activity  act= (Activity) actStack.peek();
        return act;
    }


    public  Activity pushActivity(Activity act){
        return (Activity) actStack.push(act);
    }

    public  Activity popActivity( ){
           return (Activity) actStack.pop();
    }

    public   void closeApp(){
        for (int i=actStack.size();i>=0;i--){
           Activity act = (Activity) actStack.pop();
           if(act!=null){
               act.finish();
           }
        }
    }


    public   void forceStopAPK(){
        String pkgName = getAppProcessName();
        Process sh = null;
        DataOutputStream os = null;
        try {
            sh = Runtime.getRuntime().exec("su");
            os = new DataOutputStream(sh.getOutputStream());
            final String Command = "am force-stop "+pkgName+ "\n";
            os.writeBytes(Command);
            os.flush();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try {
            sh.waitFor();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }



    public String getAppVersion( String packname){
        //包管理操作管理类
        Context context = getActivity();
        PackageManager pm = context.getPackageManager();
        try {
            PackageInfo packinfo = pm.getPackageInfo(packname, 0);

            return packinfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();

        }
        return packname;
    }
    public  String getAppProcessName() {
        Context context = getActivity();
        //当前应用pid
        int pid = android.os.Process.myPid();
        //任务管理类
        ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        //遍历所有应用
        List<ActivityManager.RunningAppProcessInfo> infos = manager.getRunningAppProcesses();
        for (ActivityManager.RunningAppProcessInfo info : infos) {
            if (info.pid == pid)//得到当前应用
                return info.processName;//返回包名
        }
        return "";
    }



    private long mExitTime;

    public   boolean doubleBackToCloseApp(int keyCode){

        //判断用户是否点击了“返回键”
        if (keyCode == KeyEvent.KEYCODE_BACK) { //与上次点击返回键时刻作差
            if ((System.currentTimeMillis() - mExitTime) > 2000) {
                //大于2000ms则认为是误操作，使用Toast进行提示
                Toast.makeText(getActivity(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
                // 并记录下本次点击“返回键”的时刻，以便下次进行判断
                mExitTime = System.currentTimeMillis();
            } else { //小于2000ms则认为是用户确实希望退出程序-调用System.exit()方法进行退出
                // System.exit(0);
                BaseAppUtils.closeApp();
            }
            return true;
        }
        return false;

    }

}
