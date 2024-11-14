package com.shrivecw.lflink;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class HomePage extends AppCompatActivity {

    private Button action1, action2, action3, action4, raiseRequest;
    private String registrationNumber;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        // Find the buttons by their IDs
        action1 = findViewById(R.id.btn_action1);
        action2 = findViewById(R.id.btn_action2);
        action3 = findViewById(R.id.btn_action3);
        action4 = findViewById(R.id.btn_action4);
        raiseRequest = findViewById(R.id.btn_raise_request);
        registrationNumber = getIntent().getStringExtra("registration_number");

        // Set onClickListeners for the buttons
        action1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Log.d("HomePage", "Action 1 button clicked");
                if (registrationNumber != null) {
                    Log.d("HomePage", "Registration Number: " + registrationNumber);
                    Intent intent = new Intent(HomePage.this, LiveRequests.class);
                    intent.putExtra("registration_number", registrationNumber);
                    startActivity(intent);
                } else {
                    Log.w("HomePage", "Registration number is null");
                    Toast.makeText(HomePage.this, "Registration number is missing", Toast.LENGTH_SHORT).show();
                }*/
            }
        });


        action2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(HomePage.this, "Action 2 Clicked", Toast.LENGTH_SHORT).show();
                /*Intent intent = new Intent(HomePage.this, MainActivity2.class);
                intent.putExtra("registration_number", registrationNumber);
                startActivity(intent);*/
                //finish();
            }
        });

        action3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(HomePage.this, "Action 3 Clicked", Toast.LENGTH_SHORT).show();
                /*Intent intent = new Intent(HomePage.this, LiveRequests.class);
                intent.putExtra("registration_number", registrationNumber);
                startActivity(intent);*/
                //finish();
            }
        });

        action4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(HomePage.this, "Action 4 Clicked", Toast.LENGTH_SHORT).show();
                /*Intent intent = new Intent(HomePage.this, LiveRequests.class);
                intent.putExtra("registration_number", registrationNumber);
                startActivity(intent);*/
                //finish();
            }
        });

        raiseRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(HomePage.this, "Raise Request Clicked", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(HomePage.this, RaiseRequest.class);
                intent.putExtra("registration_number", registrationNumber);
                startActivity(intent);
                finish();
                // You can add intent here to navigate to another activity or request handling
            }
        });
    }
}