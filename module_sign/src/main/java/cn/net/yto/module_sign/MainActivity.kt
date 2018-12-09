package cn.net.yto.module_sign

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import cn.net.yto.baselibrary.base.BaseActivity
import cn.net.yto.baselibrary.base.YTOBaseActivity
import cn.net.yto.module_sign.service.MyService
import cn.net.yto.module_sign.utils.BradCastUtils
import cn.net.yto.module_sign.utils.NoticeManager
import cn.net.yto.module_sign.utils.SocketIOUtils
import com.alibaba.android.arouter.facade.annotation.Route
import org.greenrobot.eventbus.ThreadMode
import org.greenrobot.eventbus.Subscribe
import xiaofei.library.hermeseventbus.HermesEventBus


@Route(path = "/module_sign/main")
class MainActivity :  BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    override fun setPresenter() {

    }

    override fun getContentLayoutId(): Int {
      return R.layout.module_sign_act_main1
    }


    override fun initData() {
        initService()
      //  BradCastUtils.registerNoticeBroadCast(this)
    }

    private fun initService() {
        val serviceIntent = Intent(this, MyService::class.java)


        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            //android8.0以上通过startForegroundService启动service
            startForegroundService( serviceIntent);
        }else{
            startService( serviceIntent);
        }


    }


    override fun initListener() {

    }

    override fun setTopTitle(): String {
      return ""
    }



}
