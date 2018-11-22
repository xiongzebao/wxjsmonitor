package cn.net.yto.module_sign.ui.fragment

import cn.net.yto.baselibrary.base.BaseFragment
import cn.net.yto.module_sign.R
import com.alibaba.android.arouter.facade.annotation.Route

@Route(path = "/module_sign/sign_fragment")
class SignFragment:BaseFragment(){
    override fun attachPresenter() {

    }

    override fun getContentLayoutId(): Int {
        return R.layout.module_sign_act_main
    }

    override fun initView() {
        setToolBarVisibility(true)
    }

    override fun initData() {

    }

    override fun initListener() {

    }

    override fun start() {

    }

}