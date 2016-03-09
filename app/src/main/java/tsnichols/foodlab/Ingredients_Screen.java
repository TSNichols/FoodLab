package tsnichols.foodlab;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class Ingredients_Screen extends AppCompatActivity {

    // Objects
    public static Spinner unitSizeSpin;
    public static Spinner ingredientsSpin;

    // Array list independent of ingredient database
    /**
     * Needs to be a constant merge of database as well as independent list
     */
    public static Set<String> sizeSet;
    public static ArrayList<String> unitSizeList;

    // Adapters to display database info
    public static ArrayAdapter<String> ingredientAdapter;
    public static ArrayAdapter<String> unitSizeAdapter;

    // Lists from ingredient database
    public static List<String> ingredients;;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ingredients_screen);

        // Instantiate objects
        unitSizeSpin = (Spinner) findViewById(R.id.spin_unit_size);
        ingredientsSpin = (Spinner) findViewById(R.id.spin_ingredient_name);

        // List of sizes added with database sizes - no duplicates
        unitSizeList = new ArrayList<String>();
        sizeSet = new HashSet<String>();

        // List of ingredients from database
        ingredients = HomeScreenActivity.dbHandler.databaseIngredientList();

        // Adapters for spinners
        unitSizeAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, unitSizeList);
        ingredientAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, ingredients);

        ingredientsSpin.setAdapter(ingredientAdapter);
        unitSizeSpin.setAdapter(unitSizeAdapter);

        ingredientAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        unitSizeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        updateSizeList();

        ingredientsSpin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                unitSizeSpin.setSelection(unitSizeAdapter.getPosition(HomeScreenActivity.dbHandler.getIngredientSize(ingredientsSpin.getItemAtPosition(position).toString())));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Nothing for now
            }
        });

        unitSizeSpin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                HomeScreenActivity.dbHandler.changeSize(ingredientsSpin.getSelectedItem().toString(), unitSizeSpin.getItemAtPosition(position).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Nothing for now
            }
        });
    }

    // Update size list
    public static void updateSizeList() {
        sizeSet.addAll(unitSizeList);
        sizeSet.addAll(HomeScreenActivity.dbHandler.databaseSizeList());
        unitSizeList.clear();
        unitSizeList.addAll(sizeSet);
        Collections.sort(unitSizeList);
        unitSizeAdapter.notifyDataSetChanged();
    }

    // Method called in button attributes
    public void Add_Ingredient_Method(View v) {

        AddIngredient addIngredient = new AddIngredient();
        addIngredient.show(getFragmentManager(), "AddIngredient");
    }

    // Method called in button attributes
    public void Add_Size_Method(View v) {

        AddUnitSize addUnitSize = new AddUnitSize();
        addUnitSize.show(getFragmentManager(), "AddUnitSize");
    }


}
