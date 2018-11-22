package cn.net.yto.module_info.mvp.model

import cn.framework.commenlibrary.model.PageInfo
import cn.framework.commenlibrary.model.PageObj
import cn.net.yto.baselibrary.rx.scheduler.SchedulerUtils
import cn.net.yto.module_info.mvp.model.bean.BaseResp
import cn.net.yto.module_info.mvp.model.bean.InfoListBean
import cn.net.yto.module_info.net.Request
import io.reactivex.Observable


open class InfoListModel{


  open fun requestInfoList(curPage:Int): Observable<BaseResp<PageObj<InfoListBean.InfoItemBean>>> {

     return  Request.service.getInfoList(curPage).compose(SchedulerUtils.ioToMain())

    }

   open fun requestPraise( id:Int):Observable<BaseResp<Object>>{
        return  Request.service.getPraise().compose(SchedulerUtils.ioToMain())
    }


}
