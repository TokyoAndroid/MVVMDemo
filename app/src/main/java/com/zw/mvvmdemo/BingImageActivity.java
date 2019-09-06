package com.zw.mvvmdemo;

import android.app.ProgressDialog;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.zw.mvvmdemo.bean.ImageUrlBean;
import com.zw.mvvmdemo.bean.UrlData;
import com.zw.mvvmdemo.databinding.ActivityBingImageBinding;
import com.zw.mvvmdemo.viewmodel.ImageUrlViewModel;

public class BingImageActivity extends AppCompatActivity {

    private ActivityBingImageBinding binding;
    private ImageUrlViewModel mViewModel;
    private ProgressDialog mDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil
                .setContentView(this, R.layout.activity_bing_image);

         // 建立ViewModel，并将Activity的生命周期绑定到ViewModel上
        mViewModel = new ViewModelProvider(
                this,
                ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()))
                .get(ImageUrlViewModel.class);

        // 为ViewModel的UrlData建立数据监听，并监听数据变化，根据数据更新UI
        mViewModel.getImageUrl().observe(this, new Observer<UrlData<ImageUrlBean.UrlBean>>() {
            @Override
            public void onChanged(@Nullable UrlData<ImageUrlBean.UrlBean> data) {
                mDialog.dismiss();
                if(data.getErrorMsg() != null) {
                    Toast.makeText(BingImageActivity.this, data.getErrorMsg(),
                            Toast.LENGTH_LONG).show();
                }
                // 监听到数据变化后，通过databinding更改布局UI，若未使用databinding，则需要自己写相关UI更新逻辑
                binding.setImage(data.getData());
            }
        });

        // 监听特殊状态下更新UI的操作
        mViewModel.getState().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer integer) {
                mDialog.dismiss();
                if(integer == ImageUrlViewModel.STATE_NO_PRE_IMAGE) {
                    Toast.makeText(BingImageActivity.this, "没有前一张图片了！",
                            Toast.LENGTH_LONG).show();
                }
            }
        });

        binding.setClicker(new Clicker());

        mDialog = new ProgressDialog(this);
        mDialog.setTitle("加载中");
        mDialog.show();
        mViewModel.loadImage();
    }

    public class Clicker {

        public void onClick(View view) {
            mDialog.show();
            switch (view.getId()) {
                case R.id.btn_pre:
                    mViewModel.loadPreImage();
                    break;
                case R.id.btn:
                    mViewModel.loadImage();
                    break;
                case R.id.btn_next:
                    mViewModel.loadNextImage();
                    break;
            }
        }
    }
}
