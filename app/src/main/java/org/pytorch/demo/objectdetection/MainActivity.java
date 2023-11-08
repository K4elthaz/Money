package org.pytorch.demo.objectdetection;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;


// https://github.com/pytorch/android-demo-app/tree/master/ObjectDetection
public class MainActivity extends AppCompatActivity implements Runnable {
    private static final int CAMERA_PERMISSION_REQUEST_CODE = 200;
    EditText editTextEmail, editTextPassword;
    Button buttonLogin;
//    FirebaseAuth mAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted, request the permission
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CAMERA},
                    CAMERA_PERMISSION_REQUEST_CODE);
        } else {
            // Permission already granted, proceed with your camera-related code
            // ...
        }

//        mAuth = FirebaseAuth.getInstance();
//        editTextEmail = findViewById(R.id.email_edittext);
//        editTextPassword = findViewById(R.id.password_edittext);
//        buttonLogin = findViewById(R.id.login_button);
//        buttonLogin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // code to be executed when button is clicked
//                String email, password;
//                email = String.valueOf((editTextEmail.getText()));
//                password = String.valueOf((editTextPassword.getText()));
//
//                if (TextUtils.isEmpty((email))) {
//                    Toast.makeText(MainActivity.this, "Enter Email", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                if (TextUtils.isEmpty((password))) {
//                    Toast.makeText(MainActivity.this, "Enter Password", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                mAuth.signInWithEmailAndPassword(email, password)
//                        .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
//                            @Override
//                            public void onComplete(@NonNull Task<AuthResult> task) {
//                                if (task.isSuccessful()) {
//                                    // Sign in success, update UI with the signed-in user's information
//                                    FirebaseUser user = mAuth.getCurrentUser();
//                                    Intent i = new Intent(MainActivity.this, HomeActivity.class);
//                                    startActivity(i);
//                                } else {
//                                    // If sign in fails, display a message to the user.
//                                    Toast.makeText(MainActivity.this, "Authentication failed.",
//                                            Toast.LENGTH_SHORT).show();
//                                }
//                            }
//                        });
//
//
//
//
//
//
//            }
//        });

//        TextView passwordLink = findViewById(R.id.password_link);
//        passwordLink.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Handle the click event for the password link here
//                // Open the ForgotPasswordActivity when the password link is clicked
//                Intent intent = new Intent(MainActivity.this, ForgotPasswordActivity.class);
//                startActivity(intent);
//            }
//        });
//
//        TextView signUpLink = findViewById(R.id.create_account);
//        signUpLink.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Handle the click event for the sign-up link here
//                // Open the RegisterActivity when the sign-up link is clicked
//                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
//                startActivity(intent);
//            }
//        });

    }

    @Override
    public void run() {

    }
}
