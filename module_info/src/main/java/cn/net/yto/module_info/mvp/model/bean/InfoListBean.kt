package cn.net.yto.module_info.mvp.model.bean

/**
 * Created by xiaoxiong on 2018/7/26.
 * 描述:
 * 路径:
 */
data class InfoListBean(val list:List<InfoItemBean>){

    data class InfoItemBean(val title:String,val subTitle:String,val content:String,val picUrl:String)


}