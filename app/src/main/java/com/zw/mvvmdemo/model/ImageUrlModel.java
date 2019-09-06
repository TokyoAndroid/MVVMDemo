package com.zw.mvvmdemo.model;

import com.zw.mvvmdemo.bean.ImageUrlBean;
import com.zw.mvvmdemo.bean.UrlData;
import com.zw.mvvmdemo.http.GetUrlCallback;
import com.zw.mvvmdemo.http.HttpUtil;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ImageUrlModel {

    private HttpUtil mHttpUtil;

    public ImageUrlModel() {
        mHttpUtil = HttpUtil.getInstance();
    }

    public void getImageUrl(String format, int idx, int n, final GetUrlCallback callback) {
        mHttpUtil.getImageUrl(format, idx, n)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ImageUrlBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ImageUrlBean imageUrlBean) {
                        UrlData<ImageUrlBean.UrlBean> data =
                                new UrlData<>(imageUrlBean.getImages().get(0), null);
                        callback.handleUrl(data);
                    }

                    @Override
                    public void onError(Throwable e) {
                        UrlData<ImageUrlBean.UrlBean> data = new UrlData<>(null, e.getMessage());
                        callback.handleUrl(data);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
