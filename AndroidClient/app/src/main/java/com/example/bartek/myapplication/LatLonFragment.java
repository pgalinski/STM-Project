package com.example.bartek.myapplication;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by bartek on 11/14/2015.
 */
public class LatLonFragment extends DialogFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.lat_lon_fragment, container,
                false);
        Button okButton = (Button) rootView.findViewById(R.id.okButton);
        final EditText editTextTopLeftLat = (EditText) rootView.findViewById(R.id.topLeftLat);
        final EditText editTextTopLeftLon = (EditText) rootView.findViewById(R.id.topLeftLon);
        final EditText editTextBottomRightLat = (EditText) rootView.findViewById(R.id.bottomRightLat);
        final EditText editTextBottomRightLon = (EditText) rootView.findViewById(R.id.bottomRightLon);

        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    OnOk(new LatLon(editTextTopLeftLat.getText().toString(), editTextTopLeftLon.getText().toString()),
                            new LatLon(editTextBottomRightLat.getText().toString(), editTextBottomRightLon.getText().toString()));
                    getDialog().dismiss();
                } catch (Exception ex) {
                    getDialog().setTitle("Blad parsowania");

                }

            }
        });
        Button cancelButton = (Button) rootView.findViewById(R.id.cancelButton);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
            }
        });
        getDialog().setTitle("Podaj wspolrzedne");
        // Do something else
        return rootView;
    }

    public void OnOk(LatLon topLeft, LatLon bottomRight) {

    }
}
