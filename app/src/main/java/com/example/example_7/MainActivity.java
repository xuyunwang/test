package com.example.example_7;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ScreenDensity.setDensity(getApplication(), this);
        setContentView(R.layout.activity_main);
    }
}