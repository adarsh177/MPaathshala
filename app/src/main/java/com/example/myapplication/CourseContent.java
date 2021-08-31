package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.myapplication.Adapter.CourseDashboardAdapter;
import com.example.myapplication.Adapter.TestAdapter;
import com.example.myapplication.Models.CourseModel;
import com.example.myapplication.Models.TestModel;
import com.example.myapplication.Models.membersModel;
import com.example.myapplication.Teacher.AddBatch;
import com.example.myapplication.Teacher.AddContent;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static java.security.AccessController.getContext;

public class CourseContent extends AppCompatActivity {
    private Button addBatch, addContent;
    private RecyclerView recyclerView1,recyclerView2;
    private ArrayList<TestModel> Tests = new ArrayList<>();
    private TestAdapter testAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_content);
        Intent i = getIntent();
        addBatch = findViewById(R.id.button);
        addContent = findViewById(R.id.button2);
        recyclerView1=findViewById(R.id.testList);
        recyclerView2=findViewById(R.id.assignmentList);

        if(i.getStringExtra("teacher").equals(FirebaseAuth.getInstance().getCurrentUser().getUid())){
            addBatch.setVisibility(View.VISIBLE);
            addContent.setVisibility(View.VISIBLE);
            addBatch.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(CourseContent.this, AddBatch.class));
                }
            });
            addContent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(CourseContent.this, AddContent.class));
                }
            });
        }
        readTests(recyclerView1);
        readTests(recyclerView2);

    }

    public void readTests(RecyclerView recyclerView) {
        FirebaseUser me = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference("course").child("test");
        Query query = databaseReference.orderByChild("startTime");
        query.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Tests.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    TestModel testData = snapshot.getValue(TestModel.class);
                    Tests.add(testData);
                }
                testAdapter = new TestAdapter(CourseContent.this, Tests, getIntent().getStringExtra("teacher"));
                LinearLayoutManager manager =new LinearLayoutManager(CourseContent.this);
                manager.setReverseLayout(true);
                manager.setStackFromEnd(true);
                recyclerView.setLayoutManager(manager);
                recyclerView.setAdapter(testAdapter);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(CourseContent.this, "check your network connection", Toast.LENGTH_SHORT).show();

            }
        });
    }
}