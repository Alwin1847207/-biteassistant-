package com.example.biteassistant;

public class recipeModel {

    private String Itemname;
    private String Ingredients;
    private String Steps;

    public recipeModel(){

    }

    public recipeModel(String itemname, String ingredients, String steps){
        this.Itemname = itemname;
        this.Ingredients = ingredients;
        this.Steps = steps;
    }

    public String getItemname() {return  Itemname;}

    public String getIngredients() { return  Ingredients; }

    public String getSteps() { return  Steps; }
}
