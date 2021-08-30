package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

public class splashScreenA extends AppCompatActivity {

    ImageView logo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        logo=(ImageView) findViewById(R.id.logo_ss);
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        if (logo!=null){
            TranslateAnimation animate = new TranslateAnimation(0,0, -700, 0);
            animate.setDuration(1000);
            logo.startAnimation(animate);
            FirebaseAuth auth = FirebaseAuth.getInstance();
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            Thread background = new Thread() {
                public void run() {
                    try {
                        // Thread will sleep for 3 seconds
                        try {
                            sleep(2 * 1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        // After 3 seconds redirect to another intent
                        if (user != null) {
                            if(auth.getCurrentUser().isEmailVerified()){




                                //TODO complete

//                                if(user is teacher){
                                    startActivity(new Intent(splashScreenA.this, MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK));
//                                }else{
//                                    start profile activity
//                                }








                                }else{
                                Toast.makeText(splashScreenA.this, "Please Verify your mail", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(splashScreenA.this, Login.class));
                            }
                        } else {
                            startActivity(new Intent(splashScreenA.this, Login.class));
                        }
                        //Remove activity
                        finish();
                    } catch (Exception ignored) {
                    }
                }
            };
            // start thread
            background.start();
        }else{
            FirebaseAuth auth = FirebaseAuth.getInstance();
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            Thread background = new Thread() {
                public void run() {
                    try {
                        // Thread will sleep for 5 seconds
                        sleep(2 * 1000);
                        // After 3 seconds redirect to another intent
                        if (user != null) {
                            if (auth.getCurrentUser().isEmailVerified()) {
                                startActivity(new Intent(splashScreenA.this, MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK));
                            }else{
                                Toast.makeText(splashScreenA.this, "Please Verify your mail", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(splashScreenA.this, Login.class));
                            }
                        } else {
                            startActivity(new Intent(splashScreenA.this, Login.class));
                        }
                        //Remove activity
                        finish();
                    } catch (Exception ignored) {
                    }
                }
            };
        }
    }
}