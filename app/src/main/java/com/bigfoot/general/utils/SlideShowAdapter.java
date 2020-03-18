package com.bigfoot.general.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;


import androidx.viewpager.widget.PagerAdapter;

import com.bigfoot.general.R;
import com.bigfoot.general.Slider;
import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by DELL on 28/04/2018.
 */

public class SlideShowAdapter extends PagerAdapter {

    private Context context;
    LayoutInflater inflater;

    public List<Slider> sliderList;

//    public int images[] = {
//                            com.bigfoot.www.Kartmandus.R.drawable.slider,
//                            com.bigfoot.www.Kartmandus.R.drawable.slidero,
//                            com.bigfoot.www.Kartmandus.R.drawable.image,
//                           };

    public SlideShowAdapter(List<Slider> sliderList, Context context) {
        this.context = context;
        this.sliderList = sliderList;
    }

    @Override
    public int getCount() {
        return sliderList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {

        return (view == (LinearLayout) object);

    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.slideshow_layout, container, false);

        ImageView imageView = (ImageView) view.findViewById(R.id.imageView);
//        imageView.setImageResource(images[position]);

        Glide.with(context).load(sliderList.get(position).getSliderImage()).into(imageView);

        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

        container.removeView((LinearLayout)object);

    }
}
