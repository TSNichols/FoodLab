package tsnichols.foodlab;

import android.app.DialogFragment;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class AddUnitSize extends DialogFragment implements View.OnClickListener {

    Button add_size_btn;
    Button cancel_size_btn;
    EditText unitSizeEditText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this dialog fragment
        View view = inflater.inflate(R.layout.fragment_add_unit_size, container, false);
        setCancelable(false);

        add_size_btn = (Button) view.findViewById(R.id.btn_dialog_add_size);
        cancel_size_btn = (Button) view.findViewById(R.id.btn_dialog_cancel_size);

        add_size_btn.setOnClickListener(this);
        cancel_size_btn.setOnClickListener(this);

        unitSizeEditText = (EditText) view.findViewById(R.id.editText_enter_size_name);

        return view;
    }

    public void onClick(View view) {

        EditText unitNew_edittext = (EditText) view.findViewById(R.id.editText_enter_size_name);

        if (view.getId() == R.id.btn_dialog_add_size) {
            if (unitSizeEditText.getText().length() != 0) {
                Toast.makeText(getActivity(), "Size added", Toast.LENGTH_SHORT).show();
            }
            Toast.makeText(getActivity(), "Add was clicked", Toast.LENGTH_SHORT).show();
            dismiss();
        } else {
            Toast.makeText(getActivity(), "Cancel was clicked", Toast.LENGTH_SHORT).show();
            dismiss();
        }
    }
}