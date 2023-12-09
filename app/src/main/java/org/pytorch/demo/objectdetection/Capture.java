package org.pytorch.demo.objectdetection;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.camera.view.PreviewView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.pytorch.IValue;
import org.pytorch.LiteModuleLoader;
import org.pytorch.Module;
import org.pytorch.Tensor;
import org.pytorch.torchvision.TensorImageUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class Capture extends AppCompatActivity {

    private ConstraintLayout navMenu;
    private int mImageIndex = 0;
    private String[] mTestImages;
    final private String imagesPath = "examples/";
    private Button buttonTest;

    private ImageView mImageView, icon1, icon2, icon3, hide, sideb, hb, bb, frnNT, bckNT;
    private ResultView mResultView;
    private Button mButtonDetect, dBtn, sBtn;
    private TextView textViewDetection, txt1,txt2;
    private ProgressBar mProgressBar;
    private Bitmap mBitmap = null;
    private Module mModule = null;  // our model
    private float mImgScaleX, mImgScaleY, mIvScaleX, mIvScaleY, mStartX, mStartY;

    CameraController cameraController;
    private PreviewView previewView;
    final int PERMISSION_REQUEST_CODE = 1;

    // BroadcastReceiver to trigger "Detect" button click
    private BroadcastReceiver detectButtonReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if ("trigger_detect".equals(intent.getAction())) {
                // Programmatically trigger the "Detect" button click event
                mButtonDetect = findViewById(R.id.detectButton);
                mButtonDetect.performClick();
            }
        }
    };

    // Function to get path of assets as YOLO weights
    public static String assetFilePath(Context context, String assetName) throws IOException {
        File file = new File(context.getFilesDir(), assetName);
        if (file.exists() && file.length() > 0) {
            return file.getAbsolutePath();
        }

        try (InputStream is = context.getAssets().open(assetName)) {
            try (OutputStream os = new FileOutputStream(file)) {
                byte[] buffer = new byte[4 * 1024];
                int read;
                while ((read = is.read(buffer)) != -1) {
                    os.write(buffer, 0, read);
                }
                os.flush();
            }
            return file.getAbsolutePath();
        }
    }

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manual);

        // Check permission for loading images from the gallery
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
        }
        // Check permission to use the camera
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 1);
        }

        // Set layout
        setContentView(R.layout.activity_manual);
        textViewDetection = findViewById(R.id.textViewDetect);

        // Show image
        mImageView = findViewById(R.id.frntbankNote);

        // Retrieve the captured image from the intent
        Bitmap capturedImage = getIntent().getParcelableExtra("captured_image");

        if (capturedImage != null) {
            // Check if the image is in portrait mode
            if (capturedImage.getHeight() > capturedImage.getWidth()) {
                Matrix matrix = new Matrix();
                matrix.postRotate(90); // Rotate 90 degrees for landscape

                // Rotate the image
                mBitmap = Bitmap.createBitmap(capturedImage, 0, 0, capturedImage.getWidth(), capturedImage.getHeight(), matrix, true);
            } else {
                mBitmap = capturedImage;
            }

            mImageView.setImageBitmap(mBitmap);
            mButtonDetect = findViewById(R.id.detectButton);
            mButtonDetect.postDelayed(new Runnable() {
                @Override
                public void run() {
                    mButtonDetect.performClick(); // Programmatically trigger detection
                }
            }, 100); // Programmatically trigger detection
        } else {
            // Load the default image
            // loadDefaultImage();
        }


        // Hide resultView
        mResultView = findViewById(R.id.resultView);
        mResultView.setVisibility(View.INVISIBLE);
        mResultView.getLayoutParams().height = mImageView.getHeight();


        // Load image from the gallery
        final Button buttonSelect = findViewById(R.id.selectButton);
        buttonSelect.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mResultView.setVisibility(View.INVISIBLE);
                // Building popup
                final CharSequence[] options = {"Open Gallery", "Back"};
                AlertDialog.Builder builder = new AlertDialog.Builder(Capture.this);
                builder.setTitle("New Test Image");
                builder.setItems(options, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int item) {
                        if (item == 0) { // open gallery
                            Intent pickPhoto = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                            startActivityForResult(pickPhoto, 1);
                        } else if (item == 1) {  // back
                            dialog.dismiss();
                        }
                    }
                });
                builder.show();
            }
        });

        // Detect image loaded in the mImageView
        mButtonDetect = findViewById(R.id.detectButton);
        mProgressBar = findViewById(R.id.progressBar);
        mButtonDetect.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mButtonDetect.setEnabled(false);
                mProgressBar.setVisibility(ProgressBar.VISIBLE);
