package org.pytorch.demo.objectdetection;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class ConvertCurrencyActivity extends AppCompatActivity {

    private ConstraintLayout navMenu;

    private ImageView icon1, icon2, icon3, hide, sideb, hbtn, bbtn;

    private TextView txt1;

    private Spinner fromCurrencySpinner;
    private Spinner toCurrencySpinner;
    private EditText amountEditText;
    private Button convertButton;
    private EditText resultEditText;

    // Exchange rates
    private static final double USD_TO_PHP_RATE = 55.40;
    private static final double PHP_TO_USD_RATE = 1 / USD_TO_PHP_RATE;
    private static final double EUR_TO_USD_RATE = 1.09; // Example rate
    private static final double USD_TO_EUR_RATE = 1 / EUR_TO_USD_RATE;
    private static final double PHP_TO_EUR_RATE = PHP_TO_USD_RATE * USD_TO_EUR_RATE;
    private static final double EUR_TO_PHP_RATE = 1 / PHP_TO_EUR_RATE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_convert_currency);

        // Initialize UI components
        navMenu = findViewById(R.id.menu_drawer);
        icon1 = findViewById(R.id.imageViewIcon1);
        icon2 = findViewById(R.id.imageViewIcon2);
        icon3 = findViewById(R.id.imageViewIcon3);
        hide = findViewById(R.id.hidemenu);
        sideb = findViewById(R.id.sidemenubar);
        hbtn = findViewById(R.id.homebtnimg);
        bbtn = findViewById(R.id.backbtnimg);
        txt1 = findViewById(R.id.currencyTxt);
        fromCurrencySpinner = findViewById(R.id.fromCurrencySpinner);
        toCurrencySpinner = findViewById(R.id.toCurrencySpinner);
        amountEditText = findViewById(R.id.amountEditText);
        resultEditText = findViewById(R.id.resultEditText);
        convertButton = findViewById(R.id.convertBtn);

        // Populate spinners with currency options
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.currencies_array, R.layout.spinner_item);
        adapter.setDropDownViewResource(R.layout.spinner_item);
        fromCurrencySpinner.setAdapter(adapter);
        toCurrencySpinner.setAdapter(adapter);

        convertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                convertCurrency();
            }
        });
        navMenu.setVisibility(View.GONE);

        // MENU BAR BTN CLICK LISTENERS
        icon1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(ConvertCurrencyActivity.this, AboutActivity.class);
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
                Intent i = new Intent(ConvertCurrencyActivity.this, AboutActivity.class);
                startActivity(i);
            }
        });

        icon2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(ConvertCurrencyActivity.this, UserGuideActivity.class);
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
                Intent i = new Intent(ConvertCurrencyActivity.this, UserGuideActivity.class);
                startActivity(i);
            }
        });

        icon3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(ConvertCurrencyActivity.this, DevelopersActivity.class);
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
                Intent i = new Intent(ConvertCurrencyActivity.this, DevelopersActivity.class);
                startActivity(i);
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
                convertButton.setVisibility(View.VISIBLE);
            }
        });

        // HOME AND BACK FUNCTION
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

        // Retrieve the value passed from ScanResultActivity
        String valueResult = getIntent().getStringExtra("valueResult");

        // Set the received value in the amountEditText
        amountEditText.setText(valueResult);
    }

    public void openNavMenu(View view) {
        if (navMenu.getVisibility() == View.VISIBLE) {
            navMenu.setVisibility(View.GONE);
        } else {
            navMenu.setVisibility(View.VISIBLE);
            sideb.setVisibility(View.GONE);
            hbtn.setVisibility(View.GONE);
            bbtn.setVisibility(View.GONE);
            txt1.setVisibility(View.GONE);
            convertButton.setVisibility(View.GONE);
        }
    }

    private void convertCurrency() {
        String fromCurrency = fromCurrencySpinner.getSelectedItem().toString();
        String toCurrency = toCurrencySpinner.getSelectedItem().toString();
        String amountStr = amountEditText.getText().toString();

        if (amountStr.isEmpty()) {
            Toast.makeText(this, "Please enter an amount.", Toast.LENGTH_SHORT).show();
            return;
        }

        double amount = Double.parseDouble(amountStr);
        double convertedAmount = convertAmount(fromCurrency, toCurrency, amount);

        resultEditText.setText(String.format("%.2f", convertedAmount));
    }

    private double convertAmount(String fromCurrency, String toCurrency, double amount) {
        // Conversion logic
        if (fromCurrency.equals("PHP") && toCurrency.equals("USD")) {
            return amount * PHP_TO_USD_RATE;
        } else if (fromCurrency.equals("USD") && toCurrency.equals("PHP")) {
            return amount * USD_TO_PHP_RATE;
        } else if (fromCurrency.equals("USD") && toCurrency.equals("EUR")) {
            return amount * USD_TO_EUR_RATE;
        } else if (fromCurrency.equals("EUR") && toCurrency.equals("USD")) {
            return amount * EUR_TO_USD_RATE;
        } else if (fromCurrency.equals("PHP") && toCurrency.equals("EUR")) {
            return amount * PHP_TO_EUR_RATE;
        } else if (fromCurrency.equals("EUR") && toCurrency.equals("PHP")) {
            return amount * EUR_TO_PHP_RATE;
        }
        // Add more conditions for other currency pairs if needed
        return 0;
    }
}
