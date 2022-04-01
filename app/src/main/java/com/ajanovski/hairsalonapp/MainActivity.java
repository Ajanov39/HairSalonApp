package com.ajanovski.hairsalonapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.ajanovski.hairsalonapp.utils.AppHolder;
import com.ajanovski.hairsalonapp.utils.AppointmentListener;

import ajanovski.hairsalonapp.R;

public class MainActivity extends AppCompatActivity implements AppointmentListener {

    RecyclerView rvAppointments;
    Button btnNewAppointment;
    AppointmentsAdapter adapter;
    AppointmentListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvAppointments = findViewById(R.id.rvAppointments);
        btnNewAppointment = findViewById(R.id.btnNewAppointment);

        listener = this;


        adapter = new AppointmentsAdapter(AppHolder.appointments, listener);

        rvAppointments.setAdapter(adapter);

        btnNewAppointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AppointmentActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void deleteAppointment(Appointment appointment) {
        AppHolder.appointments.remove(appointment);
        adapter.notifyDataSetChanged();
    }


    @Override
    protected void onResume() {
        super.onResume();
        rvAppointments.setAdapter(null);
        adapter = new AppointmentsAdapter(AppHolder.appointments, listener);
        rvAppointments.setAdapter(adapter);

    }
}