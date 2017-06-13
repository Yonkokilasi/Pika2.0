package com.example.josephinemenge.pika;

import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Josephine Menge on 02/06/2017.
 */
@Parcel
public class Recipe {
     String name;
    String website;
     String source;
      int yield;
    List<String> ingredientLines = new ArrayList<>();
       String imageUrl;
    private String pushId;

    public Recipe(String name, String website,String source, int yield,List<String>ingredientLines,String imageUrl) {
        this.name = name;
        this.imageUrl = imageUrl;
        this.source = source;
        this.ingredientLines = ingredientLines;
        this.yield = yield;
        this.website = website;
    }
    public Recipe() {

    }
    public String getLabel() {
        return name;
    }
    public String getSource() {
        return source;
    }
    public  int getYield() {
        return yield;
    }
    public String getWebsite() {
        return website;
    }
    public List<String> getIngredientLines() {
        return ingredientLines;
    }
    public String getImageUrl() {
        return imageUrl;
    }
    public String getPushId() {
        return pushId;
    }
    public void setPushId(String pushId) {
        this.pushId = pushId;
    }


}
