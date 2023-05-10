package net.kcci.HomeIot;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.fragment.app.Fragment;


import java.text.SimpleDateFormat;
import java.util.Date;

public class Fragment3Telnet extends Fragment {
    ClientThread clientThread;
    TextView textView;
    ScrollView scrollViewRecv;
    ToggleButton toggleButtonStart;
    SimpleDateFormat dataFormat = new SimpleDateFormat("yy.MM.dd HH:mm:ss");
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment3telnet, container, false);



        if(ClientThread.socket != null)
            toggleButtonStart.setEnabled(false);

        return  view;
    }

    void recvDataProcess(String data) {
        Date date = new Date();
        String strDate = dataFormat.format(date);
        data += '\n';
        strDate = strDate + " " + data;
        textView.append(strDate);
        scrollViewRecv.fullScroll(View.FOCUS_DOWN);
    }
}
