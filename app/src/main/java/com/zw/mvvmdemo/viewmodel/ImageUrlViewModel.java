package com.zw.mvvmdemo.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;

import com.zw.mvvmdemo.bean.ImageUrlBean;
import com.zw.mvvmdemo.bean.UrlData;
import com.zw.mvvmdemo.http.GetUrlCallback;
import com.zw.mvvmdemo.model.ImageUrlModel;

public class ImageUrlViewModel extends ViewModel {

    public static final int STATE_NO_PRE_IMAGE = 0; // 没有前一张图片的状态
    public static final int STATE_NORMAL_IMAGE = 1; // 图片正常获取的状态

    /**
     * 放在LiveData中的数据，会被View监听，
     * 当数据改变且View层属于可见状态，则会去通知View层更新数据
     */
    private MutableLiveData<UrlData<ImageUrlBean.UrlBean>> mData;

    // 用来当特殊情况下更新UI
    private MutableLiveData<Integer> mState;

    /**
     * 本例没有用到Context，
     * 若需要，则必须传Application的Context，达到与View层独立的目的。
     */
    private Context mContext;

    private ImageUrlModel model;

    private GetUrlCallback callback;

    private int index; // 当前获取的哪一张图片的索引

    public ImageUrlViewModel(){
        this(null);
    }

    public ImageUrlViewModel(Context context) {
        this.mContext = context != null ?
                context.getApplicationContext() : null;
        mData = new MutableLiveData<>();
        mState = new MutableLiveData<>();
        model = new ImageUrlModel();
        callback = new GetUrlCallback() {
            @Override
            public void handleUrl(UrlData<ImageUrlBean.UrlBean> data) {
                // ViewModel层仅仅处理数据，界面的改变逻辑由Activity去执行
                mData.setValue(data);
            }
        };
    }

    public MutableLiveData<UrlData<ImageUrlBean.UrlBean>> getImageUrl() {
        return mData;
    }

    public MutableLiveData<Integer> getState() {
        return mState;
    }

    public void loadImage() {
        model.getImageUrl("js", index, 1, callback);
    }

    public void loadNextImage() {
        index++;
        model.getImageUrl("js", index, 1, callback);
    }

    public void loadPreImage() {
        if(index == 0) {
            // 没有数据时，通过更改state的值，去通知View层更改数据
            mState.setValue(STATE_NO_PRE_IMAGE);
            mState.setValue(STATE_NORMAL_IMAGE);
            return;
        }
        index--;
        model.getImageUrl("js", index, 1, callback);
    }

}
