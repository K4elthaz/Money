package org.pytorch.demo.objectdetection;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class captureInfo extends AppCompatActivity {

    private ConstraintLayout navMenu;

    private ImageView icon1,icon2,icon3,hide,sideb,hb,bb,box,cameraButton;

    private TextView inf,capture;

    private boolean isInAboutState = true; // Add this variable
    private static final int CAMERA_REQUEST_CODE = 123;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_capture_info);

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


        cameraButton = findViewById(R.id.captureImgBtn);
        cameraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Display a Toast message before proceeding
                Toast.makeText(getApplicationContext(), "Capture the money that it fills the entire screen", Toast.LENGTH_SHORT).show();

                // Delayed task to reset the background after a few seconds
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // Reset the background resource after the delay
                        cameraButton.setBackgroundResource(R.drawable.capture1);
                    }
                }, 2000); // 2000 milliseconds (adjust the time as needed)

                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                if (cameraIntent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(cameraIntent, CAMERA_REQUEST_CODE);
                }
            }
        });

        icon1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (captureInfo.this.getClass().equals(AboutActivity.class)) {
                    // Current activity is already AboutActivity, show toast
                    Toast.makeText(captureInfo.this, "You are already in About", Toast.LENGTH_SHORT).show();
                } else {
                    // Current activity is not AboutActivity, start AboutActivity
//                    icon1.setBackgroundResource(R.drawable.active_about);
//                    Intent intent = new Intent(AboutActivity.this, AboutActivity.class);
//                    startActivity(intent);
                }
            }
        });


        icon2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                icon2.setBackgroundResource(R.drawable.active_guide);
                Intent intent = new Intent(captureInfo.this, UserGuideActivity.class);
                startActivity(intent);
            }
        });

        icon3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                icon3.setBackgroundResource(R.drawable.active_dev);
                Intent intent = new Intent(captureInfo.this, DevelopersActivity.class);
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
                Intent intent = new Intent(captureInfo.this, HomeActivity.class);
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

            // Check if the current activity is AboutActivity
            if (captureInfo.this.getClass().equals(AboutActivity.class)) {
                // If it is, set the background resource for icon1
                icon1.setBackgroundResource(R.drawable.active_about);
            } else {
                // If it's not, you can handle other cases or leave it empty
//                icon1.setBackgroundResource(R.drawable.about1);
            }
        }
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(captureInfo.this, HomeActivity.class);
        startActivity(i);
        super.onBackPressed();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CAMERA_REQUEST_CODE && resultCode == RESULT_OK) {
            Bitmap imageBitmap = (Bitmap) data.getExtras().get("data");
            if (imageBitmap != null) {
                Intent intent = new Intent(captureInfo.this, Capture.class);
                intent.putExtra("captured_image", imageBitmap);
                startActivity(intent);
            }
        }
    }
}
