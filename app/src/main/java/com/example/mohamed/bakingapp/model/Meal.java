package com.example.mohamed.bakingapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mohamed on 04/10/2017.
 */

public class Meal implements Serializable, Parcelable {
   @SerializedName("id")
    private int id;
    @SerializedName("name")
    private String name;
    @SerializedName("image")
    private String image;
    @SerializedName("steps")
    private ArrayList<MealStep> steps;
    @SerializedName("ingredients")
    private ArrayList<MealIngredients> ingredients;
    @SerializedName("servings")
    private int servings;

    public Meal(int id, String name, String image, ArrayList<MealStep> steps, ArrayList<MealIngredients> ingredients, int servings) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.steps = steps;
        this.ingredients = ingredients;
        this.servings = servings;
    }

    public int getServings() {
        return servings;
    }

    public void setServings(int servings) {
        this.servings = servings;
    }

    protected Meal(Parcel in) {
        id = in.readInt();
        name = in.readString();
        image = in.readString();
    }

    public static final Creator<Meal> CREATOR = new Creator<Meal>() {
        @Override
        public Meal createFromParcel(Parcel in) {
            return new Meal(in);
        }

        @Override
        public Meal[] newArray(int size) {
            return new Meal[size];
        }
    };

    public ArrayList<MealIngredients> getIngredients() {
        return ingredients;
    }

    public void setIngredients(ArrayList<MealIngredients> ingredients) {
        this.ingredients = ingredients;
    }

    public ArrayList<MealStep> getSteps() {
        return steps;
    }

    public void setSteps(ArrayList<MealStep> steps) {
        this.steps = steps;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(image);
    }
}
