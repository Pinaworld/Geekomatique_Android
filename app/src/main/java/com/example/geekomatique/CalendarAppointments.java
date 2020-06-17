package com.example.geekomatique;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class CalendarAppointments extends AppCompatActivity {

    CalendarAppointments calender;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_appointments);

        /**calender = (CalendarAppointments)
                findViewById(R.id.calender);*/
    }

    public void ReturnHomeAtivity(View view) {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }

    public void ListAppointmentbyDayActivity(View view) {
        Intent intent = new Intent(this, ListAppointments.class);
        startActivity(intent);
    }
}
