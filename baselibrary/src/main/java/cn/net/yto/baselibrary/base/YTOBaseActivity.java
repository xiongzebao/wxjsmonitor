package cn.net.yto.baselibrary.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Window;


import cn.net.yto.baselibrary.utils.CleanLeakUtils;
import cn.net.yto.baselibrary.utils.UserInfoUtils;

/**
 * Created by xiaoxiong on 2018/7/29.
 * 描述:
 * 路径:
 */
public class YTOBaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        BaseAppHelper.Instance().pushActivity(this);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
       // getDelegate().setLocalNightMode(UserInfoUtils.getNightMode());
        Log.e("xiong",UserInfoUtils.getNightMode()+"---------");
        getDelegate().setDefaultNightMode(UserInfoUtils.getNightMode());
        super.onCreate(savedInstanceState);
        hideActionBar();



    }

    public void setNightMode(int mode){

        startActivity(new Intent(this,this.getClass()));
     //   overridePendingTransition(R.anim.animo_alph_close, R.anim.animo_alph_close);
        finish();
    }

    private void hideActionBar(){
        if (getSupportActionBar() != null){
            getSupportActionBar().hide();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.e("xiong",this.getClass().getSimpleName()+"Destroyed");
        BaseAppHelper.Instance().popActivity();
        CleanLeakUtils.INSTANCE.fixInputMethodManagerLeak(this);
        super.onDestroy();
    }
}
