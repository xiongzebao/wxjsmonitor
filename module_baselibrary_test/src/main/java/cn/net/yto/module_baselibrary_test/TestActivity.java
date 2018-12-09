package cn.net.yto.module_baselibrary_test;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.view.View;
import android.widget.Button;

import cn.net.yto.baselibrary.base.BaseActivity;
import cn.net.yto.module_baselibrary_test.Receiver.BradCastUtils;
import cn.net.yto.module_baselibrary_test.Receiver.MyReceiver;

public class TestActivity extends BaseActivity {
    private BroadcastReceiver recevier;
    private IntentFilter intentFilter;
    LocalBroadcastManager mLocalBroadcastManager;
    public static final String MYRECEIVER_ACTION = "cn.net.yto.module_sign.MY_BROADCAST";
    @Override
    public void setPresenter() {

    }

    @Override
    protected String setTopTitle() {
        return "teset";
    }

    @Override
    protected int getContentLayoutId() {
        setTheme(R.style.MyAppTheme);
        return R.layout.act_test;
    }

    @Override
    protected void initView() {
        Button button =  findViewById(R.id.send_broadcast);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // BradCastUtils.send(TestActivity.this,1,"测试消息");
                Intent intent = new Intent(MYRECEIVER_ACTION);
                intent.setComponent(new ComponentName("cn.net.yto.module_sign.Receiver",
                        "cn.net.yto.module_sign.Receiver.MyReceiver"));
                mLocalBroadcastManager.sendBroadcast(intent);
                //context.sendBroadcast(intent,"cn.net.yto.module_sign.RECV_MYBC");

            }
        });
    }

    @Override
    protected void initData() {

       recevier = new MyReceiver();
       intentFilter = new IntentFilter();
       intentFilter.addAction(BradCastUtils.MYRECEIVER_ACTION);
        mLocalBroadcastManager = LocalBroadcastManager.getInstance(this);//获得实例
        mLocalBroadcastManager.registerReceiver(recevier, intentFilter);//注册监听


    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(recevier);
    }
}
