package org.pytorch.demo.objectdetection;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ScanResultActivity extends AppCompatActivity {

    private ConstraintLayout navMenu;

    private ImageView icon1, icon2, icon3, hide, sideb, hb, bb, rightwrong, box, convertCurrencyBtn;

    private TextView txt1, txt2, txt3, txt4, classresult, currecnyresult, valueresult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_result);

        navMenu = findViewById(R.id.menu_drawer);
        icon1 = findViewById(R.id.imageViewIcon1);
        icon2 = findViewById(R.id.imageViewIcon2);
        icon3 = findViewById(R.id.imageViewIcon3);
        hide = findViewById(R.id.hidemenu);
        sideb = findViewById(R.id.sidemenubar);
        box = findViewById(R.id.boxed);
        hb = findViewById(R.id.homebtnimg);
        bb = findViewById(R.id.backbtnimg);
        txt1 = findViewById(R.id.textview14);
        txt2 = findViewById(R.id.textview15);
        txt3 = findViewById(R.id.textview16);
        txt4 = findViewById(R.id.textview12);
        convertCurrencyBtn = findViewById(R.id.finalConBtn);
        classresult = findViewById(R.id.classificationResult);
        currecnyresult = findViewById(R.id.currencyResult);
        valueresult = findViewById(R.id.valueResult);
        rightwrong = findViewById(R.id.rightorwrongicon);
        navMenu.setVisibility(View.GONE);

        icon1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ScanResultActivity.this, AboutActivity.class);
                startActivity(intent);
            }
        });

        icon2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ScanResultActivity.this, UserGuideActivity.class);
                startActivity(intent);
            }
        });

        icon3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ScanResultActivity.this, DevelopersActivity.class);
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
                convertCurrencyBtn.setVisibility(View.VISIBLE);
                classresult.setVisibility(View.VISIBLE);
                currecnyresult.setVisibility(View.VISIBLE);
                valueresult.setVisibility(View.VISIBLE);
                txt1.setVisibility(View.VISIBLE);
                txt2.setVisibility(View.VISIBLE);
                txt3.setVisibility(View.VISIBLE);
                txt4.setVisibility(View.VISIBLE);
                box.setVisibility(View.VISIBLE);
                rightwrong.setVisibility(View.VISIBLE);
            }
        });

        hb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Navigate to HomeActivity
                Intent intent = new Intent(ScanResultActivity.this, HomeActivity.class);
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

        convertCurrencyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get the value from valueresult TextView
                String value = valueresult.getText().toString();

                // Create an intent to start ConvertCurrencyActivity
                Intent intent = new Intent(ScanResultActivity.this, ConvertCurrencyActivity.class);

                // Put the value as an extra in the intent
                intent.putExtra("valueResult", value);

                // Start the ConvertCurrencyActivity
                startActivity(intent);
            }
        });


        // Receive content from ActivityManual and update the TextView
        String textViewContent = getIntent().getStringExtra("textViewContent");
        if (textViewContent != null) {
            updateTextViewContent(textViewContent);
        }
    }

    public void openNavMenu(View view) {
        if (navMenu.getVisibility() == View.VISIBLE) {
            navMenu.setVisibility(View.GONE);
        } else {
            navMenu.setVisibility(View.VISIBLE);
            sideb.setVisibility(View.GONE);
            hb.setVisibility(View.GONE);
            bb.setVisibility(View.GONE);
            convertCurrencyBtn.setVisibility(View.GONE);
            classresult.setVisibility(View.GONE);
            currecnyresult.setVisibility(View.GONE);
            valueresult.setVisibility(View.GONE);
            txt1.setVisibility(View.GONE);
            txt2.setVisibility(View.GONE);
            txt3.setVisibility(View.GONE);
            txt4.setVisibility(View.GONE);
            box.setVisibility(View.GONE);
            rightwrong.setVisibility(View.GONE);
        }
    }

    // Update the content of the classificationResult TextView
    private void updateTextViewContent(String content) {
        classresult.setText(content);

        // Remove the percentage and trim any leading/trailing whitespace
        String cleanedContent = content.replaceAll("[^a-zA-Z ]", "").trim();

        // Add your validation logic here
        if (cleanedContent.equalsIgnoreCase("real fifty")) {
            currecnyresult.setText("Pesos");
            valueresult.setText("50");
        } else if (cleanedContent.equalsIgnoreCase("real five hundred")) {
            currecnyresult.setText("Pesos");
            valueresult.setText("500");
        } else if (cleanedContent.equalsIgnoreCase("real one hundred")) {
            currecnyresult.setText("Pesos");
            valueresult.setText("100");
        } else if (cleanedContent.equalsIgnoreCase("real one thousand")) {
            currecnyresult.setText("Pesos");
            valueresult.setText("1000");
        } else if (cleanedContent.equalsIgnoreCase("real twenty")) {
            currecnyresult.setText("Pesos");
            valueresult.setText("20");
        } else if (cleanedContent.equalsIgnoreCase("real two hundred")) {
            currecnyresult.setText("Pesos");
            valueresult.setText("200");
        } else {
            // Handle other cases or provide a default value
            currecnyresult.setText("Unknown Currency");
            valueresult.setText("N/A");
        }
    }
}
