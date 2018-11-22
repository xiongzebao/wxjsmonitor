package cn.net.yto.module_info.mvp.model


import cn.net.yto.baselibrary.rx.scheduler.SchedulerUtils
import cn.net.yto.module_info.mvp.model.bean.HomeBean
import cn.net.yto.module_info.net.Request

import io.reactivex.Observable


class HomeModel{

    /**
     * 获取首页 Banner 数据
     */

  fun requestHomeData(num:Int): Observable<HomeBean> {

     return  Request.service.getFirstHomeData(10).compose(SchedulerUtils.ioToMain())

    }


}
