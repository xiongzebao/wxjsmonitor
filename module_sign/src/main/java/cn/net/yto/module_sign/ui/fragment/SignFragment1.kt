package cn.net.yto.module_sign.ui.fragment

import android.support.v7.app.AppCompatDelegate
import android.view.View
import cn.net.yto.baselibrary.base.BaseAppUtils
import cn.net.yto.baselibrary.base.BaseFragment
import cn.net.yto.baselibrary.base.YTOBaseActivity
import cn.net.yto.baselibrary.utils.UserInfoUtils
import cn.net.yto.module_sign.R
import com.alibaba.android.arouter.facade.annotation.Route

@Route(path = "/module_sign/sign_fragment1")
class SignFragment1:BaseFragment(){
    override fun attachPresenter() {

    }

    override fun getContentLayoutId(): Int {
        return R.layout.module_sign_act_main1
    }

    override fun initView() {
        setEnableRefresh(false)
    }

    override fun initData() {

    }

    override fun initListener() {
      val dayBtn=  rootView.findViewById<View>(R.id.dayBtn)
        dayBtn.setOnClickListener(){
            val act :YTOBaseActivity= BaseAppUtils.getActivity() as YTOBaseActivity;
            UserInfoUtils.setNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            act.setNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
        val nightBtn=  rootView.findViewById<View>(R.id.nightBtn)
        nightBtn.setOnClickListener(){
            UserInfoUtils.setNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            val act :YTOBaseActivity= BaseAppUtils.getActivity() as YTOBaseActivity;
            act.setNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }
    }

    override fun start() {

    }

}