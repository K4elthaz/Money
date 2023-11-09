package org.pytorch.demo.objectdetection;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.google.android.material.navigation.NavigationView;

public class UserGuideActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_guide);

        NavigationView navigationView = findViewById(R.id.navigationView);
        ImageView sidemenubar = findViewById(R.id.sidemenubar);
        View overlay = findViewById(R.id.overlay);
        ImageView homeBtn = findViewById(R.id.homebtnimg);
        ImageView backBtn = findViewById(R.id.backbtnimg);
        ImageView BoxImg = findViewById(R.id.boxed);

        // Set initial visibility of NavigationView to GONE
        navigationView.setVisibility(View.GONE);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                // Handle item click here
                switch (item.getItemId()) {
                    case R.id.menu_item1:
                        // Open activity for menu_item1
                        startActivity(new Intent(UserGuideActivity.this, AboutActivity.class));
                        break;
                    case R.id.menu_item2:
                        // Open activity for menu_item2
                        startActivity(new Intent(UserGuideActivity.this, UserGuideActivity.class));
                        break;
                    case R.id.menu_item3:
                        // Open activity for menu_item3
                        startActivity(new Intent(UserGuideActivity.this, DevelopersActivity.class));
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
                    homeBtn.setVisibility(View.VISIBLE);
                    BoxImg.setVisibility(View.VISIBLE);
                    backBtn.setVisibility(View.VISIBLE);
                    sidemenubar.setVisibility(View.VISIBLE);
                } else {
                    navigationView.setVisibility(View.VISIBLE);
                    overlay.setVisibility(View.VISIBLE);
                    // Hide the buttons and sidemenubar when opening sidemenubar
                    homeBtn.setVisibility(View.GONE);
                    backBtn.setVisibility(View.GONE);
                    BoxImg.setVisibility(View.GONE);
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
                homeBtn.setVisibility(View.VISIBLE);
                backBtn.setVisibility(View.VISIBLE);
                BoxImg.setVisibility(View.VISIBLE);
                sidemenubar.setVisibility(View.VISIBLE);
            }
        });

        ImageView homeButton = findViewById(R.id.homebtnimg);
        ImageView backButton = findViewById(R.id.backbtnimg);

        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Navigate to HomeActivity
                Intent intent = new Intent(UserGuideActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Go back to the previous activity (assuming it's HomeActivity)
                onBackPressed();
            }
        });
    }
}