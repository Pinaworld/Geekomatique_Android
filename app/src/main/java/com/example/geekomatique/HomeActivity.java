package com.example.geekomatique;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        TextView mailAdress = (TextView) findViewById(R.id.MailShow);
        mailAdress.setText("juan@pino.com");

    }
    public void CalendarActivity(View view) {
        Intent intent = new Intent(this, CalendarAppointments.class);
        startActivity(intent);
    }
    public void DisponibilitiesActivity(View view) {
        Intent intent = new Intent(this, Disponibilities.class);
        startActivity(intent);
    }
    public void PrestationsActivity(View view) {
        Intent intent = new Intent(this, Prestations.class);
        startActivity(intent);
    }
    public void Disconnect(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}
