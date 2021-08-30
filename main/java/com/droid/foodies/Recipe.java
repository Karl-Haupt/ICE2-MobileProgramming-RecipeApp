package com.droid.foodies;

public class Recipe {
    private String recipeName;
    private String recipeIngredients;
    private String recipeMethodTitle;
    private String recipeDescription;
    private String userUID;

    private int thumbnail;
    private String timeToMake;

    public Recipe(String recipeName, String recipeIngredients, String recipeMethodTitle, String recipeDescription, int thumbnail, String timeToMake, String userUID) {
        this.recipeName = recipeName;
        this.recipeIngredients = recipeIngredients;
        this.recipeMethodTitle = recipeMethodTitle;
        this.recipeDescription = recipeDescription;
        this.thumbnail = thumbnail;
        this.timeToMake = timeToMake;
        this.userUID = userUID;
    }

    public Recipe(String recipeName, String recipeIngredients, String recipeMethodTitle, String recipeDescription, int thumbnail) {
        this.recipeName = recipeName;
        this.recipeIngredients = recipeIngredients;
        this.recipeMethodTitle = recipeMethodTitle;
        this.recipeDescription = recipeDescription;
        this.thumbnail = thumbnail;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public String getRecipeIngredients() {
        return recipeIngredients;
    }

    public String getRecipeMethodTitle() {
        return recipeMethodTitle;
    }

    public String getRecipeDescription() {
        return recipeDescription;
    }

    public int getThumbnail() {
        return thumbnail;
    }

//    public int getServings() {
//        return servings;
//    }

    public String getTimeToMake() {
        return timeToMake;
    }

    public String getUserUID() {
        return userUID;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public void setRecipeIngredients(String recipeIngredients) {
        this.recipeIngredients = recipeIngredients;
    }

    public void setRecipeMethodTitle(String recipeMethodTitle) {
        this.recipeMethodTitle = recipeMethodTitle;
    }

    public void setRecipeDescription(String recipeDescription) {
        this.recipeDescription = recipeDescription;
    }

    public void setUserUID(String userUID) {
        this.userUID = userUID;
    }

    public void setThumbnail(int thumbnail) {
        this.thumbnail = thumbnail;
    }

    public void setTimeToMake(String timeToMake) {
        this.timeToMake = timeToMake;
    }
}
