package org.pytorch.demo.objectdetection;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

public class Capture extends AppCompatActivity {

    private ConstraintLayout navMenu;

    private ImageView icon1,icon2,icon3,hide,sideb,hb,bb,frnNT,bckNT;
    private TextView txt1,txt2;
    private Button dBtn, cBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_capture);

//        MENU BTN
        navMenu = findViewById(R.id.menu_drawer);
        icon1 = findViewById(R.id.imageViewIcon1);
        icon2 = findViewById(R.id.imageViewIcon2);
        icon3 = findViewById(R.id.imageViewIcon3);
        hide = findViewById(R.id.hidemenu);

        sideb = findViewById(R.id.sidemenubar);
        hb = findViewById(R.id.homebtnimg);
        bb = findViewById(R.id.backbtnimg);

//        DETECT BTN
        dBtn = findViewById(R.id.detectButton);
//        Capture BTN
        cBtn = findViewById(R.id.captureButton);


//        IMG VIEW NUNG FRONT
        frnNT = findViewById(R.id.frntbankNote);
        txt1 = findViewById(R.id.textView3);

//        IMG VIEW NUNG BACK
        bckNT = findViewById(R.id.backbankNote);
        txt2 = findViewById(R.id.textView4);


//        HIDE THE MENU
        navMenu.setVisibility(View.GONE);

        //        MENU BAR BTN CLICKLISTINER
        icon1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Capture.this, AboutActivity.class);
                startActivity(intent);
            }
        });

        icon2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Capture.this, UserGuideActivity.class);
                startActivity(intent);
            }
        });

        icon3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Capture.this, DevelopersActivity.class);
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
                cBtn.setVisibility(View.VISIBLE);
                dBtn.setVisibility(View.VISIBLE);
                frnNT.setVisibility(View.VISIBLE);
                bckNT.setVisibility(View.VISIBLE);
                txt1.setVisibility(View.VISIBLE);
                txt2.setVisibility(View.VISIBLE);
            }
        });

        //        HOME AND BACK FUNCTION
        hb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Navigate to HomeActivity
                Intent intent = new Intent(Capture.this, HomeActivity.class);
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
//        END NG HOME AND BACK FUNCTION


    }
//HIDE AND VISIBLE FUNCTION NG NAV MENU
    public void openNavMenu(View view) {
        if (navMenu.getVisibility() == View.VISIBLE) {
            navMenu.setVisibility(View.GONE);
        } else {
            navMenu.setVisibility(View.VISIBLE);
            sideb.setVisibility(View.GONE);
            hb.setVisibility(View.GONE);
            bb.setVisibility(View.GONE);
            cBtn.setVisibility(View.GONE);
            dBtn.setVisibility(View.GONE);
            frnNT.setVisibility(View.GONE);
            bckNT.setVisibility(View.GONE);
            txt1.setVisibility(View.GONE);
            txt2.setVisibility(View.GONE);
        }
    }



}