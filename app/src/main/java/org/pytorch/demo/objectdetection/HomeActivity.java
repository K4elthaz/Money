package org.pytorch.demo.objectdetection;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
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

        // Set initial visibility of NavigationView to GONE
        navigationView.setVisibility(View.GONE);

        // Set click listener for sidemenubar
        sidemenubar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Toggle visibility of NavigationView
                if (navigationView.getVisibility() == View.VISIBLE) {
                    navigationView.setVisibility(View.GONE);
                    overlay.setVisibility(View.GONE);
                } else {
                    navigationView.setVisibility(View.VISIBLE);
                    overlay.setVisibility(View.VISIBLE);
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
            }
        });

        ImageView convertButton = findViewById(R.id.convertImgBtn);

        convertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(HomeActivity.this, ConvertCurrencyActivity.class);
                startActivity(i);
            }
        });

        ImageView scanButton = findViewById(R.id.captureImgBtn);
        scanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeActivity.this, ActivityManual.class);
                startActivity(i);
            }
        });

        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        ImageView cameraButton = findViewById(R.id.uploadImgBtn);
        cameraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                if (cameraIntent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(cameraIntent, CAMERA_REQUEST_CODE);
                }
            }
        });
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
