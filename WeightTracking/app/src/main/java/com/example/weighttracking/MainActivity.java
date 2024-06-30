package com.example.weighttracking;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

public class MainActivity extends AppCompatActivity {

    private EditText emailEditText;
    private EditText passwordEditText;
    private Button loginButton;
    private Button createAccountButton;
    private TextView appTitle;
    private Button toggleThemeButton;
    private ImageView togglePasswordVisibility;
    private DatabaseHelper dbHelper;
    private boolean isPasswordVisible = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new DatabaseHelper(this);
        appTitle = findViewById(R.id.appTitle);
        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        loginButton = findViewById(R.id.loginButton);
        createAccountButton = findViewById(R.id.createAccountButton);
        toggleThemeButton = findViewById(R.id.toggleThemeButton);
        togglePasswordVisibility = findViewById(R.id.togglePasswordVisibility);

        // Check and add admin account if not exists
        if (!dbHelper.checkUser("admin@example.com", "adminpassword")) {
            dbHelper.addUser("admin", "admin@example.com", "adminpassword", "Admin User");
        }

        loginButton.setOnClickListener(v -> {
            String email = emailEditText.getText().toString();
            String password = passwordEditText.getText().toString();

            if (dbHelper.checkUser(email, password)) {
                Intent intent = new Intent(MainActivity.this, LandingPageActivity.class);
                startActivity(intent);
            } else {
                Toast.makeText(MainActivity.this, "Incorrect username/password", Toast.LENGTH_SHORT).show();
            }
        });

        createAccountButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
            startActivity(intent);
        });

        toggleThemeButton.setOnClickListener(v -> {
            int nightMode = AppCompatDelegate.getDefaultNightMode();
            if (nightMode == AppCompatDelegate.MODE_NIGHT_YES) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            }
        });

        togglePasswordVisibility.setOnClickListener(v -> {
            if (isPasswordVisible) {
                passwordEditText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                togglePasswordVisibility.setImageResource(R.drawable.ic_visibility_off);
            } else {
                passwordEditText.setInputType(InputType.TYPE_CLASS_TEXT);
                togglePasswordVisibility.setImageResource(R.drawable.ic_visibility);
            }
            isPasswordVisible = !isPasswordVisible;
            passwordEditText.setSelection(passwordEditText.length());
        });
    }
}
