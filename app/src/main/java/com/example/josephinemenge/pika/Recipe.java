package com.example.josephinemenge.pika;

import java.util.ArrayList;

/**
 * Created by Josephine Menge on 02/06/2017.
 */

public class Recipe {
    private String mName;
    private String mWebsite;
    private  String mSource;
    private  double mYield;
    private ArrayList<String> mIngredientLines = new ArrayList<>();
    private  String mImageUrl;

    public Recipe(String name, String website,String source, double yield,ArrayList<String>ingredientLines,String imageUrl) {
        this.mName = name;
        this.mImageUrl = imageUrl;
        this.mSource = source;
        this.mIngredientLines = ingredientLines;
        this.mYield = yield;
        this.mWebsite = website;
    }
    public String getLabel() {
        return mName;
    }
    public String getSource() {
        return mSource;
    }
    public  double getYield() {
        return mYield;
    }
    public String getWebsite() {
        return mWebsite;
    }
    public ArrayList<String> getIngredientLines() {
        return mIngredientLines;
    }
    public String getImageUrl() {
        return mImageUrl;
    }


}
