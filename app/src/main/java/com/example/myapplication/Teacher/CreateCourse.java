package com.example.myapplication.Teacher;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.MainActivity;
import com.example.myapplication.Models.CourseModel;
import com.example.myapplication.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.HashMap;

public class CreateCourse extends AppCompatActivity {
    private ImageView coursePic;
    EditText name;
    Button btn;
    private Uri imageUri;
    private static final int imageRequest = 1;
    private FirebaseUser user;
    private DatabaseReference courseData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_course);
        coursePic = findViewById(R.id.courseImg);
        name=findViewById(R.id.courseName);
        btn=findViewById(R.id.createCourseBtn);
        user=FirebaseAuth.getInstance().getCurrentUser();
        coursePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(name.getText()==null) {
                    openImage();
                    name.setEnabled(false);
                }else{
                    Toast.makeText(CreateCourse.this, "Please Add a name first. name can't be changed after setting up the image.", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(name!=null){

                }
            }
        });
    }

    private void openImage() {
        Intent intent=new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,imageRequest);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==imageRequest && resultCode==RESULT_OK){
            assert data != null;
            imageUri=data.getData();
            uploadImage();
        }
    }
    private String getfilextention(Uri uri){
        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));
    }
    private void uploadImage(){
        final ProgressDialog pd= new ProgressDialog(this);
        pd.setMessage("uploading");
        pd.show();
        if(imageUri != null){
            final StorageReference fileref = FirebaseStorage.getInstance().getReference().child("uploads").child(System.currentTimeMillis()+"."+getfilextention(imageUri));
            fileref.putFile(imageUri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                    if(task.isSuccessful()){
                        fileref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                String url = uri.toString();
                                pd.dismiss();
                                CourseModel model = new CourseModel();
                                courseData = FirebaseDatabase.getInstance().getReference().child("course").child(name.getText().toString());
                                HashMap<String ,Object> usermap=new HashMap<>();
                                usermap.put("image", url);
                                usermap.put("teacher", user.getUid());
                                usermap.put("name",name.getText().toString());
                                courseData.updateChildren(usermap);
                                Toast.makeText(CreateCourse.this, "upload successful", Toast.LENGTH_LONG).show();
                                startActivity(new Intent(CreateCourse.this, MainActivity.class));
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(CreateCourse.this, "Error : " + e.toString(), Toast.LENGTH_LONG).show();
                            }
                        });
                    }else{
                        Toast.makeText(CreateCourse.this, "Error : " + task.getException().toString(), Toast.LENGTH_LONG).show();
                    }

                }
            });
        }
    }
}