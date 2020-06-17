package com.example.geekomatique;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Appointment extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment);

        String date_n = new SimpleDateFormat("MMM dd, yyyy", Locale.getDefault()).format(new Date());
        TextView date  = (TextView) findViewById(R.id.actualDate);
        date.setText(date_n);
    }

    public void ReturnListAppointmentActivity(View view) {
        Intent intent = new Intent(this, ListAppointments.class);
        startActivity(intent);
    }
    public void ModifyAppointmentActivity(View view) {
        Intent intent = new Intent(this, ModifyAppointment.class);
        startActivity(intent);
    }
    public void CancelApointmentActivity(View view) {
        Intent intent = new Intent(this, CancelAppointment.class);
        startActivity(intent);
    }

    public void SendBill(View view){
        Button billButt = (Button) findViewById(R.id.SendBillButt);
        /**   button.setOnClickListener(new View.OnClickListener() {
         public void onClick(View v) {
         your handler code here
         }
         } */
    }

}
