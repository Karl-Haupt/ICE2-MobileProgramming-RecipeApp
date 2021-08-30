package com.droid.foodies;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Foodies.db";
    public static final String RECIPE_TABLE = "Recipe_table";
    public static final String RECIPE_ID = "recipeID";
    public static final String RECIPE_NAME = "recipeName";
    public static final String RECIPE_INGREDIENTS = "recipeIngredients";
    public static final String RECIPE_METHOD_TITLE = "recipeMethodTitle";
    public static final String RECIPE_DESCRIPTION = "recipeDescription";
    public static final String RECIPE_THUMBNAIL = "recipeThumbnail";
    public static final String RECIPE_TIMETOMAKE = "timeToMake";
    public static final String USER_UID = "userUID";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + RECIPE_TABLE + "(" +
                "recipeID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "recipeName TEXT, " +
                "recipeIngredients TEXT, " +
                "recipeMethodTitle TEXT, " +
                "recipeDescription TEXT, " +
                "recipeThumbnail INTEGER, "+
                "timeToMake TEXT, " +
                "userUID TEXT)");
    }

    //Insert Data into the RECIPE_TABLE
    public boolean insertData(String recipeName, String recipeIngredients, String recipeMethodTitle, String recipeDescription, int recipeThumbnail, String recipeTimeToMake, String userUID) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(RECIPE_NAME, recipeName);
        contentValues.put(RECIPE_INGREDIENTS, recipeIngredients);
        contentValues.put(RECIPE_METHOD_TITLE, recipeMethodTitle);
        contentValues.put(RECIPE_DESCRIPTION, recipeDescription);
        contentValues.put(RECIPE_THUMBNAIL, recipeThumbnail);
        contentValues.put(RECIPE_TIMETOMAKE, recipeTimeToMake);
        contentValues.put(USER_UID, userUID);

        long result = db.insert(RECIPE_TABLE, null, contentValues);
        if(result == 1) return false;
        else {
            return true;
        }
    }

    //Delete recipe from the RECIPE_TABLE
    public boolean deleteRecipe(String recipeTitle) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(RECIPE_TABLE, "recipeName =?", new String[]{recipeTitle}) > 0;
    }

    public Cursor getAllRecipes() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor allRecipes = db.rawQuery("select * from " + RECIPE_TABLE, null);
        return allRecipes;
    }

    public ArrayList<Recipe> readRecipes() {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursorRecipe = db.rawQuery("SELECT * FROM " + RECIPE_TABLE, null);

        ArrayList<Recipe> recipeModalArrayList = new ArrayList<>();

        if (cursorRecipe.moveToFirst()) {
            do {
                recipeModalArrayList.add(new Recipe(
                        cursorRecipe.getString(1),
                        cursorRecipe.getString(2),
                        cursorRecipe.getString(3),
                        cursorRecipe.getString(4),
                        cursorRecipe.getInt(5),
                        cursorRecipe.getString(6),
                        cursorRecipe.getString(7)
                ));
            } while (cursorRecipe.moveToNext());
        }
        cursorRecipe.close();
        return recipeModalArrayList;
    }

    public boolean updateRecipe(String recipeId, String recipeName, String recipeIngredients, String recipeMethodTitle, String recipeDescription, int recipeThumbnail, String recipeTimeToMake, String userUID) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(RECIPE_ID, recipeId);
        contentValues.put(RECIPE_NAME, recipeName);
        contentValues.put(RECIPE_INGREDIENTS, recipeIngredients);
        contentValues.put(RECIPE_METHOD_TITLE, recipeMethodTitle);
        contentValues.put(RECIPE_DESCRIPTION, recipeDescription);
        contentValues.put(RECIPE_THUMBNAIL, recipeThumbnail);
        contentValues.put(RECIPE_TIMETOMAKE, recipeTimeToMake);
        contentValues.put(USER_UID, userUID);

        db.update(RECIPE_TABLE, contentValues, "recipeID = ? ", new String[] { recipeId });
        return true;
    }

    public ArrayList<Recipe> searchRecipes(String searchItem) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor allFilteredRecipes = db.rawQuery("SELECT * FROM " + RECIPE_TABLE + " WHERE recipeName LIKE '%" + searchItem + "%'", null);
        ArrayList<Recipe> recipeModalArrayList = new ArrayList<>();

        if (allFilteredRecipes.moveToFirst()) {
            do {
                recipeModalArrayList.add(new Recipe(
                        allFilteredRecipes.getString(1),
                        allFilteredRecipes.getString(2),
                        allFilteredRecipes.getString(3),
                        allFilteredRecipes.getString(4),
                        allFilteredRecipes.getInt(5),
                        allFilteredRecipes.getString(6),
                        allFilteredRecipes.getString(7)
                ));
            } while (allFilteredRecipes.moveToNext());
        }
        allFilteredRecipes.close();
        return recipeModalArrayList;
    }

    //Get the user id from the recipe
    public String getRecipesUser(int recipeId) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor recipeUser = db.rawQuery("SELECT * FROM " + RECIPE_TABLE, null);
        String userId = null;
//        SELECT UserUID FROM Recipe_table WHERE recipeID = 1
        if (recipeUser.moveToFirst()) {
            do {

                int id = recipeUser.getInt(0);
                userId = recipeUser.getString(7);

                if(recipeId == id) {
                    return userId;
                }
            } while (recipeUser.moveToNext());
        }
        return userId;
    }

    //Get the id from recipe title
    public int getIDFromRecipeTitle(String recipeTitle) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor recipeUser = db.rawQuery("SELECT * FROM " + RECIPE_TABLE, null);
        int id = -1;

        if (recipeUser.moveToFirst()) {
            do {
                id = recipeUser.getInt(0);
                String title = recipeUser.getString(1);

                if(title.equals(recipeTitle)) {
                    return id;
                }
            } while (recipeUser.moveToNext());
        }
        return id;
    }

    public Recipe getRecipeDetails(int recipeId) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursorRecipe = db.rawQuery("SELECT * FROM " + RECIPE_TABLE, null);
        String userId = null;
//        SELECT UserUID FROM Recipe_table WHERE recipeID = 1
        if (cursorRecipe.moveToFirst()) {
            do {

                int id = cursorRecipe.getInt(0);
                userId = cursorRecipe.getString(7);

                if(recipeId == id) {
                    Recipe recipe = new Recipe(
                            cursorRecipe.getString(1),
                            cursorRecipe.getString(2),
                            cursorRecipe.getString(3),
                            cursorRecipe.getString(4),
                            cursorRecipe.getInt(5),
                            cursorRecipe.getString(6),
                            cursorRecipe.getString(7)
                    );
                    return recipe;
                }
            } while (cursorRecipe.moveToNext());
        }
        return null;
    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + RECIPE_TABLE);
        onCreate(db);
    }


    //IsAdmin
}
