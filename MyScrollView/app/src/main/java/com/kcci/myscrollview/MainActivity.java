package com.kcci.myscrollview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    String strId = null;
    String strPw = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button loginButton = findViewById(R.id.loginButton);
        EditText editTextId = findViewById(R.id.editTextTextPersonName);
        EditText editTextPw = findViewById(R.id.editTextTextPassword);
        TextView TextView = findViewById(R.id.textView);
        TextView TextView2 = findViewById(R.id.textView2);

        loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view){
                strId = editTextId.getText().toString();
                strPw = editTextPw.getText().toString();
                Log.d("onClick","id : "+strId+" pw : "+strPw);
                editTextId.setText(null);
                editTextPw.setText(null);
                TextView.setText("ID : " + strId);
                TextView2.setText("PW : " + strPw);
            }
        });
    }
}