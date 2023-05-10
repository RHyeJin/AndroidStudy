package com.kcci.framelayout;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private View imageView;
    private View imageView2;
    private Boolean imageIndex = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.imageView);
        imageView2 = findViewById(R.id.imageView2);
    }

    public void onButtonClicked(View v){
        changeImage();
    }

    private void changeImage(){
        if(imageIndex){
            imageView.setVisibility(View.VISIBLE);
            imageView2.setVisibility(View.INVISIBLE);
            Log.d("changeImage","imageIndex : "+imageIndex);
            imageIndex = !imageIndex;
        }else{
            imageView.setVisibility(View.INVISIBLE);
            imageView2.setVisibility(View.VISIBLE);
            Log.d("changeImage","imageIndex : "+imageIndex);
            imageIndex = !imageIndex;
        }
    }
}