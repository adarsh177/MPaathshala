package com.example.myapplication.Teacher;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Spinner;

import com.example.myapplication.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static com.example.myapplication.BasicFunctions.spinnerGenerator;

public class AddBatch extends AppCompatActivity {
    private Spinner branch, batch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_batch);

        batch=findViewById(R.id.addBatch);
        branch=findViewById(R.id.addBranch);
        spinnerGenerator(batch, R.array.Batch, this);
        spinnerGenerator(branch, R.array.Branch,this);


    }
    private void addBatch(){
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("course");
    }
}