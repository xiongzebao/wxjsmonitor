package cn.net.yto.module_baselibrary_test

import android.view.ViewGroup
import cn.net.yto.baselibrary.base.BaseActivity
import cn.net.yto.baselibrary.base.BaseAppUtils
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    override fun setRootView(): ViewGroup {
        return super.setRootView()
    }

    override fun setTopTitle(): String {
         return "基础库测试"
    }



    override fun setPresenter() {

    }

    override fun getContentLayoutId(): Int {
      return   R.layout.activity_main
    }

    override fun initView() {

    }

    override fun initData() {

    }

    override fun initListener() {
        testBtn.setOnClickListener { BaseAppUtils.startActivity(TestActivity::class.java) }
    }


}
