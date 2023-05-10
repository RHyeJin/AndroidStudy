package com.kcci.loginactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MenuActivity extends AppCompatActivity {

    String editStrId;
    String editStrPw;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        TextView textViewIP = findViewById(R.id.textViewIP);
        EditText editTextID = findViewById(R.id.editTextID);
        EditText editTextPW = findViewById(R.id.editTextPW);
        Button button2 = findViewById(R.id.button2);



        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editStrId = editTextID.getText().toString();
                editStrPw = editTextPW.getText().toString();
                Intent intent = new Intent();
                intent.putExtra("ID", editStrId);
                intent.putExtra("PW", editStrPw);
                setResult(RESULT_OK, intent);
//                textViewIP.setText(null);
                finish();
            }
        });
        Intent gIntent = getIntent();
        String strIP = gIntent.getStringExtra("IP");
        Log.d("menuGetIp","ip : "+strIP);
        textViewIP.setText(strIP);
    }
}