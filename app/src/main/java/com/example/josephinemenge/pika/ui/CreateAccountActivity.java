package com.example.josephinemenge.pika.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.josephinemenge.pika.R;

import butterknife.Bind;

public class CreateAccountActivity extends AppCompatActivity implements View.OnClickListener{
    @Bind(R.id.createUserButton) Button mCreateUserButton;
    @Bind(R.id.nameEditText) EditText mNameEditText;
    @Bind(R.id.passwordEditText) EditText mPassword;
    @Bind(R.id.confirmPasswordEditText) EditText mConfirmPassword;
    @Bind(R.id.emailEditText) EditText mEmailText;
    @Bind(R.id.loginTextView) TextView mLoginTextView;
    @Bind(R.id.createAccount) TextView mCreateAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
    }

    @Override
    public void onClick(View v) {

    }
}
