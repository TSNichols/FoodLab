package tsnichols.foodlab;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class AddIngredient extends DialogFragment implements View.OnClickListener {

    Button add_btn;
    Button cancel_btn;
    EditText eTxtIngredientName;
    Spinner unitSizeSpin;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this dialog fragment
        View view = inflater.inflate(R.layout.fragment_add_ingredient, container, false);
        setCancelable(false);
        //

        // Instantiate objects
        add_btn = (Button) view.findViewById(R.id.btn_dialog_add_ingred);
        cancel_btn = (Button) view.findViewById(R.id.btn_dialog_cancel_ingred);
        eTxtIngredientName = (EditText) view.findViewById(R.id.etxt_add_ingredient_name);
        unitSizeSpin = (Spinner) view.findViewById(R.id.spin_unit_size);

        // Tells the spinner what objects to have
        unitSizeSpin.setAdapter(Ingredients_Screen.unitSizeAdapter);

        // Click listeners
        add_btn.setOnClickListener(this);
        cancel_btn.setOnClickListener(this);


    return view;
}

    public void onClick(View view) {
        if (view.getId() == R.id.btn_dialog_add_ingred) {

            // If there is anything in the text box
            if (eTxtIngredientName.getText().length() != 0) {

                // Add to database
                HomeScreenActivity.dbHandler.addIngredient(eTxtIngredientName.getText().toString(), unitSizeSpin.getSelectedItem().toString());

                // Update size list
                Ingredients_Screen.updateSizeList();

                // Clears lists
                Ingredients_Screen.ingredients.clear();

                // ReAdd all entries from database
                Ingredients_Screen.ingredients.addAll(HomeScreenActivity.dbHandler.databaseIngredientList());

                // Notify changes to adapter
                Ingredients_Screen.ingredientAdapter.notifyDataSetChanged();

                // Toast unnecessary - just a debugging tool
                Toast.makeText(getActivity(), eTxtIngredientName.getText() + " added", Toast.LENGTH_SHORT).show();

                eTxtIngredientName.setText("");
            }
            dismiss();
        }
        else {
            Toast.makeText(getActivity(), "Cancel was clicked", Toast.LENGTH_SHORT).show();
            dismiss();
        }
    }
}

