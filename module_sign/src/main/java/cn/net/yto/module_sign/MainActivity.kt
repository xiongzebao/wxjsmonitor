package cn.net.yto.module_sign

import cn.net.yto.baselibrary.base.BaseActivity
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import kotlinx.android.synthetic.main.module_sign_act_main.*

@Route(path = "/module_sign/main")
class MainActivity : BaseActivity() {
    override fun setTopTitle(): String {
            return "签到"
    }


    override fun setPresenter() {
        ARouter.getInstance().inject(this)  // Start auto inject.
    }

    override fun getContentLayoutId(): Int {
     return R.layout.module_sign_act_main
    }

    override fun initView() {

    }

    override fun initData() {

    }

    override fun initListener() {
        jumpToInfoActivity.setOnClickListener({


            ARouter.getInstance().build( "/module_info/main") .withString("name", "老王") .withInt("age", 18) .withString("url", "https://a.b.c").navigation();


        })
    }


}
