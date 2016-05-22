package com.aldomora.petagram.Pojo;

/**
 * Created by root on 21/05/16.
 */
public class Foto {
    private int image;
    private int rate;

    public Foto(int image, int rate) {
        this.image = image;
        this.rate = rate;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }
}
