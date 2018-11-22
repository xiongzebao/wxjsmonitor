package cn.net.yto.baselibrary.utils;

import android.content.Context;
import android.content.SharedPreferences;

import cn.net.yto.baselibrary.base.BaseAppUtils;

/**
 * Created by xiaoxiong on 2018/8/11.
 * 描述:
 * 路径:
 */
public class PreferenceUtils {

    private static final String app_key_group = "app";


    public static final int model_private = Context.MODE_PRIVATE;//Android7.0之后只支持MODE_PRIVATE,其他类型废弃

    public static int getIntValue(String key) {
        Context mContext = BaseAppUtils.getActivity();
        if (mContext == null) {
            return 0;
        }
        SharedPreferences sp = mContext.getSharedPreferences(app_key_group, model_private);
        try {
            return sp.getInt(key, -1);
        } catch (Exception ee) {
            String v = null;
            try {
                v = sp.getString(key, "");
            } catch (Exception e) {
            }
            if (v != null && v.length() > 0) {
                try {
                    return Integer.parseInt(v);
                } catch (Exception e) {
                }
            }
        }
        return 0;
    }

    public static int getIntValue(String key, int defaultValue) {
        Context mContext = BaseAppUtils.getActivity();
        if (mContext == null) {
            return defaultValue;
        }
        SharedPreferences sp = mContext.getSharedPreferences(app_key_group, model_private);
        try {
            return sp.getInt(key, defaultValue);
        } catch (Exception ee) {
            String v = null;
            try {
                v = sp.getString(key, "");
            } catch (Exception e) {
            }
            if (v != null && v.length() > 0) {
                try {
                    int vint = Integer.parseInt(v);
                    return vint;
                } catch (Exception e) {
                }
            }
        }
        return defaultValue;
    }

    public static boolean getBooleanValue(String key) {
        Context mContext = BaseAppUtils.getActivity();
        if (mContext == null) {
            return false;
        }
        SharedPreferences sp = mContext.getSharedPreferences(app_key_group, model_private);
        try {
            return sp.getBoolean(key, false);
        } catch (Exception ee) {
            String v = null;
            try {
                v = sp.getString(key, "");
            } catch (Exception e) {
                return false;
            }
            if (v != null && v.length() > 0) {
                try {
                    Boolean vBoolean = Boolean.valueOf(v);
                    return vBoolean;
                } catch (Exception e) {
                }
            }
            return false;
        }
    }

    public static Long getLongValue(String key) {
        Context mContext = BaseAppUtils.getActivity();
        if (mContext == null) {
            return null;
        }
        SharedPreferences sp = mContext.getSharedPreferences(app_key_group, model_private);
        try {
            return sp.getLong(key, -1);
        } catch (Exception ee) {
            String v = null;
            try {
                v = sp.getString(key, "");
            } catch (Exception e) {
            }
            if (v != null && v.length() > 0) {
                try {
                    return Long.parseLong(v);
                } catch (Exception e) {
                }
            }
        }
        return null;
    }

    public static String getShare(Context context, String key) {
        if (context == null) {
            Context mContext = BaseAppUtils.getActivity();
        }
        if (context == null) {
            return "";
        }
        SharedPreferences sp = context.getSharedPreferences(app_key_group, model_private);
        String value = sp.getString(key, "");
        return value;
    }

    public static void putShare(String key ,Object value){
        putShare(BaseAppUtils.getActivity(),key,value);
    }

    public static void putShare(Context context, String key, Object value) {

        if (context == null) {
            return;
        }
        SharedPreferences sp = context.getSharedPreferences(app_key_group, model_private);
        SharedPreferences.Editor spEditor = sp.edit();
        String v = null;
        if (value != null) {
            v = value.toString();
        }
        spEditor.putString(key, v);
        spEditor.commit();
    }



    //保存值并把key放到退出清空列表中
    public static void putShare(Context context, String key, Object value, boolean cleanLogout){
        putShare(context,key,value);

    }

    public static void removeShare(Context context, String key) {
        if (context == null) {
            context = BaseAppUtils.getActivity();
        }
        if (context == null) {
            return;
        }
        SharedPreferences sp = context.getSharedPreferences(app_key_group, model_private);
        SharedPreferences.Editor spEditor = sp.edit();
        spEditor.remove(key);
        spEditor.commit();
    }
}
