package com.droid.foodies;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.concurrent.atomic.AtomicInteger;

public class RecipeDetails extends AppCompatActivity {

    private TextView mRecipeName;
    private TextView mRecipeIngredients;
    private TextView mRecipeMethodTitle;
    private TextView mRecipe;
    private ImageView mThumbnail;
    private Button btnLike;
    private TextView totalLikesView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

        AtomicInteger amountOfLikes = new AtomicInteger();

        mThumbnail = (ImageView) findViewById(R.id.recipe_img_id);
        mRecipeName = (TextView) findViewById(R.id.text_recipe);
        mRecipeIngredients = (TextView) findViewById(R.id.ingredients);
        mRecipeMethodTitle = (TextView) findViewById(R.id.method);
        mRecipe = (TextView) findViewById(R.id.recipe);

        Intent intent = getIntent();
        int image = intent.getExtras().getInt("thumbnail");
        String Title = intent.getExtras().getString("recipeName");
        String Ingredients = intent.getExtras().getString("recipeIngredients");
        String MethodTitle = intent.getExtras().getString("recipeMethodTitle");
        String Recipe = intent.getExtras().getString("recipe");

        mThumbnail.setImageResource(image);
        mRecipeName.setText(Title);
        mRecipeIngredients.setText(Ingredients);
        mRecipeMethodTitle.setText(MethodTitle);
        mRecipe.setText(Recipe);

        btnLike = (Button) findViewById(R.id.btnLikeButton);
        totalLikesView = (TextView) findViewById(R.id.totalLikes);
        totalLikesView.setText(String.valueOf(amountOfLikes));

        btnLike.setOnClickListener(v -> {
            amountOfLikes.set(amountOfLikes.get() + 1);
            totalLikesView.setText(String.valueOf(amountOfLikes));
            btnLike.setEnabled(false);
        });
    }
}