package com.example.bartek.myapplication;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by bartek on 11/14/2015.
 */
public class ProgressBarFragment extends DialogFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.progress_bar_fragment, container,
                false);

        getDialog().setTitle("Pobieranie...");
        return rootView;
    }
}