//                mButtonDetect.setText(getString(R.string.run_model));
                // Scale image to 320x320
                mImgScaleX = (float) mBitmap.getWidth() / PrePostProcessor.mInputWidth;
                mImgScaleY = (float) mBitmap.getHeight() / PrePostProcessor.mInputHeight;

                mIvScaleX = (mBitmap.getWidth() > mBitmap.getHeight() ? (float) mImageView.getWidth() / mBitmap.getWidth() : (float) mImageView.getHeight() / mBitmap.getHeight());
                mIvScaleY = (mBitmap.getHeight() > mBitmap.getWidth() ? (float) mImageView.getHeight() / mBitmap.getHeight() : (float) mImageView.getWidth() / mBitmap.getWidth());

                mStartX = (mImageView.getWidth() - mIvScaleX * mBitmap.getWidth()) / 2;
                mStartY = (mImageView.getHeight() - mIvScaleY * mBitmap.getHeight()) / 2;

                // Run on a new Thread
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        // Get scaled bitmap
                        Bitmap resizedBitmap = Bitmap.createScaledBitmap(mBitmap, PrePostProcessor.mInputWidth, PrePostProcessor.mInputHeight, true);
                        // Create tensor
                        final Tensor inputTensor = TensorImageUtils.bitmapToFloat32Tensor(resizedBitmap, PrePostProcessor.NO_MEAN_RGB, PrePostProcessor.NO_STD_RGB);
                        // Forward tensor for inference
                        IValue[] outputTuple = mModule.forward(IValue.from(inputTensor)).toTuple();
                        // Retrieve output tensor
                        final Tensor outputTensor = outputTuple[0].toTensor();
                        final float[] outputs = outputTensor.getDataAsFloatArray();
                        // Get class result
                        final ArrayList<Result> results = PrePostProcessor.outputsToNMSPredictions(outputs, mImgScaleX, mImgScaleY, mIvScaleX, mIvScaleY, mStartX, mStartY);
                        // Update UI
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                mButtonDetect.setEnabled(true);
//                                mButtonDetect.setText(getString(R.string.detect));
                                mProgressBar.setVisibility(ProgressBar.INVISIBLE);
                                mResultView.setResults(results);
                                mResultView.invalidate();
                                mResultView.setVisibility(View.VISIBLE);

                                String textViewContent = generateTextViewContent(results);
                                sendTextViewContentToScanResultActivity(textViewContent);
                            }
                        });
                    }
                });
                thread.start();
            }
        });

        // Try to load our trained YOLOv5 model
        try {
            mModule = LiteModuleLoader.load(ActivityManual.assetFilePath(getApplicationContext(), "best416.torchscript.ptl"));
            BufferedReader br = new BufferedReader(new InputStreamReader(getAssets().open("classes.txt")));
            String line;
            List<String> classes = new ArrayList<>();
            while ((line = br.readLine()) != null) {
                classes.add(line);
            }
            PrePostProcessor.mClasses = new String[classes.size()];
            classes.toArray(PrePostProcessor.mClasses);
        } catch (IOException e) {
            Log.e("Object Detection", "Error reading assets", e);
            finish();
        }

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
        sBtn = findViewById(R.id.selectButton);


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
                sBtn.setVisibility(View.VISIBLE);
                dBtn.setVisibility(View.VISIBLE);
                frnNT.setVisibility(View.VISIBLE);
                bckNT.setVisibility(View.VISIBLE);
                txt1.setVisibility(View.VISIBLE);
                txt2.setVisibility(View.VISIBLE);
            }
        });

