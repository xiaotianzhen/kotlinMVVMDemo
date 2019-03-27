package com.quyangyu.mvvmdemo.util;

import android.databinding.BindingAdapter;
import android.media.Image;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class ImageUtil2{

    /**
     *  @BindingAdapter("url")
     * @param imageView
     * @param url
     */
    public static void loadImage(ImageView imageView,String url){
        Glide.with(imageView.getContext()).load(url).into(imageView);
    }
}
