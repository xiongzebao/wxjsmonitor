package cn.net.yto.module_info.mvp.presenter

import android.util.Log
import cn.framework.commenlibrary.model.PageInfo
import cn.framework.commenlibrary.model.PageObj
import cn.net.yto.baselibrary.base.PartResponseObserver
import cn.net.yto.baselibrary.base.ResponseObserver
import cn.net.yto.baselibrary.mvp.BasePresenter
import cn.net.yto.module_info.mvp.contract.InfoListContract
import cn.net.yto.module_info.mvp.model.InfoListModel
import cn.net.yto.module_info.mvp.model.MocModel
import cn.net.yto.module_info.mvp.model.bean.BaseResp
import cn.net.yto.module_info.mvp.model.bean.InfoListBean



class InfoListPresenter : BasePresenter<InfoListContract.View>(), InfoListContract.Presenter {

    private var pageInfo: PageObj<InfoListBean.InfoItemBean>? = null

    private val infoListModel: InfoListModel by lazy {
        InfoListModel()
    }

    override fun reqInfoList(isLoadMore:Boolean) {
        if(!isLoadMore){
            pageInfo=null
        }
        val curPage = pageInfo?.page?.curPage ?: 0
        if(pageInfo!=null&&!pageInfo!!.page.hasNextPage()){
            return
        }

        infoListModel.requestInfoList(curPage+1).subscribe( object : ResponseObserver<BaseResp<PageObj<InfoListBean.InfoItemBean>>>(mRootView) {
            override fun onNext(t: BaseResp<PageObj<InfoListBean.InfoItemBean>>) {
                pageInfo = t.datas
                Log.e("xiong",t.datas.toString())
                mRootView?.setInfoListData(t.datas.page.dataList, t.datas.page.curPage!=1)
                mRootView?.disableLoadMore(t.datas.page.hasNextPage())
            }
        })
    }


    override fun reqPraise(  id:Int) {
        infoListModel.requestPraise(id).subscribe( object : PartResponseObserver<BaseResp<Object>>() {
            override fun onNext(t: BaseResp<Object>) {
               mRootView?.setPraiseStat()
            }
        })
    }

    
    }







