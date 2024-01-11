package org.pytorch.demo.objectdetection;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.text.method.LinkMovementMethod;
import android.util.Log;
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
                String url = "https://banknoteinfo.net/security-features/";
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(intent);
            }
        });

        linkTextView.setPaintFlags(linkTextView.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        linkTextView.setMovementMethod(LinkMovementMethod.getInstance());

        icon1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(ScanResultActivity.this, AboutActivity.class);
//                startActivity(intent);

                icon1.setBackgroundResource(R.drawable.active_about);

                // Delayed task to reset the background after a few seconds
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // Reset the background resource after the delay
                        icon1.setBackgroundResource(R.drawable.about1);
                    }
                }, 2000); // 2000 milliseconds (adjust the time as needed)

                // Start the new activity immediately
                Intent i = new Intent(ScanResultActivity.this, AboutActivity.class);
                startActivity(i);
            }
        });

        icon2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(ScanResultActivity.this, UserGuideActivity.class);
//                startActivity(intent);

                icon2.setBackgroundResource(R.drawable.active_guide);

                // Delayed task to reset the background after a few seconds
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // Reset the background resource after the delay
                        icon2.setBackgroundResource(R.drawable.userguide1);
                    }
                }, 2000); // 2000 milliseconds (adjust the time as needed)

                // Start the new activity immediately
                Intent i = new Intent(ScanResultActivity.this, UserGuideActivity.class);
                startActivity(i);
            }
        });

        icon3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(ScanResultActivity.this, DevelopersActivity.class);
//                startActivity(intent);

                icon3.setBackgroundResource(R.drawable.active_dev);

                // Delayed task to reset the background after a few seconds
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // Reset the background resource after the delay
                        icon3.setBackgroundResource(R.drawable.developers1);
                    }
                }, 2000); // 2000 milliseconds (adjust the time as needed)

                // Start the new activity immediately
                Intent i = new Intent(ScanResultActivity.this, DevelopersActivity.class);
                startActivity(i);
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
                // Get the value from currecnyresult TextView
                String currencyResult = currecnyresult.getText().toString();

                // Create an intent to start ConvertCurrencyActivity
                Intent intent = new Intent(ScanResultActivity.this, convert_real.class);

                // Put the value as an extra in the intent
                intent.putExtra("valueResult", value);
