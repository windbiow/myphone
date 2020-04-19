package com.example.myphone.callback;

import com.example.myphone.model.domain.Commodity;

import java.util.List;

/**
 * 数据加载回调动作
 */
public interface IHomeCallback {
    /**
     * 数据加载成功
     * @param commodities
     */
    void onCategoriesLoaded(List<Commodity.DataBean> commodities);

    /**
     * 数据正在加载
     */
    void onLoading();

    /**
     * 数据加载出错
     */
    void onNetworkError();

    /**
     * 数据内容为空
     */
    void onEmpty();
}
