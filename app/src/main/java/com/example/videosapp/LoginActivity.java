package com.example.videosapp;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    EditText editTextUsername, editTextPassword;
    Button buttonLogin, buttonSignup;

    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        databaseHelper = new DatabaseHelper(this);

        editTextUsername = findViewById(R.id.editTextUsername);
        editTextPassword = findViewById(R.id.editTextPassword);
        buttonLogin = findViewById(R.id.buttonLogin);
        buttonSignup = findViewById(R.id.buttonSignup);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser();
            }
        });

        buttonSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the SignupActivity
                startActivity(new Intent(LoginActivity.this, SignupActivity.class));
            }
        });

    }

    private void loginUser() {
        String username = editTextUsername.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
            Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!databaseHelper.checkUser(username, password)) {
            Toast.makeText(this, "Invalid username or password", Toast.LENGTH_SHORT).show();
            return;
        }

        Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show();

        startActivity(new Intent(LoginActivity.this, ListVideos.class));
        finish(); // Finish the LoginActivity
        // Proceed to next activity or perform desired action
    }
}
