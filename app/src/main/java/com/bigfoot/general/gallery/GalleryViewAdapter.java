package com.bigfoot.general.gallery;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bigfoot.general.R;
import com.bumptech.glide.Glide;

import java.util.List;

public class GalleryViewAdapter extends RecyclerView.Adapter<GalleryViewAdapter.GalleryViewHolder> {

    private List<GalleryModel> timeLine;
    Context context;



    public GalleryViewAdapter(List<GalleryModel> timeLine, Context context) {

        this.context = context;
        this.timeLine = timeLine;

    }

    @NonNull
    @Override
    public GalleryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        final View v = LayoutInflater.from(context).inflate(R.layout.item_gallery, parent, false);
        final GalleryViewHolder storyViewHolder = new GalleryViewHolder(v);
        return storyViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull GalleryViewHolder holder, int position) {

        GalleryModel galleryModel = timeLine.get(position);
        holder.galleryTitle.setText(galleryModel.getType());
        holder.galleryDate.setText(galleryModel.getDate());
        Glide.with(context).load(galleryModel.getImage()).into(holder.singleImage);




    }

    @Override
    public int getItemCount() {
        return timeLine.size();
    }



    public class GalleryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView singleImage, galleryIcon,doubleOne, doubleTwo;
        TextView  galleryDate, galleryLike, galleryComment,galleryTitle;


        public GalleryViewHolder(View itemView) {
            super(itemView);

            galleryIcon = itemView.findViewById(R.id.galleryIcon);
            galleryDate = itemView.findViewById(R.id.gallerDate);
            singleImage = itemView.findViewById(R.id.gallerySingleImage);
            galleryTitle = itemView.findViewById(R.id.galleryTitle);


        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();

        }
    }






}

