package com.bigfoot.general;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

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

public class MainActivity extends AppCompatActivity {


    ViewPager viewPager;
    CircleIndicator circleIndicator;

    SlideShowAdapter slideShowAdapter;
    Handler handler;
    Runnable runnable;
    Timer timer;
    List<Slider> sliders = new ArrayList<>();

    Boolean isOpen = false;

    boolean doubleBackToExitPressedOnce = false;


    TextView addProduct, addOffer, viewProducts;
    FloatingActionButton addProductFab, addOfferFab, fab,vProducts;
    private Animation fab_open, fab_close, fab_clock, fab_anticlock;

    ConstraintLayout coordinatorLayout;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = findViewById(R.id.viewPagerHome);
        circleIndicator = findViewById(R.id.circleIndicator);

        addProduct = findViewById(R.id.addProduct);
        addOffer = findViewById(R.id.offerText);

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


        addProductFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, AboutUsActivity.class));
            }
        });


        addOfferFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, OurTeamActivity.class));

            }
        });



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

        addOffer.setVisibility(View.INVISIBLE);
        addProduct.setVisibility(View.INVISIBLE);
        viewProducts.setVisibility(View.INVISIBLE);
        addOfferFab.startAnimation(fab_close);
        addProductFab.startAnimation(fab_close);
        vProducts.startAnimation(fab_close);
        fab.startAnimation(fab_anticlock);
        addOfferFab.setClickable(false);
        addProductFab.setClickable(false);
        isOpen = false;
    }


    public void closeFab(){

        addOffer.setVisibility(VISIBLE);
        addProduct.setVisibility(VISIBLE);
        viewProducts.setVisibility(VISIBLE);
        vProducts.startAnimation(fab_open);
        addOfferFab.startAnimation(fab_open);
        addProductFab.startAnimation(fab_open);
        fab.startAnimation(fab_clock);
        addOfferFab.setClickable(true);
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




}



