package com.droid.foodies;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;


public class updateRecipe extends Fragment {
    DatabaseHelper db;
    EditText recipeId, recipeTitle, recipeIngredients, recipeMethodTitle, recipeDescription, recipeImage, recipeTimeToMake;
    TextView txtRecipeTitle, txtRecipeIngredients, txtRecipeMethodTitle, txtRecipeDescription, txtRecipeImage, txtRecipeTimeToMake;
    Button btnUpdateRecipe, btnShowFields;
    FirebaseAuth firebaseAuth;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_update_recipe, container, false);
        db = new DatabaseHelper(getActivity());
        firebaseAuth = FirebaseAuth.getInstance();

        txtRecipeTitle = (TextView) view.findViewById(R.id.txtRecipeName);
        txtRecipeIngredients = (TextView) view.findViewById(R.id.txtRecipeIngredients);
        txtRecipeMethodTitle = (TextView) view.findViewById(R.id.txtRecipeMethod);
        txtRecipeDescription = (TextView) view.findViewById(R.id.txtRecipeDescription);
        txtRecipeImage = (TextView) view.findViewById(R.id.txtRecipeImage);
        txtRecipeTimeToMake = (TextView) view.findViewById(R.id.txtRecipeTime);
        btnShowFields = (Button) view.findViewById(R.id.btnShowFields);

        recipeId = (EditText) view.findViewById(R.id.updateRecipeId);
        recipeTitle = (EditText) view.findViewById(R.id.updateRecipeName);
        recipeIngredients = (EditText) view.findViewById(R.id.updateRecipeIngredients);
        recipeMethodTitle = (EditText) view.findViewById(R.id.updateRecipeMethodTitle);
        recipeDescription = (EditText) view.findViewById(R.id.updateRecipeDescription);
        recipeImage = (EditText) view.findViewById(R.id.updateRecipeThumbnail);
        recipeTimeToMake = (EditText) view.findViewById(R.id.updateRecipeTimeToMake);
        btnUpdateRecipe = (Button) view.findViewById(R.id.btnUpdateRecipe);

        //Hiding all the update fields
        txtRecipeTitle.setVisibility(View.GONE);
        txtRecipeIngredients.setVisibility(View.GONE);
        txtRecipeMethodTitle.setVisibility(View.GONE);
        txtRecipeDescription.setVisibility(View.GONE);
        txtRecipeImage.setVisibility(View.GONE);
        txtRecipeTimeToMake.setVisibility(View.GONE);
        recipeTitle.setVisibility(View.GONE);
        recipeIngredients.setVisibility(View.GONE);
        recipeMethodTitle.setVisibility(View.GONE);
        recipeDescription.setVisibility(View.GONE);
        recipeImage.setVisibility(View.GONE);
        recipeTimeToMake.setVisibility(View.GONE);
        btnUpdateRecipe.setVisibility(View.GONE);

        btnShowFields.setOnClickListener(v -> {
            if(!recipeId.getText().toString().equals("")) {
                if (db.getRecipesUser(Integer.parseInt(recipeId.getText().toString())).equals(firebaseAuth.getUid())) {
                    Recipe recipe = db.getRecipeDetails(Integer.parseInt(recipeId.getText().toString()));

                    recipeId.setEnabled(false);

                    btnShowFields.setVisibility(View.GONE);
                    txtRecipeTitle.setVisibility(View.VISIBLE);
                    txtRecipeIngredients.setVisibility(View.VISIBLE);
                    txtRecipeMethodTitle.setVisibility(View.VISIBLE);
                    txtRecipeDescription.setVisibility(View.VISIBLE);
                    txtRecipeImage.setVisibility(View.VISIBLE);
                    txtRecipeTimeToMake.setVisibility(View.VISIBLE);
                    recipeTitle.setVisibility(View.VISIBLE);
                    recipeIngredients.setVisibility(View.VISIBLE);
                    recipeMethodTitle.setVisibility(View.VISIBLE);
                    recipeDescription.setVisibility(View.VISIBLE);
                    recipeImage.setVisibility(View.VISIBLE);
                    recipeTimeToMake.setVisibility(View.VISIBLE);
                    btnUpdateRecipe.setVisibility(View.VISIBLE);

                    recipeTitle.setText(recipe.getRecipeName());
                    recipeIngredients.setText(recipe.getRecipeIngredients());
                    recipeMethodTitle.setText(recipe.getRecipeMethodTitle());
                    recipeDescription.setText(recipe.getRecipeDescription());
                    //                recipeImage.setText(recipe.getThumbnail());
                    recipeTimeToMake.setText(recipe.getTimeToMake());

                    Toast.makeText(getActivity(), "Update your recipe, chief", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getActivity(), "Unable to update, this is not your recipe", Toast.LENGTH_LONG).show();
                }
            } else {
                Toast.makeText(getActivity(), "Please enter the recipe ID", Toast.LENGTH_LONG).show();
            }
        });

        btnUpdateRecipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isUpdated = db.updateRecipe(
                        recipeId.getText().toString(),
                        recipeTitle.getText().toString(),
                        recipeIngredients.getText().toString(),
                        recipeMethodTitle.getText().toString(),
                        recipeDescription.getText().toString(),
                        R.drawable.pasta,
                        recipeTimeToMake.getText().toString(),
                        firebaseAuth.getUid()
                );
                if(isUpdated == true)
                    Toast.makeText(getActivity(), "Thanks, recipe updated", Toast.LENGTH_LONG).show();
                else{
                    Toast.makeText(getActivity(), "Failed to update recipe", Toast.LENGTH_LONG).show();
                }
            }
        });

        return view;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Update Recipe");
    }
}