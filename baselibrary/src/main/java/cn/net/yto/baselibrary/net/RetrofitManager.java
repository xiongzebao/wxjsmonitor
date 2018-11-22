package cn.net.yto.baselibrary.net;

import android.util.Log;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitManager {
    private  OkHttpClient client=null;
    private Retrofit retrofit =null;
    private static String baseURl=null;
    private static RetrofitManager retrofitManager = null;
    private String TAG = "RetrofitManager";

    public <T> T createApiService(String baseURl,final Class<T> service){
        this.baseURl = baseURl;
         return getRetrofit().create(service);
    }

   Interceptor getLoggerInterceptor(){
       HttpLoggingInterceptor loginterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
           @Override
           public void log(String message) {
               try {
                   String text = URLDecoder.decode(message, "utf-8");
                   Log.e("OKHttp-----", text);
               } catch (UnsupportedEncodingException e) {
                   e.printStackTrace();
                   Log.w("OKHttp-----", message);
               }
           }
       });
       loginterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
       return loginterceptor;
   }

    private Retrofit getRetrofit(){
        if(retrofit==null){
            if(client==null){
                 client  = new OkHttpClient.Builder()
                         .addInterceptor(getLoggerInterceptor())
                         .connectTimeout(60L, TimeUnit.SECONDS)
                         .readTimeout(60L, TimeUnit.SECONDS)
                         .writeTimeout(60L, TimeUnit.SECONDS)
                         .build();
            }
            // 获取retrofit的实例
            retrofit = new  Retrofit.Builder()
                    .baseUrl(baseURl)  //自己配置
                    .client(client)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())

                    .build()

            ;


        }
        return  retrofit;
    }

}
