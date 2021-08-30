package com.example.myapplication.Teacher;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.MimeTypeMap;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import java.util.HashMap;

import de.hdodenhof.circleimageview.CircleImageView;

public class CreateCourse extends AppCompatActivity {
    private ImageView coursePic;
    private Uri imageUri;
    private static final int imageRequest = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_course);
        coursePic = findViewById(R.id.courseImg);
    }

//    private void openImage() {
//        Intent intent=new Intent();
//        intent.setType("image/*");
//        intent.setAction(Intent.ACTION_GET_CONTENT);
//        startActivityForResult(intent,imageRequest);
//    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if(requestCode==imageRequest && resultCode==RESULT_OK){
//            assert data != null;
//            imageUri=data.getData();
//            uploadImage();
//        }
//    }
//    private String getfilextention(Uri uri){
//        ContentResolver contentResolver = getContentResolver();
//        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
//        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));
//    }
//    private void uploadImage(){
//        final ProgressDialog pd= new ProgressDialog(this);
//        pd.setMessage("uploading");
//        pd.show();
//        if(imageUri != null){
//            final StorageReference fileref = FirebaseStorage.getInstance().getReference().child("uploads").child(System.currentTimeMillis()+"."+getfilextention(imageUri));
//            fileref.putFile(imageUri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
//                @Override
//                public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
//                    if(task.isSuccessful()){
//                        fileref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
//                            @Override
//                            public void onSuccess(Uri uri) {
//                                String url = uri.toString();
//                                pd.dismiss();
//                                usersModel model = new usersModel();
//                                user= FirebaseAuth.getInstance().getCurrentUser();
//                                userdata= FirebaseDatabase.getInstance().getReference().child("users").child(user.getUid());
//                                HashMap<String ,Object> usermap=new HashMap<>();
//                                usermap.put("imageurl", url);
//                                userdata.updateChildren(usermap);
//                                Toast.makeText(profileActivity.this, "upload successful", Toast.LENGTH_LONG).show();
//                            }
//                        }).addOnFailureListener(new OnFailureListener() {
//                            @Override
//                            public void onFailure(@NonNull Exception e) {
//                                Toast.makeText(profileActivity.this, "Error : " + e.toString(), Toast.LENGTH_LONG).show();
//                            }
//                        });
//                    }else{
//                        Toast.makeText(profileActivity.this, "Error : " + task.getException().toString(), Toast.LENGTH_LONG).show();
//                    }
//
//                }
//            });
//        }
//    }
//
//    public void setProfileImage(){
//        DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference("users").child(user.getUid());
//        databaseReference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                usersModel model = dataSnapshot.getValue(usersModel.class);
//                if (model.getImageurl() != null) {
//                    Picasso.get().load(Uri.parse(model.getImageurl())).into(profilePic);
//                }else{
//                    profilePic.setImageResource(R.drawable.ic_launcher_foreground);
//                }
//                statusS=model.getStatus();
//                genderS=model.getGender();
//                countryS=model.getCountry();
//                professionS=model.getProfession();
//                languageS=model.getLanguage();
//
//                statusEditor.setText(statusS);
//                professionEditor.setText(professionS);
//                countryEditor.setText(countryS);
//                genderEditor.setText(genderS);
//                languagesEditor.setText(languageS);
//                name.setText((model.getUsername()).substring(0,1).toUpperCase()+(model.getUsername()).substring(1));
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
//
//    }

}