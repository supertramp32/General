package com.bigfoot.general.gallery;

public class GalleryModel {

    int image;
    String  likes,comment,date, type;

    public GalleryModel(int image, String likes, String comment, String date, String type) {
        this.image = image;
        this.likes = likes;
        this.comment = comment;
        this.date = date;
        this.type = type;
    }


    public int getImage() {
        return image;
    }

    public String getLikes() {
        return likes;
    }

    public String getComment() {
        return comment;
    }

    public String getDate() {
        return date;
    }

    public String getType() {
        return type;
    }
}
