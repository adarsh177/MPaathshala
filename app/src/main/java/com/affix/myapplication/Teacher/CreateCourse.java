package com.affix.myapplication.Teacher;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Spinner;

import com.affix.myapplication.R;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.affix.myapplication.BasicFunctions.spinnerGenerator;

public class CreateCourse extends AppCompatActivity {
    private ImageView coursePic;
    private Spinner branch, batch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_course);

        coursePic = findViewById(R.id.courceImg);
        batch=findViewById(R.id.courseBatch);
        branch=findViewById(R.id.courseBranch);
        spinnerGenerator(branch, R.array.All_Branch, this);
        spinnerGenerator(batch, R.array.Batch,this);
    }
}