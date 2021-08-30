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

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;


public class deleteRecipe extends Fragment {
    EditText deleteRecipeTitle;
    Button deleteRecipe;
    DatabaseHelper db;
    FirebaseAuth firebaseAuth;

    //UUID = SQgxuEXOwuPDboVHLOa2jZYBiL82

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_delete_recipe, container, false);
        db = new DatabaseHelper(getActivity());
        deleteRecipeTitle = view.findViewById(R.id.deleteRecipeName);
        deleteRecipe = view.findViewById(R.id.btnDeleteRecipe);

        firebaseAuth = FirebaseAuth.getInstance();


        deleteRecipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int recipeID = db.getIDFromRecipeTitle(deleteRecipeTitle.getText().toString());
                if(db.getRecipesUser(recipeID).equals(firebaseAuth.getUid())) {
                    db.deleteRecipe(deleteRecipeTitle.getText().toString());
                    Toast.makeText(getActivity(), "Recipe Deleted", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getActivity(), "Unable, this is not your recipe", Toast.LENGTH_LONG).show();
                }


            }
        });

        return view;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Delete Recipe");
    }
}