package cn.net.yto.module_info.net;



import cn.net.yto.baselibrary.net.RetrofitManager;
import cn.net.yto.module_info.api.ApiService;
import cn.net.yto.module_info.api.UriConstant;


public class Request {

    public static ApiService service = null;
    static {
        RetrofitManager retrofitManager = new RetrofitManager();
       service =  retrofitManager.createApiService(UriConstant.INSTANCE.getBASE_URL(), ApiService.class);
    }

}
