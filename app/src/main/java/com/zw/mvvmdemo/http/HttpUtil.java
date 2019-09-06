package com.zw.mvvmdemo.http;

import com.zw.mvvmdemo.bean.ImageUrlBean;

import io.reactivex.Observable;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class HttpUtil {

    private Retrofit mRetrifit;

    private HttpUtil(){
        mRetrifit = new Retrofit.Builder()
                .baseUrl(ImageUrlBean.UrlBean.BASE_IMAGE_ADDRESS_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public Observable<ImageUrlBean> getImageUrl(String format, int idx, int n) {
        return mRetrifit.create(IGetUrlService.class)
                .getUrl(format, idx, n);
    }

    public static HttpUtil getInstance() {
        return Holder.mInstance;
    }

    private static class Holder {
        private static final HttpUtil mInstance = new HttpUtil();
    }
}
