package com.example.myphone.presenter;

import com.example.myphone.callback.IHomeCallback;

/**
 * 数据请求组件
 * 请求首页商品信息
 */
public interface IHomePresenter{
    /**
     * 获取商品信息
     */
    void getCommodities();

    void registerCallback(IHomeCallback callback);

    void unregisterCallback();
}
