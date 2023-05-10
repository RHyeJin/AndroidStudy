package com.kcci.loginactivity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_MENU = 101;
    EditText editText;
    TextView textViewId;
    TextView textViewPw;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editTextIP);
        Button button = findViewById(R.id.button);
        textViewId = findViewById(R.id.textViewID);
        textViewPw = findViewById(R.id.textViewPW);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
                String ipString = editText.getText().toString();
                textViewId.setText(null);
                textViewPw.setText(null);
                intent.putExtra("IP", ipString);
                startActivityForResult(intent, REQUEST_CODE_MENU);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQUEST_CODE_MENU){
            if(resultCode == RESULT_OK){
                editText.setText(null);
                String strId = data.getStringExtra("ID");
                String strPw = data.getStringExtra("PW");
                Log.d("backToMain","id : "+strId+" pw : "+strPw);
                textViewId.setText("ID : " + strId);
                textViewPw.setText("PW : " + strPw);
            }
        }
    }
}