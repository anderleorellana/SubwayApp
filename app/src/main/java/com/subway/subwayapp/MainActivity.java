package com.subway.subwayapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    int numero;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = (Button) findViewById(R.id.button);


    }

    public void onClick(View v) {
        // Do something in response to button click
        Intent i = new Intent(this, CreateAccount.class);
        System.out.println( "gaaa");
        startActivity(i);
    }
}