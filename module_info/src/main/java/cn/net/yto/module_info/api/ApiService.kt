
package   cn.net.yto.module_info.api

import cn.framework.commenlibrary.model.PageInfo
import cn.framework.commenlibrary.model.PageObj
import cn.net.yto.module_info.mvp.model.bean.BaseResp
import cn.net.yto.module_info.mvp.model.bean.HomeBean
import cn.net.yto.module_info.mvp.model.bean.InfoListBean
import io.reactivex.Observable
import retrofit2.http.*


interface ApiService{

    /**
     * 首页精选
     */
    @GET("v2/feed?")
    fun getFirstHomeData(@Query("num") num:Int): Observable<HomeBean>

    @FormUrlEncoded
    @POST("/wxjs/public/info/querylist")
    fun getInfoList(@Field ("curPage") curPage:Int):Observable<BaseResp<PageObj<InfoListBean.InfoItemBean>>>

    @POST("/wxjs/public/info/praise")
    fun getPraise():Observable<BaseResp<Object>>

}