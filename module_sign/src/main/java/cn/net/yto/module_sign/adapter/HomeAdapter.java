package cn.net.yto.module_sign.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import cn.net.yto.module_sign.R;
import cn.net.yto.module_sign.event.MessageEvent;

public class HomeAdapter extends BaseQuickAdapter<MessageEvent, BaseViewHolder> {
    public HomeAdapter(int layoutResId, List data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MessageEvent item) {
        helper.setText(R.id.tv_title,item.getTitle());
        helper.setText(R.id.tv_message,item.getMessage());
    }
}
