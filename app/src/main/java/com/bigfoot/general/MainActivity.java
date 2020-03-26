package com.bigfoot.general;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.bigfoot.general.gallery.GalleryModel;
import com.bigfoot.general.gallery.GalleryViewActivity;
import com.bigfoot.general.utils.SlideShowAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class MainActivity extends AppCompatActivity  implements View.OnClickListener{


    ViewPager viewPager;
    CircleIndicator circleIndicator;

    SlideShowAdapter slideShowAdapter;
    Handler handler;
    Runnable runnable;
    Timer timer;
    List<Slider> sliders = new ArrayList<>();

    Boolean isOpen = false;

    boolean doubleBackToExitPressedOnce = false;


    TextView addProduct, addOffer, viewProducts,vGallery;
    FloatingActionButton addProductFab, addOfferFab, fab,vProducts,galleryFab;
    private Animation fab_open, fab_close, fab_clock, fab_anticlock;

    ConstraintLayout coordinatorLayout;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = findViewById(R.id.viewPagerHome);
        circleIndicator = findViewById(R.id.circleIndicator);

        addProduct = findViewById(R.id.addProduct);
        addOffer = findViewById(R.id.vOfferText);

        galleryFab = findViewById(R.id.viewGalleryFab);
        vGallery = findViewById(R.id.viewGalleryText);

        viewProducts = findViewById(R.id.viewProducts);
        vProducts = findViewById(R.id.viewProductsFab);

        coordinatorLayout = findViewById(R.id.constraintMain);


        addOfferFab = findViewById(R.id.addOfferFab);
        addProductFab = findViewById(R.id.addproductFab);

        fab_close = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_close);
        fab_open = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_open);
        fab_clock = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_rotate_clock);
        fab_anticlock = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_rotate_anticlock);

        coordinatorLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isOpen)
                    openFab();

            }
        });


        fab = (FloatingActionButton) findViewById(R.id.fabBtn);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isOpen) {

                    openFab();


                } else {
                    closeFab();
//                     coordinatorLayout.setBackgroundColor(getColorWithAlpha(getResources().getColor(R.color.colorWhiteText)
//                             , 0.8f));

                }

            }

        });


        initViewPager();

        addSampleData();

        initViewModel();


     addProductFab.setOnClickListener(this);


        addOfferFab.setOnClickListener(this);


        galleryFab.setOnClickListener(this);

        addOffer.setOnClickListener(this);
        vGallery.setOnClickListener(this);
        addProduct.setOnClickListener(this);
        viewProducts.setOnClickListener(this);
    }


    private void initViewModel() {


        if(sliders!=null){
            slideShowAdapter = new SlideShowAdapter(sliders, this);
            viewPager.setAdapter(slideShowAdapter);
            circleIndicator.setViewPager(viewPager);
        }


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


    public void addSampleData(){



            sliders.add(new Slider( R.drawable.da));
            sliders.add(new Slider( R.drawable.daa));
            sliders.add(new Slider( R.drawable.daaa));
            //banners.add(new Banner( R.drawable.banners));

        }



    public void openFab(){

        vGallery.setVisibility(View.INVISIBLE);
        galleryFab.setVisibility(View.INVISIBLE);
        addOffer.setVisibility(View.INVISIBLE);
        addProduct.setVisibility(View.INVISIBLE);
        viewProducts.setVisibility(View.INVISIBLE);
        addOfferFab.startAnimation(fab_close);
        addProductFab.startAnimation(fab_close);
        vProducts.startAnimation(fab_close);
        fab.startAnimation(fab_anticlock);
        galleryFab.setAnimation(fab_close);
        addOfferFab.setClickable(false);
        fab.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_home));
        addProductFab.setClickable(false);

        isOpen = false;
    }


    public void closeFab(){

        vGallery.setVisibility(VISIBLE);
        galleryFab.setVisibility(VISIBLE);
        addOffer.setVisibility(VISIBLE);
        addProduct.setVisibility(VISIBLE);
        viewProducts.setVisibility(VISIBLE);
        vProducts.startAnimation(fab_open);
        addOfferFab.startAnimation(fab_open);
        addProductFab.startAnimation(fab_open);
        galleryFab.startAnimation(fab_open);
        fab.startAnimation(fab_clock);
        addOfferFab.setClickable(true);
        fab.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_storage));
        addProductFab.setClickable(true);
        isOpen = true;

    }



    @Override
    public void onBackPressed() {

        if (isOpen) {
            openFab();
        } else {

            if (doubleBackToExitPressedOnce) {

                super.onBackPressed();
                finish();

            } else {


                doubleBackToExitPressedOnce = true;
                final ConstraintLayout coordinatorLayout =  findViewById(R.id.constraintMain);
                Snackbar.make(coordinatorLayout, getString(R.string.press_back), Snackbar.LENGTH_SHORT).show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        doubleBackToExitPressedOnce = false;

                    }
                }, 2000);
            }
        }
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.addOfferFab:
            case R.id.vOfferText:
                startActivity(new Intent(MainActivity.this, OurTeamActivity.class));
                break;

            case R.id.addproductFab:
            case R.id.addProduct:
                startActivity(new Intent(MainActivity.this, AboutUsActivity.class));
                break;

            case R.id.viewGalleryFab:
            case R.id.viewGalleryText:
                startActivity(new Intent(MainActivity.this, GalleryViewActivity.class));
                break;


            case R.id.viewProductsFab:
            case R.id.viewProducts:
                break;


        }

    }
}



