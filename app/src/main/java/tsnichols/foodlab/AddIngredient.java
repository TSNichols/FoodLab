package tsnichols.foodlab;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class AddIngredient extends DialogFragment implements View.OnClickListener {

    Button add_btn;
    Button cancel_btn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this dialog fragment
    View view = inflater.inflate(R.layout.fragment_add_ingredient, container, false);
    setCancelable(false);

    add_btn = (Button) view.findViewById(R.id.btn_dialog_add_ingred);
    cancel_btn = (Button) view.findViewById(R.id.btn_dialog_cancel_ingred);

    add_btn.setOnClickListener(this);
    cancel_btn.setOnClickListener(this);


    return view;
}

    public void onClick(View view) {
        if (view.getId() == R.id.btn_dialog_add_ingred) {
            Toast.makeText(getActivity(), "Add was clicked", Toast.LENGTH_SHORT).show();
            dismiss();
        }
        else {
            Toast.makeText(getActivity(), "Cancel was clicked", Toast.LENGTH_SHORT).show();
            dismiss();
        }
    }
}

