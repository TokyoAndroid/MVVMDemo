package com.zw.mvvmdemo.adapter;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class GetBingImageAdapter {

    @BindingAdapter("url")
    public static void setImage(ImageView iv, String url) {
        Glide.with(iv)
                .load(url)
                .into(iv);
    }
}
