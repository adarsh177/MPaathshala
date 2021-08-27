package com.affix.myapplication;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.affix.myapplication.BasicFunctions.spinnerGenerator;


public class profileActivity extends AppCompatActivity {
    private CircleImageView profilePic;
    private Spinner branch, batch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        profilePic = findViewById(R.id.courceImg);
        batch=findViewById(R.id.courseBatch);
        branch=findViewById(R.id.courseBranch);
        spinnerGenerator(batch, R.array.Batch, this);
        spinnerGenerator(branch, R.array.Branch,this);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }


}