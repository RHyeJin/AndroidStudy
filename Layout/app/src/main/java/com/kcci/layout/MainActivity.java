package com.kcci.layout;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    String strId = null;
    String strPw = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.linearlayout);

        Button login = findViewById(R.id.login);
        EditText editTextId = findViewById(R.id.editTextId);
        EditText editTextPw = findViewById(R.id.editTextPw);

        login.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view){
                strId = editTextId.getText().toString();
                strPw = editTextPw.getText().toString();
                Log.d("onClick","id : "+strId+" pw : "+strPw);
                Toast.makeText(view.getContext(), "id : " + strId + ", pw : "+strPw, Toast.LENGTH_LONG).show();
                editTextId.setText(null);
                editTextPw.setText(null);
            }
        });

    }
}