//        HOME AND BACK BTN FUNCTION
        hb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Navigate to HomeActivity
                Intent intent = new Intent(Capture.this, HomeActivity.class);
                startActivity(intent);
            }
        });

        bb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Go back to the previous activity (assuming it's HomeActivity)
                onBackPressed();
            }
        });
//        END OF HOME AND BACK BTN FUNCTION
    }

    private String generateTextViewContent(ArrayList<Result> results) {
        StringBuilder textViewContent = new StringBuilder();
        for (Result result : results) {
            String labelDetected = PrePostProcessor.mClasses[result.classIndex];
            labelDetected = labelDetected.replace("_", " ");
            labelDetected = labelDetected.toUpperCase();
            textViewContent.append(labelDetected)
                    .append("  ")
                    .append(String.valueOf(Math.round(result.score * 10000) / 100))
                    .append("%\n");
        }
        return textViewContent.toString();
    }

    // Send the textViewContent to ScanResultActivity
    private void sendTextViewContentToScanResultActivity(String textViewContent) {
        Intent intent = new Intent(this, ScanResultActivity.class);
        intent.putExtra("textViewContent", textViewContent);
        startActivity(intent);
    }

    //    PARA MAG HIDE AND VISIBLE YUNG NAV
    public void openNavMenu(View view) {
        if (navMenu.getVisibility() == View.VISIBLE) {
            navMenu.setVisibility(View.GONE);
        } else {
            navMenu.setVisibility(View.VISIBLE);
            sideb.setVisibility(View.GONE);
            hb.setVisibility(View.GONE);
            bb.setVisibility(View.GONE);
            sBtn.setVisibility(View.GONE);
            dBtn.setVisibility(View.GONE);
            frnNT.setVisibility(View.GONE);
            bckNT.setVisibility(View.GONE);
            txt1.setVisibility(View.GONE);
            txt2.setVisibility(View.GONE);
        }
    }



    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case PERMISSION_REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Permission granted
                } else {
                    Toast.makeText(getApplicationContext(), "Permission to Camera Denied", Toast.LENGTH_LONG).show();
                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_CANCELED) {
            switch (requestCode) {
                case 0: // photo taken
                    if (resultCode == RESULT_OK && data != null) {
                        mBitmap = (Bitmap) data.getExtras().get("data");

                        // Check if the image is in portrait mode
                        if (mBitmap.getHeight() > mBitmap.getWidth()) {
                            Matrix matrix = new Matrix();
                            matrix.postRotate(90); // Rotate 90 degrees for landscape

                            // Rotate the bitmap
                            mBitmap = Bitmap.createBitmap(mBitmap, 0, 0, mBitmap.getWidth(), mBitmap.getHeight(), matrix, true);
                        }

                        mImageView.setImageBitmap(mBitmap);
                    }
                    break;
                case 1: // photo loaded from gallery
                    if (resultCode == RESULT_OK && data != null) {
                        Uri selectedImage = data.getData();
                        String[] filePathColumn = {MediaStore.Images.Media.DATA};
                        if (selectedImage != null) {
                            Cursor cursor = getContentResolver().query(selectedImage,
                                    filePathColumn, null, null, null);
                            if (cursor != null) {
                                cursor.moveToFirst();
                                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                                String picturePath = cursor.getString(columnIndex);
                                cursor.close();
                                mBitmap = BitmapFactory.decodeFile(picturePath);
                                mImageView.setImageBitmap(mBitmap);
                                cursor.close();
                            }
                        }
                    }
                    break;
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Register the BroadcastReceiver to listen for "trigger_detect" action
        IntentFilter intentFilter = new IntentFilter("trigger_detect");
        registerReceiver(detectButtonReceiver, intentFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();

        // Unregister the BroadcastReceiver when the activity is paused
        unregisterReceiver(detectButtonReceiver);
    }
}