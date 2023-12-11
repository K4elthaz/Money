package org.pytorch.demo.objectdetection;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import org.json.JSONObject;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class convert_real extends AppCompatActivity {

    private ConstraintLayout navMenu;
    private ImageView icon1, icon2, icon3, hide, sideb, hbtn, bbtn;

    EditText fromInput, toOutput;
    ImageView convertButton;
    Spinner fromDropdown, toDropdown;
    String fromCurrency, toCurrency;

    void setupListeners() {
        fromDropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                fromCurrency = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        toDropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                toCurrency = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        convertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation scaleAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.scale);
                convertButton.startAnimation(scaleAnimation);
                if (!isNetworkConnected()) {
                    Toast.makeText(convert_real.this, "Host unreachable, check your internet connection and try again", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (fromCurrency.equals(toCurrency)) {
                    Toast.makeText(convert_real.this, "from and to values are same.", Toast.LENGTH_SHORT).show();
                    return;
                } else if (fromInput.getText().toString().isEmpty()) {
                    Toast.makeText(convert_real.this, "Please enter a value to convert.", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    JSONFetch obj = new JSONFetch();
                    obj.execute(fromInput.getText().toString());
                }
            }
        });
    }

    void showToast(String message) {
        Toast.makeText(convert_real.this, message, Toast.LENGTH_SHORT).show();
    }

    public boolean isNetworkConnected() {
        // TODO: Check if connected to internet
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_convert_real);

        fromInput = findViewById(R.id.fromCurrency);
        toOutput = findViewById(R.id.toCurrency);
        convertButton = findViewById(R.id.convertButton);
        fromDropdown = findViewById(R.id.fromDropdown);
        toDropdown = findViewById(R.id.toDropdown);
        navMenu = findViewById(R.id.menu_drawer);
        icon1 = findViewById(R.id.imageViewIcon1);
        icon2 = findViewById(R.id.imageViewIcon2);
        icon3 = findViewById(R.id.imageViewIcon3);
        hide = findViewById(R.id.hidemenu);
        sideb = findViewById(R.id.sidemenubar);
        hbtn = findViewById(R.id.homebtnimg);
        bbtn = findViewById(R.id.backbtnimg);
        navMenu.setVisibility(View.GONE);
        setupListeners();
        initializeDefaultSpinner();
        String currencyResult = getIntent().getStringExtra("currencyResult");
        if (currencyResult != null) {
            updateSpinnerBasedOnCurrency(currencyResult);
        }

        // MENU BAR BTN CLICK LISTENERS
        icon1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(convert_real.this, AboutActivity.class);
                startActivity(intent);
            }
        });

        icon2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(convert_real.this, UserGuideActivity.class);
                startActivity(intent);
            }
        });

        icon3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(convert_real.this, DevelopersActivity.class);
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
                convertButton.setVisibility(View.VISIBLE);
            }
        });
        hbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Navigate to HomeActivity
                Intent intent = new Intent(convert_real.this, HomeActivity.class);
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
        fromInput.setText(valueResult);
    }

    public void openNavMenu(View view) {
        if (navMenu.getVisibility() == View.VISIBLE) {
            navMenu.setVisibility(View.GONE);
        } else {
            navMenu.setVisibility(View.VISIBLE);
            sideb.setVisibility(View.GONE);
            hbtn.setVisibility(View.GONE);
            bbtn.setVisibility(View.GONE);
            convertButton.setVisibility(View.GONE);
        }
    }

