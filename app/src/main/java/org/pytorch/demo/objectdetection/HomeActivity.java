package org.pytorch.demo.objectdetection;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class HomeActivity extends AppCompatActivity {

    private static final int CAMERA_REQUEST_CODE = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        NavigationView navigationView = findViewById(R.id.navigationView);
        ImageView sidemenubar = findViewById(R.id.sidemenubar);
        View overlay = findViewById(R.id.overlay);
        ImageView captureImgBtn = findViewById(R.id.captureImgBtn);
        ImageView uploadImgBtn = findViewById(R.id.uploadImgBtn);
        ImageView convertImgBtn = findViewById(R.id.convertImgBtn);

// Set initial visibility of NavigationView to GONE
        navigationView.setVisibility(View.GONE);


        // Set item click listener
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                // Handle item click here
                switch (item.getItemId()) {
                    case R.id.menu_item1:
                        // Open activity for menu_item1
                        startActivity(new Intent(HomeActivity.this, AboutActivity.class));
                        break;
                    case R.id.menu_item2:
                        // Open activity for menu_item2
                        startActivity(new Intent(HomeActivity.this, UserGuideActivity.class));
                        break;
                    case R.id.menu_item3:
                        // Open activity for menu_item3
                        startActivity(new Intent(HomeActivity.this, DevelopersActivity.class));
                        break;
                }

                return true;
            }
        });


// Set click listener for sidemenubar
        sidemenubar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Toggle visibility of NavigationView
                if (navigationView.getVisibility() == View.VISIBLE) {
                    navigationView.setVisibility(View.GONE);
                    overlay.setVisibility(View.GONE);
                    // Show the buttons and sidemenubar when closing sidemenubar
                    captureImgBtn.setVisibility(View.VISIBLE);
                    uploadImgBtn.setVisibility(View.VISIBLE);
                    convertImgBtn.setVisibility(View.VISIBLE);
                    sidemenubar.setVisibility(View.VISIBLE);
                } else {
                    navigationView.setVisibility(View.VISIBLE);
                    overlay.setVisibility(View.VISIBLE);
                    // Hide the buttons and sidemenubar when opening sidemenubar
                    captureImgBtn.setVisibility(View.GONE);
                    uploadImgBtn.setVisibility(View.GONE);
                    convertImgBtn.setVisibility(View.GONE);
                    sidemenubar.setVisibility(View.GONE);
                }
            }
        });

// Set click listener for the overlay
        overlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Close NavigationView and hide the overlay
                navigationView.setVisibility(View.GONE);
                overlay.setVisibility(View.GONE);
                // Show the buttons and sidemenubar when closing sidemenubar from overlay
                captureImgBtn.setVisibility(View.VISIBLE);
                uploadImgBtn.setVisibility(View.VISIBLE);
                convertImgBtn.setVisibility(View.VISIBLE);
                sidemenubar.setVisibility(View.VISIBLE);
            }
        });

//        MENU BAR CODE


// CONVERT BTN
        ImageView convertButton = findViewById(R.id.convertImgBtn);

        convertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(HomeActivity.this, ConvertCurrencyActivity.class);
                startActivity(i);
            }
        });


//        Capture BTN
//        NEED NG BACKEND CODE PARA MA OPEN YUNG CAPTURE UI
//        CONNECTED SA RESULTVIEW.JAVA


        ImageView captureButton = findViewById(R.id.captureImgBtn);
        captureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeActivity.this, Capture.class);
                startActivity(i);
            }
        });

//        UPLOAD BTN
        ImageView uploadBtn = findViewById(R.id.uploadImgBtn);
        uploadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(HomeActivity.this, ActivityManual.class);
                startActivity(i);
            }
        });
//         Diko alam kung ano to pero pagka meron kasi nito naka comment di ko marun yung activity manual pag pinindot yung upload btn


//        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
//        ImageView cameraButton = findViewById(R.id.uploadImgBtn);
//        cameraButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//
//                if (cameraIntent.resolveActivity(getPackageManager()) != null) {
//                    startActivityForResult(cameraIntent, CAMERA_REQUEST_CODE);
//                }
//            }
//        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CAMERA_REQUEST_CODE && resultCode == RESULT_OK) {
            Bitmap imageBitmap = (Bitmap) data.getExtras().get("data");
            if (imageBitmap != null) {
                Intent intent = new Intent(HomeActivity.this, MainDetectActivity.class);
                intent.putExtra("captured_image", imageBitmap);
                startActivity(intent);
            }
        }
    }



}
