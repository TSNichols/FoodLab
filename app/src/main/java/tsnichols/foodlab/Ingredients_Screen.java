package tsnichols.foodlab;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;

public class Ingredients_Screen extends AppCompatActivity {

    Button New_Size_BTN;
    Spinner unitSizeSpin;
    Spinner ingredientsSpin;
    public static ArrayList<String> unitSizeList;
    public static ArrayAdapter<String> unitSizeAdapter;
    public static ArrayList<String> ingredientList;
    public static ArrayAdapter<String> ingredientAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ingredients_screen);

        // Instantiate objects
        New_Size_BTN = (Button) findViewById(R.id.btn_new_size);
        unitSizeSpin = (Spinner) findViewById(R.id.spin_unit_size);
        ingredientsSpin = (Spinner) findViewById(R.id.spin_ingredient_name);


        // Needed for spinners to display the array lists
        unitSizeList = new ArrayList<String>();
        unitSizeAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, unitSizeList);
        unitSizeSpin.setAdapter(unitSizeAdapter);
        unitSizeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //
        ingredientList = new ArrayList<String>();
        ingredientAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, ingredientList);
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
