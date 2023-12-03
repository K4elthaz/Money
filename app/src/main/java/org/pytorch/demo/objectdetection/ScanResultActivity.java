package org.pytorch.demo.objectdetection;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ScanResultActivity extends AppCompatActivity {

    private ConstraintLayout navMenu;

    private ImageView icon1, icon2, icon3, hide, sideb, hb, bb, rightwrong, box, convertCurrencyBtn;

    private TextView txt1, txt2, txt3, txt4, classresult, currecnyresult, valueresult, link;

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
        TextView linkTextView = findViewById(R.id.link);

        linkTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://example.com";
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(intent);
            }
        });

        linkTextView.setPaintFlags(linkTextView.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        linkTextView.setMovementMethod(LinkMovementMethod.getInstance());

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
                Intent intent = new Intent(ScanResultActivity.this, convert_real.class);

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
        String cleanedContent = content.replaceAll("[^a-zA-Z ]", "").trim();

        if (cleanedContent.equalsIgnoreCase("Fifty Rupees")) {
            currecnyresult.setText("Rupees");
            valueresult.setText("50");
        } else if (cleanedContent.equalsIgnoreCase("FiftyRupees")) {
            currecnyresult.setText("Rupees");
            valueresult.setText("50");
        } else if (cleanedContent.equalsIgnoreCase("Five Hundred Rupees")) {
            currecnyresult.setText("Rupees");
            valueresult.setText("500");
        } else if (cleanedContent.equalsIgnoreCase("One Hundred Rupees")) {
            currecnyresult.setText("Rupees");
            valueresult.setText("100");
        } else if (cleanedContent.equalsIgnoreCase("Ten Rupees")) {
            currecnyresult.setText("Rupees");
            valueresult.setText("10");
        } else if (cleanedContent.equalsIgnoreCase("Twenty Rupees")) {
            currecnyresult.setText("Rupees");
            valueresult.setText("20");
        } else if (cleanedContent.equalsIgnoreCase("Two Hundred Rupees")) {
            currecnyresult.setText("Rupees");
            valueresult.setText("200");
        } else if (cleanedContent.equalsIgnoreCase("Two Thousand Rupees")) {
            currecnyresult.setText("Rupees");
            valueresult.setText("2000");
        } else if (cleanedContent.equalsIgnoreCase("fifty dollars back")) {
            currecnyresult.setText("Dollars");
            valueresult.setText("50");
        } else if (cleanedContent.equalsIgnoreCase("fifty dollars front")) {
            currecnyresult.setText("Dollar");
            valueresult.setText("50");
        } else if (cleanedContent.equalsIgnoreCase("fifty pesos")) {
            currecnyresult.setText("Pesos");
            valueresult.setText("50");
        } else if (cleanedContent.equalsIgnoreCase("fifty thousand won")) {
            currecnyresult.setText("Won");
            valueresult.setText("50000");
        } else if (cleanedContent.equalsIgnoreCase("five dollars back")) {
            currecnyresult.setText("Dollar");
            valueresult.setText("5");
        } else if (cleanedContent.equalsIgnoreCase("five dollars front")) {
            currecnyresult.setText("Dollar");
            valueresult.setText("5");
        } else if (cleanedContent.equalsIgnoreCase("five hundred pesos")) {
            currecnyresult.setText("Pesos");
            valueresult.setText("500");
        } else if (cleanedContent.equalsIgnoreCase("five thousand won")) {
            currecnyresult.setText("Won");
            valueresult.setText("5000");
        } else if (cleanedContent.equalsIgnoreCase("five thousand yen")) {
            currecnyresult.setText("Yen");
            valueresult.setText("5000");
        } else if (cleanedContent.equalsIgnoreCase("one dollar back")) {
            currecnyresult.setText("Dollar");
            valueresult.setText("1");
        } else if (cleanedContent.equalsIgnoreCase("one dollar front")) {
            currecnyresult.setText("Dollar");
            valueresult.setText("1");
        } else if (cleanedContent.equalsIgnoreCase("one hundred pesos")) {
            currecnyresult.setText("Pesos");
            valueresult.setText("100");
        } else if (cleanedContent.equalsIgnoreCase("one thousand pesos")) {
            currecnyresult.setText("Pesos");
            valueresult.setText("1000");
        } else if (cleanedContent.equalsIgnoreCase("one thousand won")) {
            currecnyresult.setText("Won");
            valueresult.setText("1000");
        } else if (cleanedContent.equalsIgnoreCase("one thousand yen")) {
            currecnyresult.setText("Yen");
            valueresult.setText("1000");
        } else if (cleanedContent.equalsIgnoreCase("ten dollars back")) {
            currecnyresult.setText("Dollars");
            valueresult.setText("10");
        } else if (cleanedContent.equalsIgnoreCase("ten dollars front")) {
            currecnyresult.setText("Dollar");
            valueresult.setText("10");
        } else if (cleanedContent.equalsIgnoreCase("ten thousand won")) {
            currecnyresult.setText("Won");
            valueresult.setText("10000");
        } else if (cleanedContent.equalsIgnoreCase("ten thousand yen")) {
            currecnyresult.setText("Yen");
            valueresult.setText("10000");
        } else if (cleanedContent.equalsIgnoreCase("twenty dollars back")) {
            currecnyresult.setText("Dollars");
            valueresult.setText("20");
        } else if (cleanedContent.equalsIgnoreCase("twenty dollars front")) {
            currecnyresult.setText("Dollars");
            valueresult.setText("20");
        } else if (cleanedContent.equalsIgnoreCase("twenty pesos")) {
            currecnyresult.setText("Pesos");
            valueresult.setText("20");
        } else if (cleanedContent.equalsIgnoreCase("two hundred pesos")) {
            currecnyresult.setText("Pesos");
            valueresult.setText("200");
        } else if (cleanedContent.equalsIgnoreCase("two thousand yen")) {
            currecnyresult.setText("Yen");
            valueresult.setText("2000");
//        } else if (cleanedContent.equalsIgnoreCase("fake fifty")) {
//            currecnyresult.setText("Counterfeit");
//            valueresult.setText("Counterfeit");
//            ImageView wrongIcon = findViewById(R.id.rightorwrongicon);
//            wrongIcon.setImageResource(R.drawable.wrong1);
//            TextView linkTextView = findViewById(R.id.link);
//            linkTextView.setText("Why it is a Counterfeit?");
//            linkTextView.setTextColor(Color.BLUE);
//            linkTextView.setClickable(true);
//        } else if (cleanedContent.equalsIgnoreCase("fake twenty")) {
//            currecnyresult.setText("Counterfeit");
//            valueresult.setText("Counterfeit");
//            ImageView wrongIcon = findViewById(R.id.rightorwrongicon);
//            wrongIcon.setImageResource(R.drawable.wrong1);
//            TextView linkTextView = findViewById(R.id.link);
//            linkTextView.setText("Why it is a Counterfeit?");
//            linkTextView.setTextColor(Color.BLUE);
//            linkTextView.setClickable(true);
//        } else if (cleanedContent.equalsIgnoreCase("fake one hundred")) {
//            currecnyresult.setText("Counterfeit");
//            valueresult.setText("Counterfeit");
//            ImageView wrongIcon = findViewById(R.id.rightorwrongicon);
//            wrongIcon.setImageResource(R.drawable.wrong1);
//            TextView linkTextView = findViewById(R.id.link);
//            linkTextView.setText("Why it is a Counterfeit?");
//            linkTextView.setTextColor(Color.BLUE);
//            linkTextView.setClickable(true);
//        } else if (cleanedContent.equalsIgnoreCase("fake five hundred")) {
//            currecnyresult.setText("Counterfeit");
//            valueresult.setText("Counterfeit");
//            ImageView wrongIcon = findViewById(R.id.rightorwrongicon);
//            wrongIcon.setImageResource(R.drawable.wrong1);
//            TextView linkTextView = findViewById(R.id.link);
//            linkTextView.setText("Why it is a Counterfeit?");
//            linkTextView.setTextColor(Color.BLUE);
//            linkTextView.setClickable(true);
//        } else if (cleanedContent.equalsIgnoreCase("fake two hundred")) {
//            currecnyresult.setText("Counterfeit");
//            valueresult.setText("Counterfeit");
//            ImageView wrongIcon = findViewById(R.id.rightorwrongicon);
//            wrongIcon.setImageResource(R.drawable.wrong1);
//            TextView linkTextView = findViewById(R.id.link);
//            linkTextView.setText("Why it is a Counterfeit?");
//            linkTextView.setTextColor(Color.BLUE);
//            linkTextView.setClickable(true);
//        } else if (cleanedContent.equalsIgnoreCase("fake one thousand")) {
//            currecnyresult.setText("Counterfeit");
//            valueresult.setText("Counterfeit");
//            ImageView wrongIcon = findViewById(R.id.rightorwrongicon);
//            wrongIcon.setImageResource(R.drawable.wrong1);
//            TextView linkTextView = findViewById(R.id.link);
//            linkTextView.setText("Why it is a Counterfeit?");
//            linkTextView.setTextColor(Color.BLUE);
//            linkTextView.setClickable(true);
        } else {
            // Handle other cases or provide a default value
            currecnyresult.setText("Please scan it again \n It can be Counterfeit");
            valueresult.setText("Counterfeit");
            ImageView wrongIcon = findViewById(R.id.rightorwrongicon);
            wrongIcon.setImageResource(R.drawable.wrong1);
        }
    }
}
