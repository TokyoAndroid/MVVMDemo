package com.zw.mvvmdemo.http;

import com.zw.mvvmdemo.bean.UrlData;
import com.zw.mvvmdemo.bean.ImageUrlBean;

public interface GetUrlCallback {

    void handleUrl(UrlData<ImageUrlBean.UrlBean> data);

}