//
                intent.putExtra("currencyResult", currencyResult);

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
        Log.d("Debug", "Content: " + content);
        classresult.setText(content);
        String cleanedContent = content.replaceAll("[^a-zA-Z ]", "").trim();
        ImageView wrongIcon = findViewById(R.id.rightorwrongicon);
        Log.d("Debug", "Cleaned Content: " + cleanedContent);

        if (cleanedContent.equalsIgnoreCase("Genuine two hundred peso")) {
            currecnyresult.setText("Peso");
            valueresult.setText("200");
            wrongIcon.setImageResource(R.drawable.right1);
        } else if (cleanedContent.equalsIgnoreCase("Genuine two dollars")) {
            currecnyresult.setText("Dollar");
            valueresult.setText("2");
            wrongIcon.setImageResource(R.drawable.right1);
        } else if (cleanedContent.equalsIgnoreCase("Genuine twenty peso")) {
            currecnyresult.setText("Peso");
            valueresult.setText("20");
            wrongIcon.setImageResource(R.drawable.right1);
        } else if (cleanedContent.equalsIgnoreCase("Genuine twenty dollars")) {
            currecnyresult.setText("Dollar");
            valueresult.setText("20");
            wrongIcon.setImageResource(R.drawable.right1);
        } else if (cleanedContent.equalsIgnoreCase("Genuine ten thousand yen")) {
            currecnyresult.setText("Yen");
            valueresult.setText("20000");
            wrongIcon.setImageResource(R.drawable.right1);
        } else if (cleanedContent.equalsIgnoreCase("Genuine ten thousand won")) {
            currecnyresult.setText("Won");
            valueresult.setText("10000");
            wrongIcon.setImageResource(R.drawable.right1);
        } else if (cleanedContent.equalsIgnoreCase("Genuine ten dollars")) {
            currecnyresult.setText("Dollar");
            valueresult.setText("10");
            wrongIcon.setImageResource(R.drawable.right1);
        } else if (cleanedContent.equalsIgnoreCase("Genuine one thousand yen")) {
            currecnyresult.setText("Yen");
            valueresult.setText("1000");
            wrongIcon.setImageResource(R.drawable.right1);
        } else if (cleanedContent.equalsIgnoreCase("Genuine one thousand won")) {
            currecnyresult.setText("Won");
            valueresult.setText("1000");
            wrongIcon.setImageResource(R.drawable.right1);
        } else if (cleanedContent.equalsIgnoreCase("Genuine one thousand taiwan dollar")) {
            currecnyresult.setText("Dollar");
            valueresult.setText("1000");
            wrongIcon.setImageResource(R.drawable.right1);
        } else if (cleanedContent.equalsIgnoreCase("Genuine one thousand peso")) {
            currecnyresult.setText("Peso");
            valueresult.setText("1000");
            wrongIcon.setImageResource(R.drawable.right1);
        } else if (cleanedContent.equalsIgnoreCase("Genuine one hundred taiwan dollar")) {
            currecnyresult.setText("Dollar");
            valueresult.setText("100");
            wrongIcon.setImageResource(R.drawable.right1);
        } else if (cleanedContent.equalsIgnoreCase("Genuine one hundred peso")) {
            currecnyresult.setText("Peso");
            valueresult.setText("100");
            wrongIcon.setImageResource(R.drawable.right1);
        } else if (cleanedContent.equalsIgnoreCase("Genuine one hundred dollars")) {
            currecnyresult.setText("Dollar");
            valueresult.setText("100");
            wrongIcon.setImageResource(R.drawable.right1);
        } else if (cleanedContent.equalsIgnoreCase("Genuine one dollar")) {
            currecnyresult.setText("Dollar");
            valueresult.setText("1");
            wrongIcon.setImageResource(R.drawable.right1);
        } else if (cleanedContent.equalsIgnoreCase("Genuine five thousand yen")) {
            currecnyresult.setText("Yen");
            valueresult.setText("5000");
            wrongIcon.setImageResource(R.drawable.right1);
        } else if (cleanedContent.equalsIgnoreCase("Genuine five thousand won")) {
            currecnyresult.setText("Won");
            valueresult.setText("5000");
            wrongIcon.setImageResource(R.drawable.right1);
        } else if (cleanedContent.equalsIgnoreCase("Genuine five hundred taiwan dollar")) {
            currecnyresult.setText("Dollar");
            valueresult.setText("500");
            wrongIcon.setImageResource(R.drawable.right1);
        } else if (cleanedContent.equalsIgnoreCase("Genuine five hundred peso")) {
            currecnyresult.setText("Peso");
            valueresult.setText("500");
            wrongIcon.setImageResource(R.drawable.right1);
        } else if (cleanedContent.equalsIgnoreCase("Genuine one thousand yen")) {
            currecnyresult.setText("Yen");
            valueresult.setText("1000");
            wrongIcon.setImageResource(R.drawable.right1);
        } else if (cleanedContent.equalsIgnoreCase("Genuine five dollars")) {
            currecnyresult.setText("Dollar");
            valueresult.setText("5");
            wrongIcon.setImageResource(R.drawable.right1);
        } else if (cleanedContent.equalsIgnoreCase("Genuine fifty thousand won")) {
            currecnyresult.setText("Won");
            valueresult.setText("50000");
            wrongIcon.setImageResource(R.drawable.right1);
        } else if (cleanedContent.equalsIgnoreCase("Genuine fifty peso")) {
            currecnyresult.setText("Peso");
            valueresult.setText("50");
            wrongIcon.setImageResource(R.drawable.right1);
        } else if (cleanedContent.equalsIgnoreCase("Genuine fifty dollars")) {
            currecnyresult.setText("Dollar");
            valueresult.setText("50");
            wrongIcon.setImageResource(R.drawable.right1);
//        } else if (cleanedContent.equalsIgnoreCase("Genuine twenty dollars back")) {
//            currecnyresult.setText("Dollars");
//            valueresult.setText("20");
//            wrongIcon.setImageResource(R.drawable.right1);
//        } else if (cleanedContent.equalsIgnoreCase("Genuine twenty dollars front")) {
//            currecnyresult.setText("Dollars");
//            valueresult.setText("20");
//            wrongIcon.setImageResource(R.drawable.right1);
//        } else if (cleanedContent.equalsIgnoreCase("Genuine 20 Peso")) {
//            currecnyresult.setText("Pesos");
//            valueresult.setText("20");
//            wrongIcon.setImageResource(R.drawable.right1);
//        } else if (cleanedContent.equalsIgnoreCase("Genuine 200 Peso")) {
//            currecnyresult.setText("Pesos");
//            valueresult.setText("200");
//            wrongIcon.setImageResource(R.drawable.right1);
//        } else if (cleanedContent.equalsIgnoreCase("Genuine two thousand yen")) {
//            currecnyresult.setText("Yen");
//            valueresult.setText("2000");
//            wrongIcon.setImageResource(R.drawable.right1);
        } else {
//             Handle other cases or provide a default value
            currecnyresult.setText("Please scan it again \n It can be Counterfeit");
            valueresult.setText("Counterfeit");
            wrongIcon.setImageResource(R.drawable.wrong1);
            TextView linkTextView = findViewById(R.id.link);
            linkTextView.setText("Why it is a Counterfeit?");
            linkTextView.setTextColor(Color.BLUE);
            linkTextView.setClickable(true);
        }
    }

}
