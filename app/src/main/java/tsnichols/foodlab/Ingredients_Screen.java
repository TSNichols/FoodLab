package tsnichols.foodlab;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Ingredients_Screen extends AppCompatActivity {

    Button New_Size_BTN;
    Spinner unitSizeSpin;
    Spinner ingredientsSpin;

    // Array list independent of ingredient database
    public static ArrayList<String> unitSizeList;
    public static ArrayAdapter<String> unitSizeAdapter;
    // Adapters to display database info
    public static IngredientSpinnerAdapter ingredientAdapter;
    public static IngredientSizeSpinnerAdapter ingredientSizeAdapter;
    /**
    //public static ArrayList<String> ingredientList;
    //public static ArrayAdapter<String> ingredientAdapter;
    */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ingredients_screen);

        // Instantiate objects
        New_Size_BTN = (Button) findViewById(R.id.btn_new_size);
        unitSizeSpin = (Spinner) findViewById(R.id.spin_unit_size);
        ingredientsSpin = (Spinner) findViewById(R.id.spin_ingredient_name);


        // Array List independent of ingredient database
        unitSizeList = new ArrayList<String>();
        unitSizeAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, unitSizeList);

        /*****************************************************

        //unitSizeSpin.setAdapter(unitSizeAdapter);
        //unitSizeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        
        //ingredientList = new ArrayList<String>();
        //ingredientAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, ingredientList);
        //ingredientsSpin.setAdapter(ingredientAdapter);
        //ingredientAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        *******************************************************/

        // List holds all database entries, each with name and size attributes; Adapter pulls each for each spinner
        List<DBAddIngredient> ingredients = HomeScreenActivity.dbHandler.databaseToList();

        /** Adapter pulls size attributes from list - Will need to change later to only display unique sizes */
        ingredientSizeAdapter = new IngredientSizeSpinnerAdapter(this, android.R.layout.simple_spinner_item, ingredients);
        unitSizeSpin.setAdapter(ingredientSizeAdapter);
        ingredientSizeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Adapter pulls name attribute from list
        ingredientAdapter = new IngredientSpinnerAdapter(this, android.R.layout.simple_spinner_item, ingredients);
        ingredientsSpin.setAdapter(ingredientAdapter);
        ingredientAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //
    }

    // Method called in button attributes
    public void Add_Ingredient_Method(View v){

        AddIngredient addIngredient = new AddIngredient();
        addIngredient.show(getFragmentManager(),"AddIngredient");
    }

    // Method called in button attributes
    public void Add_Size_Method(View v){

        AddUnitSize addUnitSize = new AddUnitSize();
        addUnitSize.show(getFragmentManager(), "AddUnitSize");
    }






}
