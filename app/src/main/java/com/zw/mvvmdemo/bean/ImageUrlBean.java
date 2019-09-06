package com.zw.mvvmdemo.bean;

import java.util.List;

public class ImageUrlBean {

    private List<UrlBean> images;

    public List<UrlBean> getImages() {
        return images;
    }

    public void setImages(List<UrlBean> images) {
        this.images = images;
    }

    public static class UrlBean {

        public static final String BASE_IMAGE_ADDRESS_URL = "https://cn.bing.com/";

        private String url;

        private String copyright;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getCopyright() {
            return copyright;
        }

        public void setCopyright(String copyright) {
            this.copyright = copyright;
        }
    }

}
