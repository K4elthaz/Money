package org.pytorch.demo.objectdetection;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

public class Capture extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_capture);

        NavigationView navigationView = findViewById(R.id.navigationView);
        ImageView sidemenubar = findViewById(R.id.sidemenubar);
        View overlay = findViewById(R.id.overlay);
        ImageView homeBtn = findViewById(R.id.homebtnimg);
        ImageView backBtn = findViewById(R.id.backbtnimg);
        Button dBtn = findViewById(R.id.detectButton);
        Button sBtn = findViewById(R.id.selectButton);
        ImageView frnNT = findViewById(R.id.frntbankNote);
        TextView txt1 = findViewById(R.id.textView3);
        ImageView bckNT = findViewById(R.id.backbankNote);
        TextView txt2 = findViewById(R.id.textView4);

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
                        startActivity(new Intent(Capture.this, AboutActivity.class));
                        break;
                    case R.id.menu_item2:
                        // Open activity for menu_item2
                        startActivity(new Intent(Capture.this, UserGuideActivity.class));
                        break;
                    case R.id.menu_item3:
                        // Open activity for menu_item3
                        startActivity(new Intent(Capture.this, DevelopersActivity.class));
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
                    sidemenubar.setVisibility(View.VISIBLE);
                } else {
                    navigationView.setVisibility(View.VISIBLE);
                    overlay.setVisibility(View.VISIBLE);
                    // Hide the buttons and sidemenubar when opening sidemenubar
                    sidemenubar.setVisibility(View.GONE);
                }
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
                    backBtn.setVisibility(View.VISIBLE);
                    dBtn.setVisibility(View.VISIBLE);
                    sBtn.setVisibility(View.VISIBLE);
                    frnNT.setVisibility(View.VISIBLE);
                    txt1.setVisibility(View.VISIBLE);
                    bckNT.setVisibility(View.VISIBLE);
                    txt2.setVisibility(View.VISIBLE);
                    sidemenubar.setVisibility(View.VISIBLE);
                } else {
                    navigationView.setVisibility(View.VISIBLE);
                    overlay.setVisibility(View.VISIBLE);
                    // Hide the buttons and sidemenubar when opening sidemenubar
                    homeBtn.setVisibility(View.GONE);
                    backBtn.setVisibility(View.GONE);
                    dBtn.setVisibility(View.GONE);
                    sBtn.setVisibility(View.GONE);
                    frnNT.setVisibility(View.GONE);
                    txt1.setVisibility(View.GONE);
                    bckNT.setVisibility(View.GONE);
                    txt2.setVisibility(View.GONE);
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
                dBtn.setVisibility(View.VISIBLE);
                sBtn.setVisibility(View.VISIBLE);
                frnNT.setVisibility(View.VISIBLE);
                txt1.setVisibility(View.VISIBLE);
                bckNT.setVisibility(View.VISIBLE);
                txt2.setVisibility(View.VISIBLE);
                sidemenubar.setVisibility(View.VISIBLE);
            }
        });


//        HOME AND BACK BTN FUNCTION
        ImageView homeButton = findViewById(R.id.homebtnimg);
        ImageView backButton = findViewById(R.id.backbtnimg);

        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Navigate to HomeActivity
                Intent intent = new Intent(Capture.this, HomeActivity.class);
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