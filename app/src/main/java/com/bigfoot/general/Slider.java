package com.bigfoot.general;


/**
 * Created by DELL on 14/04/2019.
 */

public class Slider {

    int id;
    int sliderImage;


    public Slider(int sliderImage) {
        this.sliderImage = sliderImage;
    }

    public Slider(int id, int sliderImage) {
        this.id = id;
        this.sliderImage = sliderImage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSliderImage() {
        return sliderImage;
    }

    public void setSliderImage(int sliderImage) {
        this.sliderImage = sliderImage;
    }
}

