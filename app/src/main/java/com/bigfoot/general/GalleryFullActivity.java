package com.bigfoot.general;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.os.Handler;

import com.bigfoot.general.utils.SlideShowAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator;

public class GalleryFullActivity extends AppCompatActivity {


    ViewPager viewPager;
    CircleIndicator circleIndicator;

    SlideShowAdapter slideShowAdapter;
    Handler handler;
    Runnable runnable;
    Timer timer;
    List<Slider> sliders = new ArrayList<>();
    int pos;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery_full);

        viewPager = findViewById(R.id.viewPagerGalleryFull);
        circleIndicator = findViewById(R.id.circleIndicator);
        pos = getIntent().getIntExtra("pos",0);


        initViewPager();
        addSampleData();
        initViewModel();


    }

    public  void initViewPager(){

        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {

                int i = viewPager.getCurrentItem();

                if (i == slideShowAdapter.sliderList.size() - 1) {
                    i = 0;
                    viewPager.setCurrentItem(i, true);
                } else {
                    i++;
                    viewPager.setCurrentItem(i, true);
                }


            }
        };

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(runnable);
            }
        }, 4000, 4000);


    }


    private void initViewModel() {


        if(sliders!=null){
            slideShowAdapter = new SlideShowAdapter(sliders, this);
            viewPager.setAdapter(slideShowAdapter);
            circleIndicator.setViewPager(viewPager);
        }


    }

    public void addSampleData(){



        sliders.add(new Slider( R.drawable.da));
        sliders.add(new Slider( R.drawable.daa));
        sliders.add(new Slider( R.drawable.daaa));
        //banners.add(new Banner( R.drawable.banners));

    }


}
