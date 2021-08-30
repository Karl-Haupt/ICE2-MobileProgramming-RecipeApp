package com.droid.foodies;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;


public class addRecipe extends Fragment {
    DatabaseHelper db;
    EditText recipeTitle, recipeIngredients, recipeMethodTitle, recipeDescription, recipeImage, recipeTimeToMake;
    Button btnAddRecipe;
    FirebaseAuth firebaseAuth;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_addrecipe, container, false);
        db = new DatabaseHelper(getActivity());

        recipeTitle = (EditText) view.findViewById(R.id.addRecipeName);
        recipeIngredients = (EditText) view.findViewById(R.id.addRecipeIngredients);
        recipeMethodTitle = (EditText) view.findViewById(R.id.addRecipeMethodTitle);
        recipeDescription = (EditText) view.findViewById(R.id.addRecipeDescription);
        recipeImage = (EditText) view.findViewById(R.id.addRecipeThumbnail);
        recipeTimeToMake = (EditText) view.findViewById(R.id.addRecipeTimeToMake);
        btnAddRecipe = (Button) view.findViewById(R.id.btnAddRecipe);

//        if(recipeTitle.getVisibility() == View.VISIBLE){
//            recipeTitle.setVisibility(View.GONE);
//        } else {
//            recipeTitle.setVisibility(View.VISIBLE);
//        }

        //I do this now
        firebaseAuth = FirebaseAuth.getInstance();

        btnAddRecipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.insertData(
                        recipeTitle.getText().toString(),
                        recipeIngredients.getText().toString(),
                        recipeMethodTitle.getText().toString(),
                        recipeDescription.getText().toString(),
                        R.drawable.pasta,
                        recipeTimeToMake.getText().toString(),
                        firebaseAuth.getUid()

                );
                boolean isInserted = false;
                if(isInserted = true)
                    Toast.makeText(getActivity(), "NIce job chief, recipe added", Toast.LENGTH_LONG).show();
                else{
                    Toast.makeText(getActivity(), "Failed to inserted recipe, chief", Toast.LENGTH_LONG).show();
                }
            }
        });

        return view;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Add Recipe");
    }
}