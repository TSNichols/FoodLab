package tsnichols.foodlab;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class Ingredients_Screen extends AppCompatActivity {

    Button New_Size_BTN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ingredients_screen);

        New_Size_BTN = (Button) findViewById(R.id.btn_new_size);
    }


    public void Add_Ingredient_Method(View v){

        AddIngredient addIngredient = new AddIngredient();
        addIngredient.show(getFragmentManager(),"AddIngredient");
    }

    public void Add_Size_Method(View v){

        AddUnitSize addUnitSize = new AddUnitSize();
        addUnitSize.show(getFragmentManager(), "AddUnitSize");
    }

}
