package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.example.myapplication.BasicFunctions.spinnerGenerator;


public class profileActivity extends AppCompatActivity {
    private CircleImageView profilePic;
    private Spinner branch, batch;
    private Button save;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        profilePic = findViewById(R.id.Img);
        batch=findViewById(R.id.Batch);
        branch=findViewById(R.id.Branch);
        save=findViewById(R.id.button4);
        spinnerGenerator(batch, R.array.Batch, this);
        spinnerGenerator(branch, R.array.Branch,this);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(profileActivity.this,MainActivity.class));
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }


}