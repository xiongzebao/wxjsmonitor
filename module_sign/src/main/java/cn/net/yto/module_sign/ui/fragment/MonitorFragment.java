package cn.net.yto.module_sign.ui.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.github.nkzawa.emitter.Emitter;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import cn.net.yto.baselibrary.base.BaseAppUtils;
import cn.net.yto.baselibrary.base.BaseFragment;
import cn.net.yto.module_sign.R;
import cn.net.yto.module_sign.Receiver.Action;
import cn.net.yto.module_sign.Receiver.MyReceiver;
import cn.net.yto.module_sign.adapter.HomeAdapter;
import cn.net.yto.module_sign.event.MessageEvent;
import cn.net.yto.module_sign.service.MyService;
import cn.net.yto.module_sign.utils.BradCastUtils;
import cn.net.yto.module_sign.utils.NoticeManager;
import cn.net.yto.module_sign.utils.SocketIOUtils;

public class MonitorFragment extends BaseFragment {

    private RecyclerView recyclerView;
    private List<MessageEvent> mDataList;
    IntentFilter filter;
    LocalBroadcastManager mLocalBroadcastManager;
    private  BaseQuickAdapter homeAdapter;

    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.e("xiong","receivedd");
            if (intent.getAction().equals(Action.ACTION_LOCAL_MESSAGE_EVENT)) {
                Log.e("xiong","id receive->"+Thread.currentThread().getId());
                MessageEvent messageEvent = (MessageEvent) intent.getSerializableExtra("MessageEvent");
                homeAdapter.addData(messageEvent);
            }
        }
    };

    @Override
    protected void attachPresenter() {

    }


    @Override
    protected int getContentLayoutId() {
        return R.layout.monitor;
    }

    @Override
    protected void initView() {
        Log.e("xiong","id->"+Thread.currentThread().getId());
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager( new GridLayoutManager(getActivity(),2));

        filter = new IntentFilter();
        filter.addAction(Action.ACTION_LOCAL_MESSAGE_EVENT);
        mLocalBroadcastManager = LocalBroadcastManager.getInstance(getActivity());//获得实例
        mLocalBroadcastManager.registerReceiver(receiver, filter);//注册监听

    }

    private void initAdapter() {
          homeAdapter = new HomeAdapter(R.layout.layout_home_item, mDataList);

        homeAdapter.openLoadAnimation();
        homeAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

            }
        });

        recyclerView.setAdapter(homeAdapter);
    }

    @Override
    protected void initData() {
        mDataList = new ArrayList<>();
        MessageEvent msg = new MessageEvent(1, "ii", "fdsfsdf");
        mDataList.add(msg);
        initAdapter();


    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void start() {

    }
}
