package com.affix.myapplication;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class BasicFunctions {
    public static void spinnerGenerator(Spinner spinner, int array, Context context) {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(context,array,R.layout.support_simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }
}