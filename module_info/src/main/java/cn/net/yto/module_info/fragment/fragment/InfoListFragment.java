package cn.net.yto.module_info.fragment.fragment;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.chad.library.adapter.base.BaseQuickAdapter;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import cn.net.yto.baselibrary.base.BaseAppUtils;
import cn.net.yto.baselibrary.base.BaseFragment;
import cn.net.yto.baselibrary.base.WebViewActivity;
import cn.net.yto.module_info.R;
import cn.net.yto.module_info.fragment.adapter.InfoListAdapter;
import cn.net.yto.module_info.mvp.contract.InfoListContract;
import cn.net.yto.module_info.mvp.model.bean.InfoListBean;
import cn.net.yto.module_info.mvp.presenter.InfoListPresenter;

@Route(path = "/module_info/info_fragment")
public class InfoListFragment extends BaseFragment implements InfoListContract.View {

    InfoListPresenter presenter;
    RecyclerView recyclerView;
    InfoListAdapter adapter;

    @Override
    protected void attachPresenter() {
        presenter = new InfoListPresenter();
        presenter.attachView(this);

    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_infolist_layout;
    }

    @Override
    protected void initView() {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(BaseAppUtils.getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        if (adapter == null) {
            adapter = new InfoListAdapter(R.layout.item_infolist_layout, null);
            recyclerView.setAdapter(adapter);
            return;
        }
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                presenter.reqInfoList(true);
            }
        }, recyclerView);

        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(getContext(), WebViewActivity.class);
                InfoListBean.InfoItemBean item = (InfoListBean.InfoItemBean) adapter.getItem(position);
                intent.putExtra("content", item.getContent());
                startActivity(intent);
            }
        });
    }

    @Override
    protected void start() {
        presenter.reqInfoList(false);
    }


    @Override
    public void setPraiseStat() {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }

    @Override
    public void disableLoadMore(boolean isCanLoadMore) {
        adapter.setEnableLoadMore(isCanLoadMore);
        if (!isCanLoadMore) {
            adapter.loadMoreEnd();
        }
    }

    @Override
    public void setInfoListData(@NotNull List<InfoListBean.InfoItemBean> infoList, boolean isLoadMore) {
        if (isLoadMore) {
            adapter.addData(infoList);
        } else {
            adapter.setNewData(infoList);
        }
    }

    @Override
    public void showContent() {
        super.showContent();

    }


}
