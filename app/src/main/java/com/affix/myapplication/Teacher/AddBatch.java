package com.affix.myapplication.Teacher;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Spinner;

import com.affix.myapplication.R;

import static com.affix.myapplication.BasicFunctions.spinnerGenerator;

public class AddBatch extends AppCompatActivity {
    private Spinner branch, batch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_batch);

        batch=findViewById(R.id.addBatch);
        branch=findViewById(R.id.addBranch);
        spinnerGenerator(batch, R.array.Batch, this);
        spinnerGenerator(branch, R.array.All_Branch,this);
    }
}