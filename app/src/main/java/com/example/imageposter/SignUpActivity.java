package com.example.imageposter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class SignUpActivity extends AppCompatActivity {

    public static final String TAG = "SignupActivity";
    private EditText etUsername;
    private EditText etPassword;
    private EditText etRepeatPassword;
    private Button btnSignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);


        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        etRepeatPassword = findViewById(R.id.etPasswordConfirm);
        btnSignup = findViewById(R.id.etSignup);

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "onClick login button");
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();
                String passwordConfirm = etRepeatPassword.getText().toString();
                signUpUser(username, password, passwordConfirm);
            }
        });
    }

    private void signUpUser(@NonNull String username, String password, String passwordConfirm)
    {
        Log.i(TAG, "Attempting to SignUp user " + username);

        if(username.isEmpty() || password.isEmpty())
        {
            Toast.makeText(SignUpActivity.this,"username or password can not be empty",Toast.LENGTH_SHORT).show();
            return;
        }

        if(passwordConfirm.equals(password)==false) {
            Toast.makeText(SignUpActivity.this, "Passwords don't match", Toast.LENGTH_LONG).show();
            return;
        }

        createUser(username,password);
        goMainActivity();
        Toast.makeText(SignUpActivity.this, "Success!", Toast.LENGTH_SHORT);
    }

    // Create the ParseUser
    private void createUser(String username, String password) {
        Log.i(TAG, "Attempting to signup user " + username);
        ParseUser user = new ParseUser();
        // Set core properties
        user.setUsername(username);
        user.setPassword(password);
        // Invoke signUpInBackground
        user.signUpInBackground(new SignUpCallback() {
            public void done(ParseException e) {
                if (e == null) {
                    // Hooray! Let them use the app now.
                    Toast.makeText(SignUpActivity.this, "Success!", Toast.LENGTH_LONG);
                    goMainActivity();
                } else {
                    // Sign up didn't succeed. Look at the ParseException
                    // to figure out what went wrong
                    Log.e(TAG, "could not sign up");
                }
            }
        });

    }

    private void goMainActivity() {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }
}