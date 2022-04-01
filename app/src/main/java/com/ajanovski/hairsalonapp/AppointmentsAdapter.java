package com.ajanovski.hairsalonapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ajanovski.hairsalonapp.utils.AppointmentListener;

import java.util.ArrayList;

import ajanovski.hairsalonapp.R;

public class AppointmentsAdapter extends RecyclerView.Adapter<AppointmentsAdapter.AppointmentsViewHolder> {

    ArrayList<Appointment> appointmentsList;
    AppointmentListener listener;

    public AppointmentsAdapter(ArrayList<Appointment> appointmentsList, AppointmentListener listener) {
        this.appointmentsList = appointmentsList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public AppointmentsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_appointment, null);

        AppointmentsViewHolder viewHolder = new AppointmentsViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AppointmentsViewHolder holder, int position) {
        Appointment appointment = appointmentsList.get(position);
        holder.tvAppointmentName.setText(appointment.getUser());
        holder.tvAppointmentDate.setText(appointment.getDate());
        holder.btnDeleteAppointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.deleteAppointment(appointment);
            }
        });
    }

    @Override
    public int getItemCount() {
        return appointmentsList.size();
    }

    public class AppointmentsViewHolder extends RecyclerView.ViewHolder {

        TextView tvAppointmentName, tvAppointmentDate;
        ImageButton btnDeleteAppointment;

        public AppointmentsViewHolder(@NonNull View itemView) {
            super(itemView);

            tvAppointmentDate = itemView.findViewById(R.id.tvAppointmentDate);
            tvAppointmentName = itemView.findViewById(R.id.tvAppointmentName);
            btnDeleteAppointment = itemView.findViewById(R.id.btnDeleteAppointment);

        }
    }

}
