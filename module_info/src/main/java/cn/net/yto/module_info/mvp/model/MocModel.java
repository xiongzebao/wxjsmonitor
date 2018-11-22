package cn.net.yto.module_info.mvp.model;


import java.util.ArrayList;
import java.util.List;

import cn.framework.commenlibrary.model.PageObj;
import cn.net.yto.baselibrary.rx.scheduler.SchedulerUtils;
import cn.net.yto.module_info.mvp.model.bean.BaseResp;
import cn.net.yto.module_info.mvp.model.bean.InfoListBean;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

public class MocModel {
    static public  class InfoListModel extends  cn.net.yto.module_info.mvp.model.InfoListModel{


        /**
         * 获取首页 Banner 数据
         */
        public Observable<BaseResp<PageObj<InfoListBean.InfoItemBean>>> requestInfoList(final int curPAge){

/*

            return  Observable.create(new ObservableOnSubscribe<BaseResp<InfoListBean>>() {

                @Override
                public void subscribe(ObservableEmitter<BaseResp<InfoListBean>> e) throws Exception {
                    List<InfoListBean.InfoItemBean> list = new ArrayList<InfoListBean.InfoItemBean>();


                    for (int i=0;i<5;i++){
                        list.add(new InfoListBean.InfoItemBean("title"+curPAge,"fdfdsf","fdfsdfs","fsfsf"));
                    }

                    InfoListBean infoListBean = new InfoListBean(list);
                    BaseResp baseResp = new BaseResp("200","success",infoListBean);
                    e.onNext(baseResp);
                    e.onComplete();
                }
            }).compose(SchedulerUtils.INSTANCE.<BaseResp<InfoListBean>>ioToMain());*/

            return  null;

        }

        public Observable<BaseResp<Object>> requestPraise(int id){
            return null;
        }

    }

}
