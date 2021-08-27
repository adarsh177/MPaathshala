package com.affix.myapplication;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import de.hdodenhof.circleimageview.CircleImageView;


public class profileActivity extends AppCompatActivity {
    private CircleImageView profilePic;
    private Spinner branch, batch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        profilePic = findViewById(R.id.imageView);
        batch=findViewById(R.id.batch);
        branch=findViewById(R.id.branch);
        spinnerGenerator(batch, R.array.Batch);
        spinnerGenerator(branch, R.array.Branch);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
    public void spinnerGenerator(Spinner spinner, int array) {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(profileActivity.this,array,R.layout.support_simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

}