package tsnichols.foodlab;

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
import java.util.List;

public class Ingredients_Screen extends AppCompatActivity {

    // Objects
    public static Spinner unitSizeSpin;
    public static Spinner ingredientsSpin;

    // Array list independent of ingredient database
    public static ArrayList<String> unitSizeList;
    public static ArrayAdapter<String> unitSizeAdapter;

    // Adapters to display database info
    public static ArrayAdapter<String> ingredientAdapter;
    public static ArrayAdapter<String> ingredientSizeAdapter;

    // Lists from ingredient database
    public static List<String> ingredients;
    public static List<String> ingredientSize;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ingredients_screen);

        // Instantiate objects
        unitSizeSpin = (Spinner) findViewById(R.id.spin_unit_size);
        ingredientsSpin = (Spinner) findViewById(R.id.spin_ingredient_name);

        // Array List independent of ingredient database
        unitSizeList = new ArrayList<String>();
        unitSizeAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, unitSizeList);

        // List holds all database entries, each with name and size attributes; Adapter pulls each for each spinner
        ingredients = HomeScreenActivity.dbHandler.databaseIngredientList();
        // List only holds unique size entries with only size attributes
        ingredientSize = HomeScreenActivity.dbHandler.databaseSizeList();

        // List generated only has unique sizes from database - adapter pulls only size attribute (only attribute for this list)
        ingredientSizeAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, ingredientSize);
        unitSizeSpin.setAdapter(ingredientSizeAdapter);
        ingredientSizeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Adapter pulls name attribute from list
        ingredientAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, ingredients);
        ingredientsSpin.setAdapter(ingredientAdapter);
        ingredientAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ingredientsSpin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                unitSizeSpin.setSelection(ingredientSizeAdapter.getPosition(HomeScreenActivity.dbHandler.getIngredientSize(ingredientsSpin.getItemAtPosition(position).toString())));
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
