package org.pytorch.demo.objectdetection;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class HomeActivity extends AppCompatActivity {

    private static final int CAMERA_REQUEST_CODE = 123;

    private ConstraintLayout navMenu;

    private ImageView icon1,icon2,icon3,hide,sideb,capb,upb,conb,live;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        navMenu = findViewById(R.id.menu_drawer);
        icon1 = findViewById(R.id.imageViewIcon1);
        icon2 = findViewById(R.id.imageViewIcon2);
        icon3 = findViewById(R.id.imageViewIcon3);
        hide = findViewById(R.id.hidemenu);
        sideb = findViewById(R.id.sidemenubar);
        capb = findViewById(R.id.captureImgBtn);
        upb = findViewById(R.id.uploadImgBtn);
        conb = findViewById(R.id.convertImgBtn);
        navMenu.setVisibility(View.GONE);
        live = findViewById(R.id.live);

//        MENU BAR BTN CLICKLISTINER
        icon1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, AboutActivity.class);
                startActivity(intent);
            }
        });

        icon2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, UserGuideActivity.class);
                startActivity(intent);
            }
        });

        icon3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, DevelopersActivity.class);
                startActivity(intent);
            }
        });

        hide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navMenu.setVisibility(View.GONE);
                sideb.setVisibility(View.VISIBLE);
                capb.setVisibility(View.VISIBLE);
                upb.setVisibility(View.VISIBLE);
                conb.setVisibility(View.VISIBLE);
            }
        });


// CONVERT BTN
        ImageView convertButton = findViewById(R.id.convertImgBtn);

        convertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Animation scaleAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.scale);
                convertButton.startAnimation(scaleAnimation);
                Intent i = new Intent(HomeActivity.this, convert_real.class);
                startActivity(i);
            }
        });




        live.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation scaleAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.scale);
                live.startAnimation(scaleAnimation);
                Intent i = new Intent(HomeActivity.this, MainActivity2.class);
                startActivity(i);
            }
        });

//        UPLOAD BTN
        ImageView uploadBtn = findViewById(R.id.uploadImgBtn);
        uploadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Animation scaleAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.scale);
                uploadBtn.startAnimation(scaleAnimation);
                Intent i = new Intent(HomeActivity.this, ActivityManual.class);
                startActivity(i);
            }
        });
//         Diko alam kung ano to pero pagka meron kasi nito naka comment di ko marun yung activity manual pag pinindot yung upload btn


        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        ImageView cameraButton = findViewById(R.id.captureImgBtn);
        cameraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation scaleAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.scale);
                cameraButton.startAnimation(scaleAnimation);
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                if (cameraIntent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(cameraIntent, CAMERA_REQUEST_CODE);
                }
            }
        });
    }

// NAV VISIBLE AND GONE

    public void openNavMenu(View view) {
        if (navMenu.getVisibility() == View.VISIBLE) {
            navMenu.setVisibility(View.GONE);
        } else {
            navMenu.setVisibility(View.VISIBLE);
            sideb.setVisibility(View.GONE);
            capb.setVisibility(View.GONE);
            upb.setVisibility(View.GONE);
            conb.setVisibility(View.GONE);
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CAMERA_REQUEST_CODE && resultCode == RESULT_OK) {
            Bitmap imageBitmap = (Bitmap) data.getExtras().get("data");
            if (imageBitmap != null) {
                Intent intent = new Intent(HomeActivity.this, Capture.class);
                intent.putExtra("captured_image", imageBitmap);
                startActivity(intent);
            }
        }
    }



}
