package com.example.renaldo.agendadecontatos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.renaldo.agendadecontatos.ui.main.MainFragment;

public class MainActivity extends AppCompatActivity {

    public static final String CONTACT_ID = "CONTACT_ID";
    public static final int NEW_CONTACT = -1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

    }
}