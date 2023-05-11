package com.example.lab3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.service.autofill.Dataset;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    // Declare the UI elements
    private EditText usernameEditText;
    private EditText passwordEditText;
    private EditText confirmPasswordEditText;
    private RadioGroup userTypeRadioGroup;
    private RadioButton ownerRadioButton;
    private RadioButton userRadioButton;
    private Button loginButton;
    private Button signupButton;

    // Define the onCreate method
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize the UI elements
        usernameEditText = (EditText) findViewById(R.id.usernameEditText);
        passwordEditText = (EditText) findViewById(R.id.passwordEditText);
        confirmPasswordEditText = (EditText) findViewById(R.id.confirmPasswordEditText);
        userTypeRadioGroup = (RadioGroup) findViewById(R.id.userTypeRadioGroup);
        ownerRadioButton = (RadioButton) findViewById(R.id.ownerRadioButton);
        userRadioButton = (RadioButton) findViewById(R.id.userRadioButton);
        loginButton = (Button) findViewById(R.id.loginButton);
        signupButton = (Button) findViewById(R.id.signupButton);

        // Set the onClick listeners for the buttons
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the input values
                String username = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                int userType = userTypeRadioGroup.getCheckedRadioButtonId();

                // Validate the input values
                if (username.isEmpty() || password.isEmpty() || userType == -1) {
                    Toast.makeText(MainActivity.this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
                } else {
                    // Check if the user exists in the shared preferences and has the correct password and user type
                    // For simplicity, we assume that there is a method called checkUser that returns true or false
                    boolean validUser = checkUser(username, password, userType);

                    if (validUser) {
                            // Go to the carpooling / trip planning page
                            Intent intent = new Intent(MainActivity.this, CarPoolingActivity.class);
                            intent.putExtra("username", username);
                            intent.putExtra("role",userType);
                            startActivity(intent);
                    } else {
                        // Login failed, show a toast message
                        Toast.makeText(MainActivity.this, "Invalid username or password or user type", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the input values
                String username = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                String confirmPassword = confirmPasswordEditText.getText().toString();
                int userType = userTypeRadioGroup.getCheckedRadioButtonId();

                // Validate the input values
                if (username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty() || userType == -1) {
                    Toast.makeText(MainActivity.this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
                } else if (!password.equals(confirmPassword)) {
                    Toast.makeText(MainActivity.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                } else {
                    // Check if the username is already taken in the shared preferences
                    // For simplicity, we assume that there is a method called checkUsername that returns true or false
                    boolean usernameTaken = checkUsername(username);

                    if (usernameTaken) {
                        // Username already exists, show a toast message
                        Toast.makeText(MainActivity.this, "Username already taken, please choose another one", Toast.LENGTH_SHORT).show();
                    } else {
                        // Username is available, insert the user into the shared preferences
                        // For simplicity, we assume that there is a method called insertUser that returns true or false
                        boolean userInserted = insertUser(username, password, userType);

                        if (userInserted) {
                            // User inserted successfully, show a toast message and go back to the main activity
                            Toast.makeText(MainActivity.this, "User registered successfully", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(MainActivity.this, CarPoolingActivity.class);
                            startActivity(intent);
                        } else {
                            // User insertion failed, show a toast message
                            Toast.makeText(MainActivity.this, "User registration failed, please try again later", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });
    }

    // Define a method to check if the user exists in the shared preferences and has the correct password and user type
    // For simplicity, we assume that there is a shared preferences class called SharedPreferencesHelper that has a method called getUser that returns a user object or null
    private boolean checkUser(String username, String password, int userType) {
        // Create an instance of the shared preferences helper class
        SharedPreferencesHelper sp = new SharedPreferencesHelper(this);
        User user = sp.getUser(username);

        // Check if the user is not null
        if (user != null) {
            // Get the password and user type from the user object
            String dbPassword = user.getPassword();
            int dbUserType = user.getUserType();

            // Compare the password and user type with the input values
            if (password.equals(dbPassword) && userType == dbUserType) {
                return true; // User exists and has the correct password and user type
            } else {
                return false; // User exists but has incorrect password or user type
            }
        } else {
            return false; // User does not exist in the shared preferences
        }
    }

    // Define a method to check if the username is already taken in the shared preferences
    // For simplicity, we assume that there is a shared preferences class called SharedPreferencesHelper that has a method called getUser that returns a user object or null
    private boolean checkUsername(String username) {
        // Create an instance of the shared preferences helper class
        SharedPreferencesHelper sp = new SharedPreferencesHelper(this);

        // Get the user from the shared preferences with the given username
        User user = sp.getUser(username);

        // Check if the user is not null
        if (user != null) {
            return true; // Username already exists in the shared preferences
        } else {
            return false; // Username does not exist in the shared preferences
        }
    }

    // Define a method to insert the user into the shared preferences
    // For simplicity, we assume that there is a shared preferences class called SharedPreferencesHelper that has a method called insertUser that returns true or false
    private boolean insertUser(String username, String password, int userType) {
        // Create an instance of the shared preferences helper class
        SharedPreferencesHelper sp = new SharedPreferencesHelper(this);

        // Insert the user into the shared preferences
        boolean result = sp.insertUser(username, password, userType);

        // Return the result
        return result;
    }

}
