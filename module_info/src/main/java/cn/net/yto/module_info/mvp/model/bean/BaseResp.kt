package cn.net.yto.module_info.mvp.model.bean

data class  BaseResp<T>(val code:String,val message:String,val datas:T){
    override fun toString(): String {
        return super.toString()
    }
}