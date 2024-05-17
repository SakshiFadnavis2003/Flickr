package com.example.videosapp;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.regex.Pattern;

public class SignupActivity extends AppCompatActivity {
    EditText editTextName, editTextPhone, editTextEmail, editTextUsername, editTextPassword;
    Button buttonSubmit;

    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        databaseHelper = new DatabaseHelper(this);

        editTextName = findViewById(R.id.editTextName);
        editTextPhone = findViewById(R.id.editTextPhone);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextUsername = findViewById(R.id.editTextUsername);
        editTextPassword = findViewById(R.id.editTextPassword);
        buttonSubmit = findViewById(R.id.buttonSubmit);

        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });
    }

    private void registerUser() {
        String name = editTextName.getText().toString().trim();
        String phone = editTextPhone.getText().toString().trim();
        String email = editTextEmail.getText().toString().trim();
        String username = editTextUsername.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        // Validation
        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(phone) || TextUtils.isEmpty(email) ||
                TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
            Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!isValidEmail(email)) {
            Toast.makeText(this, "Invalid email format", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!isValidPhone(phone)) {
            Toast.makeText(this, "Invalid phone number format", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!isValidUsername(username)) {
            Toast.makeText(this, "Username already exists", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!isValidPassword(password)) {
            Toast.makeText(this, "Password should be at least 6 characters long", Toast.LENGTH_SHORT).show();
            return;
        }

        long id = databaseHelper.addUser(name, phone, email, username, password);
        if (id != -1) {
            Toast.makeText(this, "User registered successfully", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(SignupActivity.this, LoginActivity.class));
            finish();
        } else {
            Toast.makeText(this, "Failed to register user", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }

    private boolean isValidPhone(String phone) {
        Pattern pattern = Pattern.compile("\\d{10}");
        return pattern.matcher(phone).matches();
    }

    private boolean isValidUsername(String username) {
        return !databaseHelper.checkUsername(username);
    }

    private boolean isValidPassword(String password) {
        return password.length() >= 6;
    }
}
