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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_course);
        coursePic = findViewById(R.id.courseImg);
    }
}