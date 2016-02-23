package tsnichols.foodlab;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

public class Ingredients_Screen extends AppCompatActivity {

    Button New_Ingredient_BTN;
    Button New_Size_BTN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ingredients_screen);
        addListenerOnButton();
    }

    public void addListenerOnButton(){

        New_Ingredient_BTN = (Button) findViewById(R.id.btn_new_ingredient);
        New_Size_BTN = (Button) findViewById(R.id.btn_new_size);




    }
}
