package com.example.module_main

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.view.KeyEvent
import cn.net.yto.baselibrary.base.YTOActivity
import cn.net.yto.baselibrary.widget.DialogUtils
import com.alibaba.android.arouter.launcher.ARouter
import com.example.module_main.mvp.model.bean.TabEntity
import com.flyco.tablayout.listener.CustomTabEntity
import com.flyco.tablayout.listener.OnTabSelectListener
import kotlinx.android.synthetic.main.act_main_layout.*


class MainActivity1 : YTOActivity() {


    //默认为0
    private var mIndex = 2
    private val mTitles = arrayOf("每日精选", "发现", "热门", "我的")

    // 未被选中的图标
    private val mIconUnSelectIds = intArrayOf(R.mipmap.ic_home_normal, R.mipmap.ic_discovery_normal, R.mipmap.ic_hot_normal, R.mipmap.ic_mine_normal)
    // 被选中的图标
    private val mIconSelectIds = intArrayOf(R.mipmap.ic_home_selected, R.mipmap.ic_discovery_selected, R.mipmap.ic_hot_selected, R.mipmap.ic_mine_selected)


    private val mTabEntities = ArrayList<CustomTabEntity>()
    private var InfoFragment :Fragment?=null;
    private var SignFragment :Fragment?=null
    private var MeFragment :Fragment?=null

    override fun getContentLayoutId(): Int {
        return R.layout.act_main_layout
    }

    override fun initListener() {

    }

    override fun initView() {
        initTab()
        tab_layout.currentTab = mIndex
        switchFragment(mIndex)
    }

    override fun initData() {

    }

    override fun start() {

    }







    //初始化底部菜单
    private fun initTab() {
        (0 until mTitles.size)
                .mapTo(mTabEntities) { TabEntity(mTitles[it], mIconSelectIds[it], mIconUnSelectIds[it]) }
        //为Tab赋值
        tab_layout.setTabData(mTabEntities)
        tab_layout.setOnTabSelectListener(object : OnTabSelectListener {
            override fun onTabSelect(position: Int) {
                //切换Fragment
                switchFragment(position)
            }

            override fun onTabReselect(position: Int) {

            }
        })
    }

    /* */
    /**
     * 切换Fragment
     * @param position 下标
     */
    private fun switchFragment(position: Int) {
       // InfoFragment = ARouter.getInstance().build("/module_info/info_fragment").navigation() as Fragment?
      //  SignFragment = ARouter.getInstance().build("/module_sign/sign_fragment").navigation() as Fragment?

        val transaction = supportFragmentManager.beginTransaction()
        hideFragments(transaction)
        when (position) {
            0 // 首页
            -> SignFragment?.let {
                transaction.show(it)
            }?:ARouter.getInstance().build("/module_sign/sign_fragment").navigation().let {
                SignFragment = it as Fragment?
                transaction.add(R.id.fl_container, it, "sign")
            }

            1  //发现
            -> InfoFragment?.let {
                transaction.show(it)
            }?:ARouter.getInstance().build("/module_info/info_fragment").navigation().let {
                InfoFragment = it as Fragment?
                transaction.add(R.id.fl_container, it, "info")
            }
            2  //发现
            -> MeFragment?.let {
                transaction.show(it)
            }?:ARouter.getInstance().build("/module_sign/sign_fragment1").navigation().let {
                MeFragment = it as Fragment?
                transaction.add(R.id.fl_container, it, "infosii")
            }
        }
        mIndex = position
        tab_layout.currentTab = mIndex
        transaction.commitAllowingStateLoss()
    }

    /**
     * 隐藏所有的Fragment
     * @param transaction transaction
     */
    private fun hideFragments(transaction: FragmentTransaction) {
        InfoFragment?.let { transaction.hide(it) }
        SignFragment?.let { transaction.hide(it) }
        MeFragment?.let { transaction.hide(it) }

    }


/*
    override fun onSaveInstanceState(outState: Bundle) {
//        showToast("onSaveInstanceState->"+mIndex)
//        super.onSaveInstanceState(outState)
        //记录fragment的位置,防止崩溃 activity被系统回收时，fragment错乱
        if (tab_layout != null) {
            outState.putInt("currTabIndex", mIndex)
        }
    }
*/


    private var mExitTime: Long = 0

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (System.currentTimeMillis().minus(mExitTime) <= 2000) {
                finish()
            } else {
                mExitTime = System.currentTimeMillis()
                DialogUtils.showToast("再按一次退出程序")
            }
            return true
        }
        return super.onKeyDown(keyCode, event)
    }


}
