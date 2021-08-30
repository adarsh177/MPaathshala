package com.example.myapplication;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.speech.tts.Voice;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract;
import com.firebase.ui.auth.IdpResponse;
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult;
import com.firebase.ui.auth.util.ExtraConstants;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.ActionCodeSettings;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class SignUp extends AppCompatActivity {
    private Spinner type;
    private Button create;
    private TextView email,username,password;
    private FirebaseAuth auth;
    private FirebaseUser user;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        type = findViewById(R.id.typeSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.Type, R.layout.spinner_bg);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        type.setAdapter(adapter);

        email=findViewById(R.id.email);
        username=findViewById(R.id.username);
        password=findViewById(R.id.password);
        auth= FirebaseAuth.getInstance();
        create = findViewById(R.id.create);
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailInput = email.getText().toString();
                String passwordInput = password.getText().toString();
                String usernameInput = username.getText().toString();
                if (TextUtils.isEmpty(emailInput) || TextUtils.isEmpty(passwordInput) || TextUtils.isEmpty(usernameInput)) {
                    Toast.makeText(SignUp.this, "Please fill all the information", Toast.LENGTH_SHORT).show();
                } else if (passwordInput.length() < 6) {
                    Toast.makeText(SignUp.this, "Password must contain at least 6 characters", Toast.LENGTH_SHORT).show();
                }else {
                    registerUser(emailInput.trim(), passwordInput.trim(), type.getSelectedItem().toString());
                }

//
//                ActionCodeSettings actionCodeSettings = ActionCodeSettings.newBuilder()
//                        .setAndroidPackageName(
//                                /* yourPackageName= */ "...",
//                                /* installIfNotAvailable= */ true,
//                                /* minimumVersion= */ null)
//                        .setHandleCodeInApp(true) // This must be set to true
//                        .setUrl("https://google.com") // This URL needs to be whitelisted
//                        .build();
//
//                List<AuthUI.IdpConfig> providers = Arrays.asList(
//                        new AuthUI.IdpConfig.EmailBuilder()
//                                .enableEmailLinkSignIn()
//                                .setActionCodeSettings(actionCodeSettings)
//                                .build(),
//                        new AuthUI.IdpConfig.GoogleBuilder().build());
//                if (AuthUI.canHandleIntent(getIntent())) {
//                    if (getIntent().getExtras() == null) {
//                        return;
//                    }
//                    String link = getIntent().getExtras().getString(ExtraConstants.EMAIL_LINK_SIGN_IN);
//                    if (link != null) {
//                        Intent signInIntent = AuthUI.getInstance()
//                                .createSignInIntentBuilder()
//                                .setEmailLink(link)
//                                .setAvailableProviders(providers)
//                                .build();
//                        signInLauncher.launch(signInIntent);
//                    }
//                }
//            }
            }
        });
    }

    private void registerUser(final String email, String password, String type) {
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(SignUp.this, new OnCompleteListener<AuthResult>() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    auth.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(getApplicationContext(), "Verification Link sent successfully", Toast.LENGTH_SHORT).show();
                                user = FirebaseAuth.getInstance().getCurrentUser();
                                HashMap<String, String> usermap = new HashMap<>();
                                usermap.put("userId", user.getUid());
                                usermap.put("name", username.getText().toString().trim());
                                usermap.put("email", user.getEmail().trim());

                                db.collection(""+type).document(user.getUid()).set(usermap)
                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void aVoid) {

                                            }
                                        })
                                        .addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {

                                            }
                                        });

                                finish();
                            } else {
                                Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
                if (!email.contains("@")) {
                    Toast.makeText(SignUp.this, "Email address does not exist", Toast.LENGTH_SHORT).show();
                }
                if (!task.isSuccessful()) {
                    Toast.makeText(SignUp.this, "user already exist", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

//    private final ActivityResultLauncher<Intent> signInLauncher = registerForActivityResult(
//            new FirebaseAuthUIActivityResultContract(),
//            new ActivityResultCallback<FirebaseAuthUIAuthenticationResult>() {
//                @Override
//                public void onActivityResult(FirebaseAuthUIAuthenticationResult result) {
//                    onSignInResult(result);
//                }
//            }
//    );
//    private void onSignInResult(FirebaseAuthUIAuthenticationResult result) {
//        IdpResponse response = result.getIdpResponse();
//        if (result.getResultCode() == RESULT_OK) {
//            // Successfully signed in
//            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//            // ...
//        } else {
//            // Sign in failed. If response is null the user canceled the
//            // sign-in flow using the back button. Otherwise check
//            // response.getError().getErrorCode() and handle the error.
//            // ...
//        }
//    }
}