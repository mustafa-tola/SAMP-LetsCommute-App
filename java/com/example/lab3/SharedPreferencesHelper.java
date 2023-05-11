package com.example.lab3;

import android.content.Context;
import android.content.SharedPreferences;
public class SharedPreferencesHelper {

        // Declare the shared preferences object and the editor object
        private SharedPreferences sp;
        private SharedPreferences.Editor editor;

        // Define the constructor
        public SharedPreferencesHelper(Context context) {
            // Initialize the shared preferences object with the name "carpooling_app" and the mode "private"
            sp = context.getSharedPreferences("carpooling_app", Context.MODE_PRIVATE);

            // Initialize the editor object
            editor = sp.edit();
        }

        // Define a method to insert a user into the shared preferences
        public boolean insertUser(String username, String password, int userType) {
            // Put the username, password and user type into the editor
            editor.putString(username + "_password", password);
            editor.putInt(username + "_userType", userType);

            // Commit the changes and return the result
            return editor.commit();
        }

        // Define a method to get a user from the shared preferences
        public User getUser(String username) {
            // Check if the username exists in the shared preferences
            if (sp.contains(username + "_password") && sp.contains(username + "_userType")) {
                // Get the password and user type from the shared preferences
                String password = sp.getString(username + "_password", null);
                int userType = sp.getInt(username + "_userType", -1);

                // Create a new user object with the username, password and user type
                User user = new User(username, password, userType);

                // Return the user object
                return user;
            } else {
                // Username does not exist in the shared preferences, return null
                return null;
            }
        }

}
