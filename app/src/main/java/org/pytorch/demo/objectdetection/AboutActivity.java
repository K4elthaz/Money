package org.pytorch.demo.objectdetection;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

public class AboutActivity extends AppCompatActivity {

    private ConstraintLayout navMenu;

    private ImageView icon1,icon2,icon3,hide,sideb,hb,bb,box;

    private TextView inf,capture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        navMenu = findViewById(R.id.menu_drawer);
        icon1 = findViewById(R.id.imageViewIcon1);
        icon2 = findViewById(R.id.imageViewIcon2);
        icon3 = findViewById(R.id.imageViewIcon3);
        hide = findViewById(R.id.hidemenu);
        sideb = findViewById(R.id.sidemenubar);
        hb = findViewById(R.id.homebtnimg);
        bb = findViewById(R.id.backbtnimg);
        box = findViewById(R.id.boxed);
        inf = findViewById(R.id.infoabt);
        navMenu.setVisibility(View.GONE);
        capture = findViewById(R.id.capture);

//        MENU BAR BTN CLICKLISTINER
        icon1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                icon1.setBackgroundResource(R.drawable.active_about);
                Intent intent = new Intent(AboutActivity.this, AboutActivity.class);
                startActivity(intent);
            }
        });

        icon2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                icon2.setBackgroundResource(R.drawable.active_guide);
                Intent intent = new Intent(AboutActivity.this, UserGuideActivity.class);
                startActivity(intent);
            }
        });

        icon3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                icon3.setBackgroundResource(R.drawable.active_dev);
                Intent intent = new Intent(AboutActivity.this, DevelopersActivity.class);
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
                capture.setVisibility(View.VISIBLE);
            }
        });

//        HOME AND BACK FUNCTION

        hb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Navigate to HomeActivity
                Intent intent = new Intent(AboutActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });

        bb.setOnClickListener(new View.OnClickListener() {
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
            capture.setVisibility(View.GONE);
        }
    }
}