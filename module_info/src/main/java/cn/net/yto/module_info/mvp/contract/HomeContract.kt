package cn.net.yto.module_info.mvp.contract



import cn.net.yto.baselibrary.mvp.IBaseView
import cn.net.yto.baselibrary.mvp.IPresenter
import cn.net.yto.module_info.mvp.model.bean.HomeBean


/**
 * Created by xuhao on 2017/11/8.
 * 契约类
 */

interface HomeContract {

    interface View : IBaseView {

        /**
         * 设置第一次请求的数据
         */
        fun setHomeData(homeBean: HomeBean)




    }

    interface Presenter : IPresenter<View> {

        /**
         * 获取首页精选数据
         */
        fun requestHomeData(num: Int)



    }


}