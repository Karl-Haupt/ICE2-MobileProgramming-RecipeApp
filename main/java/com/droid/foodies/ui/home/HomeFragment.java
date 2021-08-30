package com.droid.foodies.ui.home;

import android.app.AlertDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.droid.foodies.DatabaseHelper;
import com.droid.foodies.R;
import com.droid.foodies.Recipe;
import com.droid.foodies.RecycleViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    RecyclerView myrecyclerView;
    RecycleViewAdapter myAdapter;
    DatabaseHelper db;
    Button btnViewAllRecipes;

    List<Recipe> recipes1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        db = new DatabaseHelper(getActivity());

        recipes1 = new ArrayList<>();
        db.insertData("Chicken Roll","200 gm chopped into cubes chicken" +
                        "1 medium chopped tomato" +
                        "1/2 pinch red chilli powder" +
                        "2 tablespoon vegetable oil" +
                        "1/2 cut into strips cucumber" +
                        "1/2 tablespoon chopped coriander leaves" +
                        "1 large thinly sliced onion" +
                        "2 medium chopped green chilli" +
                        "2 pinches garam masala powder" +
                        "1 lemon wedges" +
                        "1 teaspoon tomato ketchup" +
                        "1 tablespoon green chilli sauce","Method",
                "Chicken Roll is a delectable North Indian recipe made using all purpose flour, stir-fried chicken, yoghurt and a variety of vegetables rolled into paranthas. On days you do not want to prepare an elaborate meal, this delectable dish can be a saviour. Rolls are quite popular across India and they are often a favourite evening snack. Egg Roll, Kathi Kebab Roll, Mutton Roll, Paneer Roll, Potato Roll and even Fish Roll are among its many variations. This easy roll recipe is made using chicken and has the unforgettable aroma of Indian spices. You can also utilize your leftover parathas and chapatis in making this recipe. If you do not like to use all-purpose flour or maida, you can make it with whole wheat flour too. In fact, it can be made even with leftover chicken. All you have to ensure is that you use the right amount of spices so that the flavour is not lost. " +
                        "Easy to pack and carry, you can also take it to office or prepare it for picnics and day trips. " +
                        "A must try roll recipe for all chicken lovers!\n",R.drawable.chicken_roll,
                "45" , "SQgxuEXOwuPDboVHLOa2jZYBiL82");

        db.insertData("Donut","1 c. whole milk" +
                        "1/4 c. plus 1 tsp. granulated sugar, divided" +
                        "1 packet (or 2 1/4 tsp.) active dry yeast " +
                        "4 c. all-purpose flour, plus more if needed" +
                        "1/2 tsp. kosher salt" +
                        "6 tbsp. melted butter" +
                        "2 large eggs" +
                        "1/2 tsp. pure vanilla extract" +
                        "Canola or vegetable oil, for frying","Method","\n" +
                        "Grease a large bowl with cooking spray and set aside. In a small, microwave-safe bowl or glass measuring cup, add milk. Microwave until lukewarm, 40 seconds. Add a teaspoon of sugar and stir to dissolve, then sprinkle over yeast and let sit until frothy, about 8 minutes.\n" +
                        "Make glaze: In a large bowl, whisk together milk, powdered sugar, and vanilla until smooth. Set aside.\n" +
                        "Line a large baking sheet with paper towels. In a large dutch oven over medium heat, heat 2'' oil to 350°. Cook doughnuts, in batches, until deeply golden on both sides, about 1 minute per side. Holes will cook even faster!\n" +
                        "Transfer doughnuts to paper towel-lined baking sheet to drain and cool slightly. Dip into glaze, then place onto a cooling rack (or eat immediately!)",R.drawable.donut1,
                "34", "SQgxuEXOwuPDboVHLOa2jZYBiL82");
        db.insertData("Dosa","3 cups rice" +
                        "1 cup urad daal (split, skinless black gram)" +
                        "3/4 teaspoon fenugreek seeds" +
                        "Salt (to taste)" +
                        "Vegetable / canola / sunflower cooking oil","Method",
                "Wash the rice and urad daal well. Add the fenugreek seeds to the mix and fill enough water in the rice-daal bowl to cover them about 2-inch deep. Soak overnight.\n" +
                        "Put some cooking oil in a small bowl and keep ready. You will also need a bowl of ice cold water, a large, flat nonstick pan, 2 sheets of paper towel, a ladle, a spatula, and a basting brush.\n" +
                        "When the upper surface begins to look cooked (it will no longer look soft or runny), flip the dosa. By this time, ideally, the surface that was underneath should be light golden in color. Cook for 1 minute after flipping.\n" +
                        "The dosa is almost done. Fold it in half and allow to cook for 30 seconds more.",R.drawable.dosa1, "56", "wguTaaIgaydCqepWC68oziUo2243");
        db.insertData("Pancake","1 1/4 cups milk" +
                        "1 egg" +
                        "3 tablespoons butter melted" +
                        "1 1/2 cups all-purpose flour" +
                        "3 1/2 teaspoons baking powder" +
                        "1 teaspoon salt" +
                        "1 tablespoon white sugar","Method",
                "In a large bowl, sift together the flour, baking powder, salt and sugar. Make a well in the center and pour in the milk, egg and melted butter; mix until smooth." +
                        "Heat a lightly oiled griddle or frying pan over medium high heat. Pour or scoop the batter onto the griddle, using approximately 1/4 cup for each pancake. Brown on both sides and serve hot.",
                R.drawable.pancakes, "34", "SQgxuEXOwuPDboVHLOa2jZYBiL82");
        db.insertData("Pasta","1 tsp oil" +
                        "1 tsp butter \n" +
                        "2 clove garlic, finely chopped \n" +
                        "1 inch ginger, finely chopped \n" +
                        "½ onion, finely chopped \n" +
                        "1 cup tomato pulp \n" +
                        "¼ tsp turmeric / haldi \n" +
                        "½ tsp kashmiri red chilli powder \n" +
                        "2 tbsp tomato sauce \n" +
                        "½ tsp garam masala",
                "Method",
                "firstly, saute 1 inch ginger and 2 clove garlic in 1 tsp oil and 1 tsp butter." +
                        "further saute ½ onion till they turn soft." +
                        "additionally add 1 cup tomato pulp and saute well." +
                        "keep stirring till the tomato pulp thickens." +
                        "now add ¼ tsp turmeric, ½ tsp chilli powder, ½ tsp garam masala and ½ tsp salt." +
                        "saute till the spices gets cooked completely." +
                        "now add 2 tbsp corn, ¼ capsicum, ¼ carrot, 2 tbsp peas and 7 florets broccoli. saute for a minute." +
                        "add in 3 tbsp water and mix well." +
                        "cover and simmer for 5 minutes." +
                        "now add in 2 tbsp tomato sauce and ½ tsp mixed herbs. mix well." +
                        "add in cooked pasta and mix gently till the sauce gets coated well." +
                        "finally, serve masala pasta indian style hot topped with cheese if required.",R.drawable.pasta1, "89", "wguTaaIgaydCqepWC68oziUo2243");

        recipes1.addAll(0, db.readRecipes());

        myrecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView_id);

        myAdapter = new RecycleViewAdapter(getActivity(),recipes1);

        myrecyclerView.setLayoutManager(new GridLayoutManager(getActivity(),1));

        myrecyclerView.setAdapter(myAdapter);
        db = new DatabaseHelper(getActivity());

        btnViewAllRecipes = (Button) view.findViewById(R.id.btnViewAllRecipe);

        btnViewAllRecipes.setOnClickListener(new View.OnClickListener() {
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

    public void showMessage(String title, String Message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Home");
    }
}