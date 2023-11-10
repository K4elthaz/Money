package org.pytorch.demo.objectdetection;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

public class UserGuideActivity extends AppCompatActivity {

    private ConstraintLayout navMenu;

    private ImageView icon1,icon2,icon3,hide,sideb,hb,bb,box;
    private TextView inf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_guide);

        navMenu = findViewById(R.id.menu_drawer);
        icon1 = findViewById(R.id.imageViewIcon1);
        icon2 = findViewById(R.id.imageViewIcon2);
        icon3 = findViewById(R.id.imageViewIcon3);
        hide = findViewById(R.id.hidemenu);
        sideb = findViewById(R.id.sidemenubar);
        hb = findViewById(R.id.homebtnimg);
        bb = findViewById(R.id.backbtnimg);
        box = findViewById(R.id.boxed);
        inf = findViewById(R.id.infouser);
        navMenu.setVisibility(View.GONE);

//        MENU BAR BTN CLICKLISTINER
        icon1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserGuideActivity.this, AboutActivity.class);
                startActivity(intent);
            }
        });

        icon2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserGuideActivity.this, UserGuideActivity.class);
                startActivity(intent);
            }
        });

        icon3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserGuideActivity.this, DevelopersActivity.class);
                startActivity(intent);
            }
        });

        hide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navMenu.setVisibility(View.GONE);
                sideb.setVisibility(View.VISIBLE);
                hb.setVisibility(View.VISIBLE);
                bb.setVisibility(View.VISIBLE);
                box.setVisibility(View.VISIBLE);
                inf.setVisibility(View.VISIBLE);
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
                finish();
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

    public void openNavMenu(View view) {
        if (navMenu.getVisibility() == View.VISIBLE) {
            navMenu.setVisibility(View.GONE);
        } else {
            navMenu.setVisibility(View.VISIBLE);
            sideb.setVisibility(View.GONE);
            hb.setVisibility(View.GONE);
            bb.setVisibility(View.GONE);
            box.setVisibility(View.GONE);
            inf.setVisibility(View.GONE);
        }
    }
}