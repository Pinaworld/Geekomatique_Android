package com.example.geekomatique;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class CancelAppointment extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cancel_appointment);
    }


    public void ReturnAppointmentActivity(View view) {
        Intent intent = new Intent(this, Appointment.class);
        startActivity(intent);
    }
}