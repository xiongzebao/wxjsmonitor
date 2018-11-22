package cn.net.yto.baselibrary.base;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Looper;

import com.orhanobut.logger.Logger;

import java.lang.reflect.Field;
import java.util.Properties;

import cn.net.yto.baselibrary.widget.DialogUtils;

public class CrashHandler implements Thread.UncaughtExceptionHandler {

    /** Debug Log tag*/
    public static final String TAG = "CrashHandler";
    /** 是否开启日志输出,在Debug状态下开启,
     * 在Release状态下关闭以提示程序性能
     * */
    public static final boolean DEBUG = true;
    /** 系统默认的UncaughtException处理类 */
    private Thread.UncaughtExceptionHandler mDefaultHandler;
    /** CrashHandler实例 */
    private static CrashHandler INSTANCE;
    /** 程序的Context对象 */
    private Context mContext;
    /** 使用Properties来保存设备的信息和错误堆栈信息*/
    private Properties mDeviceCrashInfo = new Properties();
    private static final String VERSION_NAME = "versionName";
    private static final String VERSION_CODE = "versionCode";
    private static final String STACK_TRACE = "STACK_TRACE";
    /** 错误报告文件的扩展名 */
    private static final String CRASH_REPORTER_EXTENSION = ".cr";

    private static  Object syncRoot = new Object();

    /** 保证只有一个CrashHandler实例 */
    private CrashHandler() {}

    /** 获取CrashHandler实例 ,单例模式*/
    public static CrashHandler getInstance() {
       /* if (INSTANCE == null) {
            INSTANCE = new CrashHandler();
        }
        return INSTANCE;*/
        // 防止多线程访问安全，这里使用了双重锁
        if (INSTANCE == null)
        {
            synchronized (syncRoot)
            {
                if (INSTANCE == null)
                {
                    INSTANCE =  new CrashHandler();
                }
            }
        }
        return INSTANCE;
    }

    /**
     * 初始化,注册Context对象,
     * 获取系统默认的UncaughtException处理器,
     * 设置该CrashHandler为程序的默认处理器
     * @param ctx
     */
    public void init(Context ctx) {
        mContext = ctx;
        mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    /**
     * 当UncaughtException发生时会转入该函数来处理
     */
    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        if (!handleException(ex) && mDefaultHandler != null) {
            //如果用户没有处理则让系统默认的异常处理器来处理
            mDefaultHandler.uncaughtException(thread, ex);
        }
    }

    /**
     * 自定义错误处理,收集错误信息
     * 发送错误报告等操作均在此完成.
     * 开发者可以根据自己的情况来自定义异常处理逻辑
     * @param ex
     * @return true:如果处理了该异常信息;否则返回false
     */
    private boolean handleException(final Throwable ex) {
        if (ex == null) {
            Logger.e("handleException --- ex==null");
            return true;
        }
        final String msg = ex.getLocalizedMessage();
        if(msg == null) {
            return false;
        }
        //使用Toast来显示异常信息
        new Thread() {
            @Override
            public void run() {
                Looper.prepare();
              /*  if(DEBUG){
                    Logger.d(TAG, "异常信息->"+msg);
                    Toast toast = Toast.makeText(mContext, "程序出错，即将退出:\r\n" + msg,
                            Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                }*/
                Logger.e(msg);
                ex.printStackTrace();
                DialogUtils.showDialog(msg);
                Looper.loop();
            }
        }.start();
        return true;
    }





    /**
     * 收集程序崩溃的设备信息
     *
     * @param ctx
     */
    public void collectCrashDeviceInfo(Context ctx) {
        try {
            PackageManager pm = ctx.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(ctx.getPackageName(),
                    PackageManager.GET_ACTIVITIES);
            if (pi != null) {
                mDeviceCrashInfo.put(VERSION_NAME,
                        pi.versionName == null ? "not set" : pi.versionName);
                mDeviceCrashInfo.put(VERSION_CODE, ""+pi.versionCode);
            }
        } catch (PackageManager.NameNotFoundException e) {
            Logger.e(TAG, "Error while collect package info", e);
        }
        //使用反射来收集设备信息.在Build类中包含各种设备信息,
        //例如: 系统版本号,设备生产商 等帮助调试程序的有用信息
        //具体信息请参考后面的截图
        Field[] fields = Build.class.getDeclaredFields();
        for (Field field : fields) {
            try {
                field.setAccessible(true);
                mDeviceCrashInfo.put(field.getName(), ""+field.get(null));
                if (DEBUG) {
                    Logger.e(TAG, field.getName() + " : " + field.get(null));
                }
            } catch (Exception e) {
                Logger.e(TAG, "Error while collect crash info", e);
            }
        }
    }


}