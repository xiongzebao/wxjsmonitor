package cn.net.yto.module_info.mvp.contract


import cn.net.yto.baselibrary.mvp.BaseView
import cn.net.yto.baselibrary.mvp.IPresenter
import cn.net.yto.module_info.mvp.model.bean.InfoListBean


interface InfoListContract{

    interface View : BaseView {
        fun disableLoadMore(isCanLoadMore: Boolean)
        fun setInfoListData(infoList: List<InfoListBean.InfoItemBean>, isLoadMore: Boolean )
        fun setPraiseStat()
    }

    interface Presenter : IPresenter<View> {

        fun reqInfoList(isLoadMore: Boolean)
        fun reqPraise( id:Int)

    }
}