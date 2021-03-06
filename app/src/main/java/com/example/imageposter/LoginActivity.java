package com.example.imageposter;

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

public class LoginActivity extends AppCompatActivity {

    public static final String TAG = "LoginActivity";
    private EditText etUsername;
    private EditText etPassword;
    private Button btnLogin;
    private Button btnSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if (ParseUser.getCurrentSessionToken() !=null) {
            goMainActivity();
        }

        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnSignUp = findViewById(R.id.btnSignup);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "onClick login button");
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();
                loginUser(username, password);
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               goSignUpActivity();
            }
        });
    }

//    private void signUpUser(String username, String password)
//    {
//        Log.i(TAG, "Attempting to SignUp user " + username);
//
////        if(username.isEmpty() || password.isEmpty())
////        {
////            Toast.makeText(LoginActivity.this,"username or password can not be empty",Toast.LENGTH_SHORT).show();
////            return;
////        }
//
////        if(passwordConfirm!=password) {
////            Toast.makeText(LoginActivity.this, "Passwords don't match", Toast.LENGTH_LONG);
////            return;
////        }
//
//        createUser(username,password);
//        goMainActivity();
//        Toast.makeText(LoginActivity.this, "Success!", Toast.LENGTH_SHORT);
//    }
//
//    // Create the ParseUser
//    private void createUser(String username, String password) {
//        Log.i(TAG, "Attempting to signup user " + username);
//        ParseUser user = new ParseUser();
//        // Set core properties
//        user.setUsername(username);
//        user.setPassword(password);
//        // Invoke signUpInBackground
//        user.signUpInBackground(new SignUpCallback() {
//            public void done(ParseException e) {
//                if (e == null) {
//                    // Hooray! Let them use the app now.
//                    Toast.makeText(LoginActivity.this, "Success!", Toast.LENGTH_LONG);
//                } else {
//                    // Sign up didn't succeed. Look at the ParseException
//                    // to figure out what went wrong
//                    Log.e(TAG, "could not sign up");
//                }
//            }
//        });
//
//    }


    private void loginUser(String username, String password) {
        Log.i(TAG, "Attempting to login user " + username);
        ParseUser.logInInBackground(username, password, new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {
                if (e!=null) {
                    Log.e(TAG, "Issue with login" ,e);
                    Toast.makeText(LoginActivity.this, "Issue with login", Toast.LENGTH_LONG);
                    return;
                }
                goMainActivity();
                Toast.makeText(LoginActivity.this, "Success!", Toast.LENGTH_LONG);
            }
        });
    }

    private void goMainActivity() {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }

    private void goSignUpActivity() {
        Intent i = new Intent(this, SignUpActivity.class);
        startActivity(i);
        finish();
    }
}