package org.pytorch.demo.objectdetection;

import static android.content.ContentValues.TAG;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.util.Log;
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

    LinearLayout linearLayout3, linearLayout4;

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
        linearLayout3 = findViewById(R.id.linearLayout3);
        linearLayout4 = findViewById(R.id.linearLayout4);

//        MENU BAR BTN CLICKLISTINER
//        ABOUT
        icon1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                icon1.setBackgroundResource(R.drawable.active_about);
//                Intent intent = new Intent(HomeActivity.this, AboutActivity.class);
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
                Intent i = new Intent(HomeActivity.this, AboutActivity.class);
                startActivity(i);
            }
        });

//      UserGuide
        icon2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                icon2.setBackgroundResource(R.drawable.active_guide);
//                Intent intent = new Intent(HomeActivity.this, UserGuideActivity.class);
//                startActivity(intent);
                // Change the background resource
                icon2.setBackgroundResource(R.drawable.active_guide);

                // Delayed task to reset the background after a few seconds
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // Reset the background resource after the delay
                        icon3.setBackgroundResource(R.drawable.userguide1);
                    }
                }, 2000); // 2000 milliseconds (adjust the time as needed)

                // Start the new activity immediately
                Intent i = new Intent(HomeActivity.this, UserGuideActivity.class);
                startActivity(i);
            }
        });

        //      Developers
        icon3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                icon3.setBackgroundResource(R.drawable.active_dev);
//                Intent intent = new Intent(HomeActivity.this, DevelopersActivity.class);
//                startActivity(intent);

                // Change the background resource
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
                Intent i = new Intent(HomeActivity.this, DevelopersActivity.class);
                startActivity(i);
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
                live.setVisibility(View.VISIBLE);
                linearLayout3.setVisibility(View.VISIBLE);
                linearLayout4.setVisibility(View.VISIBLE);
            }
        });


// CONVERT BTN
        ImageView convertButton = findViewById(R.id.convertImgBtn);

        convertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                convertButton.setBackgroundResource(R.drawable.active_convert);
//                Intent i = new Intent(HomeActivity.this, convert_real.class);
//                startActivity(i);
                // Change the background resource
                convertButton.setBackgroundResource(R.drawable.active_convert);

                // Delayed task to reset the background after a few seconds
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // Reset the background resource after the delay
                        convertButton.setBackgroundResource(R.drawable.convert1);
                    }
                }, 2000); // 2000 milliseconds (adjust the time as needed)

                // Start the new activity immediately
                Intent i = new Intent(HomeActivity.this, convert_real.class);
                startActivity(i);
            }
        });




        live.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                live.setBackgroundResource(R.drawable.active_live);
//                Intent i = new Intent(HomeActivity.this, MainActivity2.class);
//                startActivity(i);

                // Change the background resource
                live.setBackgroundResource(R.drawable.active_live);

                // Delayed task to reset the background after a few seconds
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // Reset the background resource after the delay
                        live.setBackgroundResource(R.drawable.live);
                    }
                }, 2000); // 2000 milliseconds (adjust the time as needed)

                // Start the new activity immediately
                Intent i = new Intent(HomeActivity.this, MainActivity2.class);
                startActivity(i);
            }
        });

//        UPLOAD BTN
        ImageView uploadBtn = findViewById(R.id.uploadImgBtn);
        uploadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                uploadBtn.setBackgroundResource(R.drawable.active_upload);
//                Intent i = new Intent(HomeActivity.this, ActivityManual.class);
//                startActivity(i);
                uploadBtn.setBackgroundResource(R.drawable.active_upload);

                // Delayed task to reset the background after a few seconds
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // Reset the background resource after the delay
                        uploadBtn.setBackgroundResource(R.drawable.upload1);
                    }
                }, 2000); // 2000 milliseconds (adjust the time as needed)

                // Start the new activity immediately
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
//                cameraButton.setBackgroundResource(R.drawable.active_capture);
//                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//
//                if (cameraIntent.resolveActivity(getPackageManager()) != null) {
//                    startActivityForResult(cameraIntent, CAMERA_REQUEST_CODE);
//                }

                // Change the background resource
                cameraButton.setBackgroundResource(R.drawable.active_capture);

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
            live.setVisibility(View.GONE);
//            linearLayout3.setVisibility(View.GONE);
//            linearLayout4.setVisibility(View.GONE);
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


    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause");
//        sideb.setVisibility(View.VISIBLE);
//        capb.setVisibility(View.VISIBLE);
//        upb.setVisibility(View.VISIBLE);
//        conb.setVisibility(View.VISIBLE);
//        live.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop");
        sideb.setVisibility(View.VISIBLE);
        capb.setVisibility(View.VISIBLE);
        upb.setVisibility(View.VISIBLE);
        conb.setVisibility(View.VISIBLE);
        live.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart");
    }

}
