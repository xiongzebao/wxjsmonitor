package cn.net.yto.module_info

import android.widget.Toast
import cn.net.yto.baselibrary.base.BaseActivity

import com.alibaba.android.arouter.facade.annotation.Route


@Route(path = "/module_info/main")
class MainActivity : BaseActivity() {
    override fun setTopTitle(): String {
        return "资讯"
    }

    override fun setPresenter() {

    }

    override fun getContentLayoutId(): Int {
     return R.layout.act_info_main
    }

    override fun initView() {

    }

    override fun initData() {
       val name =   intent.getStringExtra("name")
        val age = intent.getIntExtra("age",0)

        Toast.makeText(this, "$name:$age",Toast.LENGTH_LONG).show()

    }

    override fun initListener() {

    }


}
