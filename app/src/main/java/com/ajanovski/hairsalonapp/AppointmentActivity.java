package com.ajanovski.hairsalonapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;

import com.ajanovski.hairsalonapp.utils.AppHolder;

import java.util.Calendar;

import ajanovski.hairsalonapp.R;

public class AppointmentActivity extends AppCompatActivity implements CalendarView.OnDateChangeListener {

    CalendarView cvAppointments;
    EditText etUsername;
    Button btnCreateAppointment;
    int year1, month1, day1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment);

        cvAppointments = findViewById(R.id.calendarView);
        etUsername = findViewById(R.id.etUser);
        btnCreateAppointment = findViewById(R.id.btnCreateAppointment);

        Calendar calendar = Calendar.getInstance();

        cvAppointments.setMinDate(calendar.getTimeInMillis());

        etUsername.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(checkFields()) {
                    enableButton(true);
                } else {
                    enableButton(false);
                }
            }
        });


        btnCreateAppointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String date = day1 + "/" + month1 + "/" + year1;

                Appointment appointment = new Appointment(etUsername.getText().toString(), date);

                AppHolder.appointments.add(appointment);

                AlertDialog.Builder dialog = new AlertDialog.Builder(AppointmentActivity.this);
                dialog.setTitle(R.string.success)
                        .setMessage(R.string.appointment_created)
                        .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                                finish();
                            }
                        });
                dialog.show();
            }
        });


        cvAppointments.setOnDateChangeListener(this);



    }


    public boolean checkFields() {
        String username = etUsername.getText().toString();
        if(username.length() > 0 && day1 != 0) {
            return true;
        } else {
            return false;
        }
    }

    public void enableButton(boolean enable) {
        btnCreateAppointment.setEnabled(enable);
    }

    @Override
    public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int day) {
        day1 = day;
        month1 = month + 1;
        year1 = year;
    }
}