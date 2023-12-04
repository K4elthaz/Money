package org.pytorch.demo.objectdetection;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class FirstPage extends AppCompatActivity {

    public ImageView homebtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Check if the app is launched for the first time
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        boolean isFirstTime = prefs.getBoolean("isFirstTime", true);

        if (isFirstTime) {
            // The app is launched for the first time
            SharedPreferences.Editor editor = prefs.edit();
            editor.putBoolean("isFirstTime", false);
            editor.apply();

            setContentView(R.layout.activity_first_page);

            homebtn = findViewById(R.id.homeimg);

            homebtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(FirstPage.this, HomeActivity.class);
                    startActivity(intent);
                    finish();
                }
            });
        } else {
            // The app is not launched for the first time, redirect to another activity
            Intent intent = new Intent(FirstPage.this, HomeActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
