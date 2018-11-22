package cn.net.yto.baselibrary.mvp

import cn.net.yto.baselibrary.base.BaseAppUtils

interface BaseView:IBaseView {



    override fun showLoading() {
        val fragment = BaseAppUtils.getBaseFragment()
        val activity = BaseAppUtils.getBaseActivity()
        if (fragment != null) {
            fragment.showLoading()
        } else if (activity != null) {
            activity.showLoading()
        }
    }


   /* override fun showError(msg,code) {
        val fragment = BaseAppUtils.getBaseFragment()
        val activity = BaseAppUtils.getBaseActivity()
        if (fragment != null) {
            fragment.showError()
        } else if (activity != null) {
            activity.showError()
        }
    }

    override fun showNetError() {
        val fragment = BaseAppUtils.getBaseFragment()
        val activity = BaseAppUtils.getBaseActivity()
        if (fragment != null) {
            fragment.showNetError()
        } else if (activity != null) {
            activity.showNetError()
        }
    }*/

    override fun showError(errMsg: String, code: Int) {
        val fragment = BaseAppUtils.getBaseFragment()
        val activity = BaseAppUtils.getBaseActivity()
        if (fragment != null) {
            fragment.showError(errMsg,code)
        } else if (activity != null) {
            activity.showError(errMsg,code)
        }
    }

    override fun showContent() {
        val fragment = BaseAppUtils.getBaseFragment()
        val activity = BaseAppUtils.getBaseActivity()
        if (fragment != null) {
            fragment.showContent()
        } else if (activity != null) {
            activity.showContent()
        }
    }

    open override fun dismissLoading() {
        val fragment = BaseAppUtils.getBaseFragment()
        val activity = BaseAppUtils.getBaseActivity()
        if (fragment != null) {
            fragment.dismissLoading()
        } else if (activity != null) {
            activity.dismissLoading()
        }
    }

}