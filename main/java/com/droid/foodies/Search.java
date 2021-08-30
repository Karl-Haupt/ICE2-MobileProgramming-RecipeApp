package com.droid.foodies;

import android.app.AlertDialog;
import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class Search extends Fragment {
    RecyclerView myrecyclerView;
    RecycleViewAdapter myAdapter;
    DatabaseHelper db;
    Button btnSearchRecipe;
    EditText searchRecipeName;

    List<Recipe> recipes1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        db = new DatabaseHelper(getActivity());
        recipes1 = new ArrayList<>();

        btnSearchRecipe = (Button) view.findViewById(R.id.btnSearchRecipe);
        searchRecipeName = (EditText) view.findViewById(R.id.searchRecipeName);

        btnSearchRecipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(recipes1.size() > 0) {
                    recipes1.removeAll(recipes1);
                }
                String searchResult = searchRecipeName.getText().toString();
                recipes1.addAll(0, db.searchRecipes(searchResult));

                myrecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView_id);

                myAdapter = new RecycleViewAdapter(getActivity(),recipes1);

                myrecyclerView.setLayoutManager(new GridLayoutManager( getActivity(),1));

                myrecyclerView.setAdapter(myAdapter);
            }
        });

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Search Recipes");
    }



}