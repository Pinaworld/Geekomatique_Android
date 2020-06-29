package com.example.geekomatique;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class CancelAppointment extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cancel_appointment);

        String date_n = new SimpleDateFormat("dd MMM yyyy", Locale.getDefault()).format(new Date());
        TextView date  = (TextView) findViewById(R.id.actualDate);
        date.setText(date_n);
    }


    public void CancelAppoitment(View view){

        Toast toastConfirmedCancel = Toast.makeText(getApplicationContext(), "Rendez-vous annulé !", Toast.LENGTH_SHORT);
        toastConfirmedCancel.show();

        ReturnAppointmentActivity(view);
    }

    public void ReturnAppointmentActivity(View view) {
        Intent intent = new Intent(this, Appointment.class);
        startActivity(intent);
    }
}
