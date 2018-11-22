package cn.net.yto.baselibrary.widget;

import android.support.v7.app.AppCompatDialog;
import android.widget.TextView;
import android.widget.Toast;

import cn.net.yto.baselibrary.base.BaseAppUtils;

public class DialogUtils {
    public static void showDialog(String str){
        AppCompatDialog dialog = new   AppCompatDialog(BaseAppUtils.getActivity());
        TextView textView = new TextView(BaseAppUtils.getActivity());
        textView.setText(str);
        dialog.setContentView(textView);
        dialog.show();

    }

    public static void showToast(String msg){
        Toast.makeText(BaseAppUtils.getActivity(),msg,Toast.LENGTH_SHORT).show();
    }
}
