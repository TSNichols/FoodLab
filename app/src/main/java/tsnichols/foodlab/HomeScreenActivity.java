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

        final Context context = this;

        btn_calender = (ImageButton) findViewById(R.id.calender_btn);
        btn_groceries = (ImageButton) findViewById(R.id.groceries_btn);
        btn_recipes = (ImageButton) findViewById(R.id.recipes_btn);
        btn_ingredients = (ImageButton) findViewById(R.id.ingredients_btn);

        btn_calender.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Calender_Screen.class);
                startActivity(intent);
            }
        });

        btn_recipes.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Recipes_Screen.class);
                startActivity(intent);
            }
        });

        btn_ingredients.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Ingredients_Screen.class);
                startActivity(intent);
            }
        });

    }


}
