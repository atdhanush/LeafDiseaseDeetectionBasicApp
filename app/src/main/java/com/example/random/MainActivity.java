package com.example.random;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private int mInputSize = 224;
    private String mModelPath = "converted_model.tflite";
    private String mLabelPath = "labels.txt";
    private  Classifier classifier;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            initClassifier();
            initViews();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void initClassifier() throws IOException {
        classifier = new Classifier(getAssets(), mModelPath, mLabelPath, mInputSize);
    }

    private void initViews() {
        findViewById(R.id.iv_1).setOnClickListener(this);
        findViewById(R.id.iv_2).setOnClickListener(this);
        findViewById(R.id.iv_3).setOnClickListener(this);
        findViewById(R.id.iv_4).setOnClickListener(this);

    }



    @Override
    public void onClick(View v) {
        Bitmap bitmap = ((BitmapDrawable)((ImageView)v).getDrawable()).getBitmap();

        List<Classifier.Recognition> result = classifier.recognizeImage(bitmap);

        Toast.makeText(this, result.get(0).toString(), Toast.LENGTH_SHORT).show() ;
    }
}
