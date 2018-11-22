package cn.net.yto.module_info.fragment.adapter;


import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import cn.net.yto.module_info.R;
import cn.net.yto.module_info.mvp.model.bean.InfoListBean;


public class InfoListAdapter extends BaseQuickAdapter<InfoListBean.InfoItemBean, BaseViewHolder> {

    public InfoListAdapter(int layoutResId) {
        super(layoutResId);

    }

    public InfoListAdapter(int layoutResId, List data) {
        super(layoutResId, data);

    }

    @Override
    protected void convert(BaseViewHolder helper, InfoListBean.InfoItemBean item) {
        helper.setText(R.id.title, item.getTitle());
        helper.setText(R.id.subTitle, item.getSubTitle());
        // 加载网络图片
        Glide.with(mContext).load(item.getPicUrl()).into((ImageView) helper.getView(R.id.coverImg));

    }
}

