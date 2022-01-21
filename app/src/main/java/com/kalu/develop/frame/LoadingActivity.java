package com.kalu.develop.frame;

import lib.kalu.frame.mvvm.BaseActivity;
import lib.kalu.frame.mvvm.standard.StandardPresenter;

/**
 * @author zhanghang
 * @description: 启动页
 * @date :2022-01-17
 */
public class LoadingActivity extends BaseActivity<LoadingViewModel, StandardPresenter> {

    @Override
    public int initLayout() {
        return R.layout.activity_loading;
    }

    @Override
    public void initData() {
    }
}
