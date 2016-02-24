package tsnichols.foodlab;

import android.support.v7.app.AppCompatActivity;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.view.View;
import android.view.View.OnClickListener;


public class HomeScreenActivity extends AppCompatActivity {

    ImageButton btn_calender;
    ImageButton btn_recipes;
    ImageButton btn_groceries;
    ImageButton btn_ingredients;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_screen);
        addListenerOnButton();
    }

    public void addListenerOnButton(){

        btn_calender = (ImageButton) findViewById(R.id.calender_btn);
        btn_groceries = (ImageButton) findViewById(R.id.groceries_btn);
        btn_recipes = (ImageButton) findViewById(R.id.recipes_btn);
        btn_ingredients = (ImageButton) findViewById(R.id.ingredients_btn);

        //Runs the Calender_Screen java activity
        btn_calender.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Calender_Screen.class);
                startActivity(intent);
            }
        });

        //Runs the Recipes_Screen activity
        btn_recipes.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Recipes_Screen.class);
                startActivity(intent);
            }
        });

        //Runs the Ingredients_Screen activity
        btn_ingredients.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Ingredients_Screen.class);
                startActivity(intent);
            }
        });

    }


}
