package com.kcci.socketclient;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    static MainHandler mainHandler;
    ClientThread clientThread;
    TextView textView;
    ScrollView scrollView;

    SimpleDateFormat dataFormat = new SimpleDateFormat("yy.MM.dd HH:mm:ss");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText editTextIp = findViewById(R.id.editTextIP);
        EditText editTextPort = findViewById(R.id.editTextPort);

        ToggleButton linkButton = findViewById(R.id.toggleButton);
        EditText editTextChat = findViewById(R.id.editTextChat);
        Button sendButton = findViewById(R.id.sendButton);
        sendButton.setEnabled(false);
        textView = findViewById(R.id.textView);
        scrollView = findViewById(R.id.scrollViewRecv);

        linkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(linkButton.isChecked()){
                    String serverIp = editTextIp.getText().toString();
                    String serverPort = editTextPort.getText().toString();

                    clientThread = new ClientThread(serverIp, serverPort);
                    clientThread.start();
                    sendButton.setEnabled(true);
                }else{
                    clientThread.stopClient();
                    sendButton.setEnabled(false);
                }
            }
        });

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String message = editTextChat.getText().toString();
                clientThread.sendMessage(message);
                Log.d("button2", message);
                editTextChat.setText(null);
            }
        });
        mainHandler = new MainHandler();
    }
    class MainHandler extends Handler {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            Date date = new Date();
            String strData = dataFormat.format(date);
            Bundle bundle = msg.getData();
            String data = bundle.getString("msg");
            textView.append(strData + " --> " + data+'\n');
            scrollView.fullScroll(View.FOCUS_DOWN);
        }
    }
}