//    class FromDropdown implements AdapterView.OnItemSelectedListener {
//        @Override
//        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//            fromCurrency = parent.getItemAtPosition(position).toString();
//            initializeDefaultSpinner();
//            String currencyResult = getIntent().getStringExtra("currencyResult");
//            if (currencyResult != null) {
//                updateSpinnerBasedOnCurrency(currencyResult);
//            }
//
//
//            if ("Pesos".equals(currencyResult)) {
//                // Change entries to currency_pesos
//                ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
//                        convert_real.this,
//                        R.array.currency_pesos,
//                        android.R.layout.simple_spinner_item
//                );
//                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//                fromDropdown.setAdapter(adapter);
//            }
//            else if ("Rupees".equals(currencyResult)) {
//                // Change entries to currency_pesos
//                ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
//                        convert_real.this,
//                        R.array.currency_rupees,
//                        android.R.layout.simple_spinner_item
//                );
//                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//                fromDropdown.setAdapter(adapter);
//            }
//            else if ("Won".equals(currencyResult)) {
//                // Change entries to currency_pesos
//                ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
//                        convert_real.this,
//                        R.array.currency_won,
//                        android.R.layout.simple_spinner_item
//                );
//                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//                fromDropdown.setAdapter(adapter);
//            }
//            else if ("Yen".equals(currencyResult)) {
//                // Change entries to currency_pesos
//                ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
//                        convert_real.this,
//                        R.array.currency_yen,
//                        android.R.layout.simple_spinner_item
//                );
//                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//                fromDropdown.setAdapter(adapter);
//            }
//            else if ("Dollars".equals(currencyResult)) {
//                // Change entries to currency_pesos
//                ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
//                        convert_real.this,
//                        R.array.currency_dollars,
//                        android.R.layout.simple_spinner_item
//                );
//                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//                fromDropdown.setAdapter(adapter);
//            }
//            else {
//                // Change entries to currency_options
//                ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
//                        convert_real.this,
//                        R.array.currency_options,
//                        android.R.layout.simple_spinner_item
//                );
//                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//                fromDropdown.setAdapter(adapter);
//            }
//        }
//
//        @Override
//        public void onNothingSelected(AdapterView<?> parent) {
//        }
//    }


    class ToDropdown implements AdapterView.OnItemSelectedListener {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            toCurrency = parent.getItemAtPosition(position).toString();
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {
        }
    }

    //            valuerestxts.setText(currencyResult);
    class JSONFetch extends AsyncTask<String, Void, Void> {
        ProgressDialog dialog = new ProgressDialog(convert_real.this);
        String error = "", apiResponse = "";

        @Override
        protected Void doInBackground(String... params) {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder().url("https://frankfurter.app/latest?amount=" + params[0] + "&from=" + fromCurrency + "&to=" + toCurrency).build();
            try {
                Response response = client.newCall(request).execute();
                apiResponse = response.body().string();
            } catch (Exception e) {
                error = e.toString();
            }
            return null;
        }

        @Override
        protected void onPreExecute() {
            dialog.setMessage("Converting...");
            dialog.show();
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Void v) {
            dialog.dismiss();
            if (!error.isEmpty()) {
                showToast("Something went wrong " + error);
            }
            try {
                toOutput.setText(new JSONObject(apiResponse).getJSONObject("rates").getString(toCurrency));
            } catch (Exception e) {
                showToast("Something went wrong " + e.toString());
            }

            super.onPostExecute(v);
        }
    }
    private void initializeDefaultSpinner() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.currency_options, // Default spinner data
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        fromDropdown.setAdapter(adapter);
    }

    private void updateSpinnerBasedOnCurrency(String currencyResult) {
        ArrayAdapter<CharSequence> adapter;

        switch (currencyResult) {
            case "Pesos":
                adapter = ArrayAdapter.createFromResource(
                        this,
                        R.array.currency_pesos,
                        android.R.layout.simple_spinner_item);
                break;
            case "Rupees":
                adapter = ArrayAdapter.createFromResource(
                        this,
                        R.array.currency_rupees,
                        android.R.layout.simple_spinner_item);
                break;
            case "Won":
                adapter = ArrayAdapter.createFromResource(
                        this,
                        R.array.currency_won,
                        android.R.layout.simple_spinner_item);
                break;
            case "Yen":
                adapter = ArrayAdapter.createFromResource(
                        this,
                        R.array.currency_yen,
                        android.R.layout.simple_spinner_item);
                break;
            case "Dollars":
                adapter = ArrayAdapter.createFromResource(
                        this,
                        R.array.currency_dollars,
                        android.R.layout.simple_spinner_item);
                break;
            default:
                adapter = ArrayAdapter.createFromResource(
                        this,
                        R.array.currency_options,
                        android.R.layout.simple_spinner_item);
        }

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        fromDropdown.setAdapter(adapter);
    }

}