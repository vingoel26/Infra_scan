package com.example.infrascan;

import android.graphics.Bitmap;

public class FirebaseItem {
    private Bitmap imageUrl;



    private String desc;
    private int pD;

    public FirebaseItem() { } // Required for Firebase

    public FirebaseItem(Bitmap imageUrl,String desc,int pD) {
        this.imageUrl = imageUrl;
        this.desc = desc;
        this.pD = pD;
    }

    public Bitmap getImageUrl() {
        return imageUrl;
    }public void setImageUrl(Bitmap img) {
        imageUrl =  img;
    }
    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getpD() {
        return pD;
    }

    public void setpD(int pD) {
        this.pD = pD;
    }
}
