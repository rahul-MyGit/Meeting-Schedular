package com.example.meetingschedular;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.meetingschedular.Database.DataBaseConn;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class Fragment1 extends Fragment {

    EditText agenda;
    TextView date,time;
    DataBaseConn dbc;
    Button btn;
    int hour, minute;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_1, container, false);

        date=view.findViewById(R.id.txtDate);
        time=view.findViewById(R.id.txtTime);
        agenda=view.findViewById(R.id.txtAgenda);
        btn=view.findViewById(R.id.btn1);

        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        dbc=new DataBaseConn(getActivity());

        date.setOnClickListener(v -> {
            DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    month = month+1;
                    String date1 = dayOfMonth+"/"+month+"/"+year;
                    date.setText(date1);
                }
            },year,month,day);
            datePickerDialog.show();
        });

        time.setOnClickListener(v -> {
            TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int min) {
                    hour = hourOfDay;
                    minute = min;
                    time.setText(String.format(Locale.getDefault(),"%02d:%02d",hour,minute));
                }
            };
            TimePickerDialog timePickerDialog = new TimePickerDialog(getContext(),onTimeSetListener,hour,minute,false);

            timePickerDialog.setTitle("Select Time");
            timePickerDialog.show();
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mdate,mTime,mAgenda;
                mdate=date.getText().toString();
                mTime=time.getText().toString();
                mAgenda=agenda.getText().toString();

                if (mdate.isEmpty() || mAgenda.isEmpty() || mTime.isEmpty()) {
                    Toast.makeText(getContext(), "All Fields Required!", Toast.LENGTH_SHORT).show();
                }
                else {
                    dbc.insertvalue(mdate,mTime,mAgenda);
                    Toast.makeText(getActivity(), "Data Inserted", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return view;
    }
    private void closeKeyBoard(){
        View  view = getActivity().getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)
                    getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }

    }

}
