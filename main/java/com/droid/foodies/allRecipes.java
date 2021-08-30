package com.droid.foodies;

import android.app.AlertDialog;
import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class allRecipes extends Fragment {
    DatabaseHelper db;
    Button btnViewData;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_all_recipes, container, false);

        db = new DatabaseHelper(getActivity());
        btnViewData = view.findViewById(R.id.btnViewAllToast);

        btnViewData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res = db.getAllRecipes();
                if(res.getCount() == 0) {
                    showMessage("Error", "Nothing found");
                    return;
                }

                StringBuffer buffer = new StringBuffer();
                while(res.moveToNext()) {
                    buffer.append("ID: " + res.getString(0) + "\n");
                    buffer.append("Name: " + res.getString(1) + "\n");
                    buffer.append("Ingredients: " + res.getString(2) + "\n");
                    buffer.append("Method Title: " + res.getString(3) + "\n");
                    buffer.append("Description: " + res.getString(4) + "\n");
                    buffer.append("Thumbnail: " + res.getString(5) + "\n");
                    buffer.append("Time To Make: " + res.getString(6) + "\n\n");
                }

                showMessage("Recipes", buffer.toString());
            }
        });

        return view;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("All Recipes");
    }


    public void showMessage(String title, String Message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }
}