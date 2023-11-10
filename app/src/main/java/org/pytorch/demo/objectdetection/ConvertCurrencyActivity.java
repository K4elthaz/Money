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

public class ConvertCurrencyActivity extends AppCompatActivity {

    private ConstraintLayout navMenu;

    private ImageView icon1,icon2,icon3,hide,sideb,hbtn,bbtn, conbtn;

    private TextView txt1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_convert_currency);

        navMenu = findViewById(R.id.menu_drawer);
        icon1 = findViewById(R.id.imageViewIcon1);
        icon2 = findViewById(R.id.imageViewIcon2);
        icon3 = findViewById(R.id.imageViewIcon3);
        hide = findViewById(R.id.hidemenu);
        sideb = findViewById(R.id.sidemenubar);

//        DI NUNG Home and Back BTN
        hbtn = findViewById(R.id.homebtnimg);
        bbtn = findViewById(R.id.backbtnimg);
// Titlte text ID
        txt1 = findViewById(R.id.currencyTxt);

// Eto yung ID nung sa Convert BTN
        conbtn = findViewById(R.id.convertBtn);


        navMenu.setVisibility(View.GONE);

        //        MENU BAR BTN CLICKLISTINER
        icon1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ConvertCurrencyActivity.this, AboutActivity.class);
                startActivity(intent);
            }
        });

        icon2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ConvertCurrencyActivity.this, UserGuideActivity.class);
                startActivity(intent);
            }
        });

        icon3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ConvertCurrencyActivity.this, DevelopersActivity.class);
                startActivity(intent);
            }
        });

        hide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navMenu.setVisibility(View.GONE);
                sideb.setVisibility(View.VISIBLE);
                hbtn.setVisibility(View.VISIBLE);
                bbtn.setVisibility(View.VISIBLE);
                txt1.setVisibility(View.VISIBLE);
                conbtn.setVisibility(View.VISIBLE);
            }
        });

//        HOME AND BACK FUNTION
        hbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Navigate to HomeActivity
                Intent intent = new Intent(ConvertCurrencyActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });

        bbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Go back to the previous activity (assuming it's HomeActivity)
                onBackPressed();
            }
        });
//        END NG BACK AND HOME FUNCTION


    }


//HIDE AND VISIBLE NG NAV MENU BAR
    public void openNavMenu(View view) {
        if (navMenu.getVisibility() == View.VISIBLE) {
            navMenu.setVisibility(View.GONE);
        } else {
            navMenu.setVisibility(View.VISIBLE);
            sideb.setVisibility(View.GONE);
            hbtn.setVisibility(View.GONE);
            bbtn.setVisibility(View.GONE);
            txt1.setVisibility(View.GONE);
            conbtn.setVisibility(View.GONE);
        }
    }
}