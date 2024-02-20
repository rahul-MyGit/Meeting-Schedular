package com.example.meetingschedular;

import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.meetingschedular.Database.DataBaseConn;

public class Fragment2 extends Fragment {

    EditText date;
    CalendarView cal;
    Button btn1;
    DataBaseConn dbc;
    TextView t;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_2, container, false);

        date=view.findViewById(R.id.editTextDate);
        cal=view.findViewById(R.id.calendarView);
        btn1=view.findViewById(R.id.btn2);
        dbc=new DataBaseConn(getActivity());
        // t=()

        cal.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                String d=dayOfMonth+"/"+(month+1)+"/"+year;
                date.setText(d);
            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String d1=date.getText().toString();
                StringBuffer res=new StringBuffer();
                Cursor c=dbc.fetch(d1);
                int count=c.getCount();
                c.moveToFirst();
                if(count>0) {
                    do {
                        res.append(c.getString(c.getColumnIndex("agenda"))+"\t"+"at"+"\t"+c.getString(c.getColumnIndex("time")));
                        res.append("\n");

                        //med = (String.valueOf(c.getString(c.getColumnIndex("agenda"))));
                        //med1 = (String.valueOf(c.getString(c.getColumnIndex("time"))));
                    }while (c.moveToNext());
                    Toast.makeText(getActivity(), res, Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(getActivity(), "No Meeting on This Day....", Toast.LENGTH_SHORT).show();

                }

            }
        });
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }
}