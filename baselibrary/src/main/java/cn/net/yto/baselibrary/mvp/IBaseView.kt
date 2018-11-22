
package cn.net.yto.baselibrary.mvp
/**
 * @author Jake.Ho
 * created: 2017/10/25
 * desc:
 */
interface IBaseView {

    fun showLoading()

    fun dismissLoading()

    fun showContent()
    //服务器内部错误
    fun showError(  errMsg:String, code:Int)

}
