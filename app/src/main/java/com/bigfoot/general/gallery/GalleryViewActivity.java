package com.bigfoot.general.gallery;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.bigfoot.general.R;


import java.util.ArrayList;

public class GalleryViewActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<GalleryModel> galleryModels = new ArrayList<>();
    GalleryViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery_view);

        recyclerView = findViewById(R.id.galleryViewRecycler);

        initRecyclerView();
        initViewModel();

    }

    private void initViewModel() {
        GalleryModel galleryModel = new GalleryModel(R.drawable.da,"1",
                "2","2020-03-26","Front");
        galleryModels.add(galleryModel);

        galleryModel = new GalleryModel(R.drawable.daa,"1",
                "2","2020-03-26","Side");
        galleryModels.add(galleryModel);

        galleryModel = new GalleryModel(R.drawable.daaa,"1",
                "2","2020-03-26","Top");
        galleryModels.add(galleryModel);

        adapter = new GalleryViewAdapter(galleryModels,this);
        recyclerView.setAdapter(adapter);

    }


    private void initRecyclerView() {
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setOverScrollMode(View.OVER_SCROLL_NEVER);
    }
}
