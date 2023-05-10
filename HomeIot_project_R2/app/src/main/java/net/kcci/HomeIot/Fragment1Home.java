package net.kcci.HomeIot;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import androidx.fragment.app.Fragment;


public class Fragment1Home extends Fragment {
    MainActivity mainActivity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment1home, container, false);
        mainActivity = (MainActivity)getActivity();

        return view;
    }
    void recvDataProcess(String strRecvData)
    {
        String[] splitLists = strRecvData.toString().split("\\[|]|@|\\n") ;
        for(int i=0; i< splitLists.length; i++)
            Log.d("recvDataProcess"," i: " + i + ", value: " +splitLists[i]);

        if(splitLists.length == 3) {
            String[] insertArgs = new String[3];
            insertArgs[0] = splitLists[1];          //"KSH_ARD"
            int index = splitLists[2].indexOf('O');
            String tempStr = splitLists[2].substring(0,index);
            insertArgs[1] = tempStr;                //"LAMP"
            insertArgs[2] = splitLists[2].substring(index);
            Log.d("database", " 0 : " + insertArgs[0] + " 1 : " + insertArgs[1] + " 2 : " + insertArgs[2] );
            mainActivity.database.updateRecordParam(mainActivity.SQLdb, insertArgs);
        }
    }
}
