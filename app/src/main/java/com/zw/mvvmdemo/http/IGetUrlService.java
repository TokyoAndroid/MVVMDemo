package com.zw.mvvmdemo.http;


import com.zw.mvvmdemo.bean.ImageUrlBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IGetUrlService {

    @GET("HPImageArchive.aspx")
    Observable<ImageUrlBean> getUrl(@Query("format") String format,
                                    @Query("idx") int idx,
                                    @Query("n") int n);
}
