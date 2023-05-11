package com.example.lab3;

public class User {
        // Declare the fields
        private String username;
        private String password;
        private int userType;

        // Define the constructor
        public User(String username, String password, int userType) {
            this.username = username;
            this.password = password;
            this.userType = userType;
        }

        // Define the getters and setters
        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public int getUserType() {
            return userType;
        }

        public void setUserType(int userType) {
            this.userType = userType;
        }

}
