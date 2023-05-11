package com.example.lab3;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.view.WindowCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.lab3.databinding.ActivityCarPoolingBinding;

public class CarPoolingActivity extends AppCompatActivity {

    private TextView welcomeTextView;
    private EditText sourceEditText;
    private EditText destinationEditText;
    private Button actionButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_car_pooling);

                // Initialize the UI elements
                welcomeTextView = (TextView) findViewById(R.id.welcomeTextView);
                sourceEditText = (EditText) findViewById(R.id.sourceEditText);
                destinationEditText = (EditText) findViewById(R.id.destinationEditText);
                actionButton = (Button) findViewById(R.id.actionButton);

                // Get the username from the intent
                Intent intent = getIntent();
                String username = intent.getStringExtra("username");
                String userType = intent.getStringExtra("userType");

                // Set the welcome text view with the username
                welcomeTextView.setText("Welcome, " + username + "!");
                if(userType.equals("Vehicle Owner")) {
                    actionButton.setText("Plan Trip");
                }
                else {
                    actionButton.setText("Offer Ride");
                }
        // Set the onClick listeners for the buttons
        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(sourceEditText.getText().toString().equals("") || destinationEditText.getText().toString().equals("")) {
                    Toast.makeText(CarPoolingActivity.this,"Give source or destination in the fields",Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(CarPoolingActivity.this,"Your ride has been on waiting list. We will notify you when ride is confirmed",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}